package fr.imt.tp.tpimt2.controllers;

import fr.imt.tp.tpimt2.exceptions.UserNotFoundException;
import fr.imt.tp.tpimt2.models.User;
import fr.imt.tp.tpimt2.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping( value = "/users")
    public ResponseEntity<List<User>> getUsers()
    {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping( value = "/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam("q") String query)
    {
        return new ResponseEntity<>(userService.searchUser(query), HttpStatus.OK);
    }

    @GetMapping( value = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id)
    {
        try
        {
            return new ResponseEntity(userService.getUserById(id), HttpStatus.OK);
        } catch(UserNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
