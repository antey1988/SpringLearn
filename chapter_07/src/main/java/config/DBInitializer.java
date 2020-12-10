package config;

import dao.InstrumentDao;
import dao.SingerDao;
import entities.Album;
import entities.Instrument;
import entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class DBInitializer {
    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    SingerDao singerDao;
    @Autowired
    InstrumentDao instrumentDao;

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization...");
        //added instrument Guitar in DataBase
        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentDao.save(guitar);
        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentDao.save(piano);
        //create albums
        Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(new Date(2017,1,20));
        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(new Date(2009,10,12));
        //added singer in DataBase
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(1977, 9, 16));
        singer.addInstrument(guitar);
        singer.addInstrument(piano);
        singer.addAlbum(album1);
        singer.addAlbum(album2);

        singerDao.save(singer);

        logger.info("Database initialization finished.");

    }
}
