package springjdbc.usesdao.annot;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertSingerWithAlbums extends BatchSqlUpdate {
    private static final String SQL = "insert into Album " +
            "(singer_id, title, release_date) " +
            "values(:singer_id, :title, :release_date)";
    private static final int BATCH_SIZE = 10;

    public InsertSingerWithAlbums(DataSource ds) {
        super(ds, SQL);
        this.declareParameter(new SqlParameter("singer_id", Types.INTEGER));
        this.declareParameter(new SqlParameter("title", Types.VARCHAR));
        this.declareParameter(new SqlParameter("release_date", Types.DATE));
        /*this.setGeneratedKeysColumnNames("id");
        this.setReturnGeneratedKeys(true);*/
        this.setBatchSize(BATCH_SIZE);
    }
}
