package fr.imt.tp.tpimt2.services.impl;

import fr.imt.tp.tpimt2.exceptions.UserNotFoundException;
import fr.imt.tp.tpimt2.models.User;
import fr.imt.tp.tpimt2.repositories.UserRepository;
import fr.imt.tp.tpimt2.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> searchUser(String query)
    {
        return userRepository.findByUsernameContains(query);
    }
}
