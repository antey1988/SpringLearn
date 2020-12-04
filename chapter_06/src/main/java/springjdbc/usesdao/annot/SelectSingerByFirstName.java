package springjdbc.usesdao.annot;

import entryjdbc.entities.Singer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SelectSingerByFirstName extends MappingSqlQuery<Singer> {
    private static final String SQL = "select id, first_name, last_name, birth_date from singer " +
            "where first_name = :first_name";

    public SelectSingerByFirstName(DataSource dataSource) {
        super(dataSource, SQL);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
    }

    @Override
    protected Singer mapRow(ResultSet resultSet, int i) throws SQLException {
        Singer singer = new Singer();
        singer.setId(resultSet.getLong("id"));
        singer.setFirstName(resultSet.getString("first_name"));
        singer.setLastName(resultSet.getString("last_name"));
        singer.setBirthDate(resultSet.getDate("birth_date"));
        return singer;
    }
}
