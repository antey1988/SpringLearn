package ch8.services;

import ch8.entities.Singer;

import java.util.List;

public interface SingerServiceRepository {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);

}
