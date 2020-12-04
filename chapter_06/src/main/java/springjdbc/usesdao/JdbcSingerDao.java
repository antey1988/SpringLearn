package springjdbc.usesdao;

import entryjdbc.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import springjdbc.usesdao.dao.SingerDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {

//    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /*@Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT first_name || ' ' || last_name FROM singer WHERE id = ?",
                new Object[]{id}, String.class);
    }*/
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
