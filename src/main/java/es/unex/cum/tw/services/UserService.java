package es.unex.cum.tw.services;

import es.unex.cum.tw.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
    List<User> findAll();
    Optional<User> findById(int id);
    boolean save(User user);
    boolean update(User user);
    boolean deleteById(int id);
    boolean delete(User user);

}
