package springjdbc.usesdao;

import entryjdbc.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import springjdbc.usesdao.dao.SingerDao;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT first_name || ' ' || last_name FROM singer WHERE id = ?",
                new Object[]{id}, String.class);
    }
    @Override
    public void insert(Singer singer) {
        throw new NotImplementedException("insert");

    }
    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null)
            throw new BeanCreationException("Must set jdbcTemplate on SingerDao");
    }

}
