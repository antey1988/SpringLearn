package ch8.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;

@Service
public class DBInitializer {
    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    InstrumentRepository instrumentRepository;

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization...");

        Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentRepository.save(guitar);
        Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentRepository.save(piano);
        Instrument voice = new Instrument();
        voice.setInstrumentId("Voice");
        instrumentRepository.save(voice);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(Date.valueOf("1980-04-12"));
        singer.addInstrument(guitar);
        singer.addInstrument(piano);
        Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(Date.valueOf("2017-01-20"));
        singer.addAlbum(album1);
        Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(Date.valueOf("2009-03-17"));
        singer.addAlbum(album2);
        singerRepository.save(singer);

        singer = new Singer();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(Date.valueOf("1945-02-30"));
        singer.addInstrument(guitar);
        Album album3 = new Album();
        album3.setTitle("From The Cradle");
        album3.setReleaseDate(Date.valueOf("1994-08-13"));
        singer.addAlbum(album3);
        singerRepository.save(singer);

        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(Date.valueOf("1975-03-01"));
        singer.addInstrument(guitar);
        singerRepository.save(singer);

        logger.info("Database initialization finished.");
    }
}
