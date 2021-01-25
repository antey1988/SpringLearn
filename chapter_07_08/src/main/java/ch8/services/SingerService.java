package ch8.services;

import ch8.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    List<Singer> findAllByNativeQuery();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);

    List<Singer> findByCriteriaQuery(String firstname, String lastName);

}
