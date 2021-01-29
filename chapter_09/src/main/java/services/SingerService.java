package services;

import entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    int countAll();
}
