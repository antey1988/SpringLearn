package springjdbc.usesdao.dao;

import entryjdbc.entities.Singer;

import java.sql.SQLException;
import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findNameById(Long id);
    List<Singer> findAllWithAlbums();
    void insert(Singer singer);
    void update(Singer singer);
}
