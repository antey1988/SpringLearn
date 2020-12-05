package springjdbc.usesdao.dao;

import entryjdbc.entities.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findNameById(Long id);
    List<Singer> findAllWithAlbums();
    void insertWithAlbums(Singer singer);
    void insert(Singer singer);
    void update(Singer singer);
}
