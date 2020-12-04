package springjdbc.usesdao.annot;

import entryjdbc.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import springjdbc.usesdao.dao.SingerDao;

import javax.annotation.Resource;
import javax.sql.DataSource;
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

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
        this.updateSinger = new UpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
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
        throw new NotImplementedException("findAllWithAlbums");
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
}
