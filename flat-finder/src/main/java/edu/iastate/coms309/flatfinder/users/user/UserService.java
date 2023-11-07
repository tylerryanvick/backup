package edu.iastate.coms309.flatfinder.users.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Service class for managing business logic related to the User entity.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieve a user by their username.
     * @param userName The username to search for.
     * @return An optional user.
     */
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * Save or update a user.
     * @param user The user entity to save.
     * @return The saved user entity.
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Delete a user based on their username.
     * @param userName The username of the user to be deleted.
     */
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    /**
     * Check if a user with the given username exists.
     * @param userName The username to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    /**
     * Retrieve all users.
     * @return A list of all user entities.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Authenticate a user based on their username and password.
     * @param userName The username to authenticate.
     * @param userPassword The password to authenticate.
     * @return The authenticated user if found, null otherwise.
     */
    public User findByUserNameAndUserPassword(String userName, String userPassword) {
        return userRepository.findByUserNameAndUserPassword(userName, userPassword);
    }
}