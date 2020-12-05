package springjdbc.usesdao.annot;

import entryjdbc.entities.Album;
import entryjdbc.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import springjdbc.usesdao.dao.SingerDao;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("singerDao")
public class AnnotJdbcSingerDao implements SingerDao {
    private static final Logger logger = LoggerFactory.getLogger(AnnotJdbcSingerDao.class);
    private DataSource dataSource;
    private SelectAllSingers selectAllSingers;
    private SelectSingerByFirstName selectSingerByFirstName;
    private UpdateSinger updateSinger;
    private InsertSinger insertSinger;
    private InsertSingerWithAlbums insertSingerWithAlbums;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
        this.updateSinger = new UpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
        this.insertSingerWithAlbums = new InsertSingerWithAlbums(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public String findNameById(Long id) {
        throw new NotImplementedException("findNamedById");
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", firstName);
        return selectSingerByFirstName.executeByNamedParam(paramMap);
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        String sql = "select s.id, s.first_name, s.last_name, s.birth_date, " +
                "a.id as album_id, a.singer_id, a.title, a.release_date " +
                "from singer as s " +
                "left join album as a " +
                "on s.id = a.singer_id";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(sql, (resultSet)->{
            Map<Long, Singer> map = new HashMap<>();
            Singer singer;
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                singer = map.get(id);
                if (singer == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(resultSet.getString("first_name"));
                    singer.setLastName(resultSet.getString("last_name"));
                    singer.setBirthDate(resultSet.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                Long albumId = resultSet.getLong("album_id");
                if (albumId > 0) {
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(resultSet.getLong("singer_id"));
                    album.setTitle(resultSet.getString("title"));
                    album.setReleaseDate(resultSet.getDate("release_date"));
                    singer.addAlbum(album);
                }
            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public void insert(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New singer inserted with id: " + singer.getId());
    }

    @Override
    public void update(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        paramMap.put("id", singer.getId());
        updateSinger.updateByNamedParam(paramMap);
        logger.info("Existing singer updated with id: " + singer.getId());
    }

    @Override
    public void insertWithAlbums(Singer singer) {
        insert(singer);
        List<Album> albums = singer.getAlbums();
        if (albums != null) {

            for (Album album : albums) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("singer_id", singer.getId());
                paramMap.put("title", album.getTitle());
                paramMap.put("release_date", album.getReleaseDate());
                /*KeyHolder keyHolder = new GeneratedKeyHolder();
                insertSingerWithAlbums.updateByNamedParam(paramMap, keyHolder);
                album.setId(keyHolder.getKey().longValue());*/
            }
            insertSingerWithAlbums.flush();
        }
    }
}
