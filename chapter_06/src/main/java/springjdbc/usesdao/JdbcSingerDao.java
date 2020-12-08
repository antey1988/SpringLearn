package springjdbc.usesdao;

import entryjdbc.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import springjdbc.usesdao.dao.SingerDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public String findNameById(Long id) {
        String sql = "SELECT first_name ||' '|| last_name FROM Singer where id = :singerid";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("singerid", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public void insert(Singer singer) {
        throw new NotImplementedException("insert");

    }

    @Override
    public void update(Singer singer) {
        throw new NotImplementedException("insert");

    }

    @Override
    public void insertWithAlbums(Singer singer) {
        throw new NotImplementedException("insertWithAlbums");
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        throw new NotImplementedException("findByFirstName");
    }

    @Override
    public List<Singer> findAll() {
        throw new NotImplementedException("findAll");
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        throw new NotImplementedException("findAll");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (namedParameterJdbcTemplate == null)
            throw new BeanCreationException("Must set " + namedParameterJdbcTemplate.getClass().getSimpleName() +" on SingerDao");
    }
}
