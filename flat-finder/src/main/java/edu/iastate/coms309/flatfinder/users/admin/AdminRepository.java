package edu.iastate.coms309.flatfinder.users.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

/**
 * Repository interface for accessing Admin data from the database.
 */
public interface AdminRepository extends JpaRepository<Admin, String> {

    /**
     * Finds an admin by their username.
     *
     * @param userName the username of the admin
     * @return an Optional containing the found admin, or empty otherwise
     */
    Optional<Admin> findByUserName(String userName);

    /**
     * Deletes an admin by their username.
     *
     * @param userName the username of the admin to delete
     */
    void deleteByUserName(String userName);

    /**
     * Checks if an admin exists by their username.
     *
     * @param userName the username of the admin
     * @return true if the admin exists, false otherwise
     */
    boolean existsByUserName(String userName);

    /**
     * Finds an admin by their username and password.
     *
     * @param userName     the username of the admin
     * @param userPassword the password of the admin
     * @return the found admin
     */
    Admin findByUserNameAndUserPassword(String userName, String userPassword);
}