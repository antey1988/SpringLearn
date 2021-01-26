package ch8.services;

import ch8.entities.Album;
import ch8.entities.Singer;
import ch8.repos.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dataJpaAlbumService")
@Transactional
public class AlbumServiceRepositoryImpl implements AlbumServiceRepository{

    private static Logger logger = LoggerFactory.getLogger(AlbumServiceRepositoryImpl.class);

    @Autowired
    private AlbumRepository albumRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Album> findBySinger(Singer singer) {
        return albumRepository.findBySinger(singer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Album> findByTitle(String title) {
        return albumRepository.findByTitle(title);
    }
}
