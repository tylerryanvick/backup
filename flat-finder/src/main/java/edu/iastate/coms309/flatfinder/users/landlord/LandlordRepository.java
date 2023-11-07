package edu.iastate.coms309.flatfinder.users.landlord;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for managing CRUD operations related to the Landlord entity.
 */
public interface LandlordRepository extends JpaRepository<Landlord, Integer> {

    /**
     * Retrieves a landlord based on their unique ID.
     *
     * @param id Unique identifier of the landlord.
     * @return Optional containing the landlord if found, empty otherwise.
     */
    Optional<Landlord> findById(Integer id);

    /**
     * Deletes a landlord based on their unique ID.
     *
     * @param id Unique identifier of the landlord to be deleted.
     */
    void deleteById(Integer id);

    /**
     * Checks if a landlord exists based on their unique ID.
     *
     * @param id Unique identifier of the landlord.
     * @return true if the landlord exists, false otherwise.
     */
    boolean existsById(Integer id);

    /**
     * Retrieves a landlord based on their username.
     *
     * @param userName Username of the landlord.
     * @return Optional containing the landlord if found, empty otherwise.
     */
    Optional<Landlord> findByUserName(String userName);

    /**
     * Deletes a landlord based on their username.
     *
     * @param userName Username of the landlord to be deleted.
     */
    void deleteByUserName(String userName);

    /**
     * Checks if a landlord exists based on their username.
     *
     * @param userName Username of the landlord.
     * @return true if the landlord exists, false otherwise.
     */
    boolean existsByUserName(String userName);

    /**
     * Authenticates and retrieves a landlord based on their username and password.
     *
     * @param userName Username of the landlord.
     * @param userPassword Password of the landlord.
     * @return Landlord entity if authentication is successful, null otherwise.
     */
    Landlord findByUserNameAndUserPassword(String userName, String userPassword);
}