package edu.iastate.coms309.flatfinder.users.landlord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Service class handling business logic for the Landlord entity.
 */
@Service
public class LandlordService {

    @Autowired
    private LandlordRepository landlordRepository;

    /**
     * Save a landlord entity.
     *
     * @param landlord Landlord entity to be saved.
     * @return Saved landlord entity.
     */
    public Landlord save(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    /**
     * Retrieve all landlord entities.
     *
     * @return List of all landlords.
     */
    public List<Landlord> findAll() {
        return landlordRepository.findAll();
    }

    /**
     * Find a landlord by their userName.
     *
     * @param userName Username of the landlord.
     * @return Optional landlord entity.
     */
    public Optional<Landlord> findByUserName(String userName) {
        return landlordRepository.findByUserName(userName);
    }

    /**
     * Delete a landlord by their userName.
     *
     * @param userName Username of the landlord to be deleted.
     */
    public void deleteByUserName(String userName) {
        landlordRepository.deleteByUserName(userName);
    }

    /**
     * Check existence of a landlord by their userName.
     *
     * @param userName Username to check.
     * @return true if landlord exists, false otherwise.
     */
    public boolean existsByUserName(String userName) {
        return landlordRepository.existsByUserName(userName);
    }

    /**
     * Authenticate and retrieve a landlord using userName and password.
     *
     * @param userName Username of the landlord.
     * @param userPassword Password of the landlord.
     * @return Landlord entity if authenticated.
     */
    public Landlord findByUserNameAndUserPassword(String userName, String userPassword) {
        return landlordRepository.findByUserNameAndUserPassword(userName, userPassword);
    }
}