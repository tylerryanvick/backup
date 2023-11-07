package edu.iastate.coms309.flatfinder.users.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the User entity.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Retrieve a user based on the given username.
     * @param userName The username to search for.
     * @return An optional user.
     */
    Optional<User> findByUserName(String userName);

    /**
     * Delete a user based on the given username.
     * @param userName The username of the user to be deleted.
     */
    void deleteByUserName(String userName);

    /**
     * Check if a user with the given username exists.
     * @param userName The username to check.
     * @return true if the user exists, false otherwise.
     */
    boolean existsByUserName(String userName);

    /**
     * Retrieve a user based on the given username and password.
     * @param userName The username to search for.
     * @param userPassword The password to search for.
     * @return A user if found, null otherwise.
     */
    User findByUserNameAndUserPassword(String userName, String userPassword);
}