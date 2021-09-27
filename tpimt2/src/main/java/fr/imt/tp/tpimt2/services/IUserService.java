package fr.imt.tp.tpimt2.services;

import fr.imt.tp.tpimt2.exceptions.UserNotFoundException;
import fr.imt.tp.tpimt2.models.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserById(Long id) throws UserNotFoundException;
    List<User> searchUser(String query);
}
