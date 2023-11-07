package edu.iastate.coms309.flatfinder.listing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class to manage the business logic for listings.
 */
@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    /**
     * Finds a listing by its ID.
     *
     * @param id the ID of the listing to find.
     * @return an Optional containing the found listing or empty if not found.
     */
    public Optional<Listing> findById(Integer id) {
        return listingRepository.findById(id);
    }

    /**
     * Retrieves all the listings.
     *
     * @return a list of all listings.
     */
    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    /**
     * Saves or updates a listing.
     *
     * @param listing the listing to save or update.
     * @return the saved or updated listing.
     */
    public Listing save(Listing listing) {
        return listingRepository.save(listing);
    }

    /**
     * Deletes a listing by its ID.
     *
     * @param id the ID of the listing to delete.
     */
    public void deleteById(Integer id) {
        listingRepository.deleteById(id);
    }

    /**
     * Checks if a listing exists by its ID.
     *
     * @param id the ID of the listing to check.
     * @return true if the listing exists, false otherwise.
     */
    public boolean existsById(Integer id) {
        return listingRepository.existsById(id);
    }
}