package springjdbc.usesdao.dao;

import entryjdbc.entities.Singer;

import java.util.List;

public interface SingerDao {
    String findNameById(Long id);
    List<Singer> findAll();
    List<Singer> findAllWithAlbums();
    void insert(Singer singer);
}
