package edu.iastate.coms309.flatfinder.users.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Service class for managing operations related to the Admin entity.
 */
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    /**
     * Constructor-based dependency injection for the AdminRepository.
     *
     * @param adminRepository the admin repository
     */
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Retrieves an admin by their username.
     *
     * @param userName the username of the admin
     * @return an Optional containing the admin if found, or empty otherwise
     */
    public Optional<Admin> findByUserName(String userName) {
        return adminRepository.findByUserName(userName);
    }

    /**
     * Saves an admin entity.
     *
     * @param admin the admin entity to save
     * @return the saved admin entity
     */
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    /**
     * Deletes an admin by their username.
     *
     * @param userName the username of the admin to delete
     */
    public void deleteByUserName(String userName) {
        adminRepository.deleteByUserName(userName);
    }

    /**
     * Checks if an admin exists by their username.
     *
     * @param userName the username of the admin
     * @return true if the admin exists, false otherwise
     */
    public boolean existsByUserName(String userName) {
        return adminRepository.existsByUserName(userName);
    }

    /**
     * Retrieves all admins.
     *
     * @return a list of all admins
     */
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    /**
     * Retrieves an admin by their username and password.
     *
     * @param userName     the username of the admin
     * @param userPassword the password of the admin
     * @return the found admin
     */
    public Admin findByUserNameAndUserPassword(String userName, String userPassword) {
        return adminRepository.findByUserNameAndUserPassword(userName, userPassword);
    }
}