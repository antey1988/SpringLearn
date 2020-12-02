package entryjdbc;

import entryjdbc.dao.PlainSingerDao;
import entryjdbc.dao.SingerDao;
import entryjdbc.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PlainJdbcDemo {
    private static SingerDao singerDao = new PlainSingerDao();
    private static Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);

    public static void main(String[] args) {
        logger.info("Listing initial singer data:");
        listAllSinger();

        logger.info("------------------");
        logger.info("Insert a new singer");
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date(new GregorianCalendar(1991, 2, 1991).getTime().getTime()));
        singerDao.insert(singer);
        logger.info("Listing singer data after new singer created:");
        listAllSinger();

        logger.info("------------------");
        logger.info("Deleting the previous created singer");
        singerDao.delete(singer.getId());
        logger.info("Listing singer data after new singer deleted:");
        listAllSinger();
    }

    private  static void listAllSinger() {
        List<Singer> singers = singerDao.findAll();
        singers.forEach(t->logger.info(t.toString()));
    }
}
