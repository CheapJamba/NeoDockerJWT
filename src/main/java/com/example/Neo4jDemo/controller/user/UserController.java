package com.example.Neo4jDemo.controller.user;

import com.example.Neo4jDemo.model.User;
import com.example.Neo4jDemo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User with id {" + id + "} doesn't exist");
        }
        return optionalUser.get();
    }

    @GetMapping("/byName/{name}")
    public User getUserByName(@PathVariable String name) {
        Optional<User> optionalUser = userRepository.findUserByName(name);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User with name {" + name + "} doesn't exist");
        }
        return optionalUser.get();
    }

    @PostMapping
    public User addUser(@RequestBody CreateUserDTO createUserDTO) {
        User user = new User(createUserDTO.name, createUserDTO.email);
        return userRepository.save(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping
    public User updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(updateUserDTO.id);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User with id {" + updateUserDTO.id + "} doesn't exist");
        }
        User user = optionalUser.get();
        if (updateUserDTO.name != null) {
            user.setName(updateUserDTO.name);
        }
        if (updateUserDTO.email != null) {
            user.setEmail(updateUserDTO.email);
        }
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Deleted";
        }
        return "Doesn't exist";
    }

    @GetMapping("/countGuests")
    public Collection<?> getGuestCount() {
        return userRepository.countGuestsPerHost();
    }

    @GetMapping("/possible/{name}")
    public Collection<?> getPossibleInvites(@PathVariable("name") String requesterName) {
        return userRepository.findPossibleInvites(requesterName);
    }

}
