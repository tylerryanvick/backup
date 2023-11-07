package edu.iastate.coms309.flatfinder.users.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Controller class managing HTTP actions related to the User entity.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves all users.
     *
     * @return List of all users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Find a user by their userName.
     *
     * @param userName Username of the user.
     * @return ResponseEntity containing the user entity if found.
     */
    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        return userService.findByUserName(userName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Authenticate and retrieve a user using userName and password.
     *
     * @param userName Username of the user.
     * @param userPassword Password of the user.
     * @return ResponseEntity containing the user entity if authenticated.
     */
    @GetMapping("/{userName}/{userPassword}")
    public ResponseEntity<User> getUserByUserNameAndUserPassword(@PathVariable String userName, @PathVariable String userPassword) {
        User user = userService.findByUserNameAndUserPassword(userName, userPassword);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new user.
     *
     * @param user User entity to be created.
     * @return ResponseEntity containing the saved user entity.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * Update user details based on userName.
     *
     * @param userName Username of the user to be updated.
     * @param updatedUser User entity containing the updated details.
     * @return ResponseEntity containing the updated user entity.
     */
    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUser(@PathVariable String userName, @RequestBody User updatedUser) {
        if (!userService.existsByUserName(userName)) {
            return ResponseEntity.notFound().build();
        }
        updatedUser.setUserName(userName); // Assuming userName is the primary key and is immutable
        return ResponseEntity.ok(userService.save(updatedUser));
    }

    /**
     * Update user's userName.
     *
     * @param userName Current username of the user.
     * @param newUserName New username to set.
     * @return ResponseEntity containing the updated user entity.
     */
    @PutMapping("/{userName}/userName")
    public ResponseEntity<User> updateUserName(@PathVariable String userName, @RequestParam String newUserName) {
        return updateUserField(userName, user -> user.setUserName(newUserName));
    }

    /**
     * Update user's email.
     *
     * @param userName Username of the user.
     * @param userEmail New email to set.
     * @return ResponseEntity containing the updated user entity.
     */
    @PutMapping("/{userName}/userEmail")
    public ResponseEntity<User> updateUserEmail(@PathVariable String userName, @RequestParam String userEmail) {
        return updateUserField(userName, user -> user.setUserEmail(userEmail));
    }

    /**
     * Update user's password.
     *
     * @param userName Username of the user.
     * @param userPassword New password to set.
     * @return ResponseEntity containing the updated user entity.
     */
    @PutMapping("/{userName}/userPassword")
    public ResponseEntity<User> updateUserPassword(@PathVariable String userName, @RequestParam String userPassword) {
        return updateUserField(userName, user -> user.setUserPassword(userPassword));
    }

    /**
     * Update user's account status.
     *
     * @param userName Username of the user.
     * @param accountStatus New account status to set.
     * @return ResponseEntity containing the updated user entity.
     */
    @PutMapping("/{userName}/accountStatus")
    public ResponseEntity<User> updateAccountStatus(@PathVariable String userName, @RequestParam String accountStatus) {
        return updateUserField(userName, user -> user.setAccountStatus(accountStatus));
    }

    /**
     * Delete a user by their userName.
     *
     * @param userName Username of the user to be deleted.
     * @return ResponseEntity indicating the result of the deletion operation.
     */
    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        if (!userService.existsByUserName(userName)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteByUserName(userName);
        return ResponseEntity.noContent().build();
    }

    /**
     * Helper method to update fields for a user.
     *
     * @param userName Username of the user to be updated.
     * @param fieldUpdater Functional interface to update a specific field of the user.
     * @return ResponseEntity containing the updated user entity.
     */
    private ResponseEntity<User> updateUserField(String userName, java.util.function.Consumer<User> fieldUpdater) {
        return userService.findByUserName(userName)
                .map(user -> {
                    fieldUpdater.accept(user);
                    userService.save(user);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}