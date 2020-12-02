package springjdbc.usesdao.dao;

import entryjdbc.entities.Singer;

public interface SingerDao {
    String findNameById(Long id);
    void insert(Singer singer);
}
