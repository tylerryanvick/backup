package edu.iastate.coms309.flatfinder.users.landlord;

import edu.iastate.coms309.flatfinder.listing.Listing;
import edu.iastate.coms309.flatfinder.reviews.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Rest controller for managing CRUD operations related to the Landlord entity.
 */
@RestController
@RequestMapping("/landlords")
public class LandlordController
{
    @Autowired
    private LandlordService landlordService;

    /**
     * Retrieves all landlords.
     *
     * @return List of all landlords.
     */
    @GetMapping
    public List<Landlord> getAllLandlords()
    {
        return landlordService.findAll();
    }

    /**
     * Retrieves a landlord based on their username.
     *
     * @param userName Username of the landlord.
     * @return ResponseEntity containing the landlord if found, Not Found status otherwise.
     */
    @GetMapping("/{userName}")
    public ResponseEntity<Landlord> getLandlordByUserName(@PathVariable String userName)
    {
        return landlordService.findByUserName(userName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Authenticates and retrieves a landlord based on their username and password.
     *
     * @param userName Username of the landlord.
     * @param userPassword Password of the landlord.
     * @return ResponseEntity containing the landlord if authentication is successful, Not Found status otherwise.
     */
    @GetMapping("/{userName}/{userPassword}")
    public ResponseEntity<Landlord> getLandlordByUserNameAndUserPassword(@PathVariable String userName, @PathVariable String userPassword)
    {
        Landlord landlord = landlordService.findByUserNameAndUserPassword(userName, userPassword);
        if (landlord != null)
        {
            return ResponseEntity.ok(landlord);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new landlord.
     *
     * @param landlord Landlord entity to be created.
     * @return ResponseEntity containing the created landlord.
     */
    @PostMapping
    public ResponseEntity<Landlord> createLandlord(@RequestBody Landlord landlord)
    {
        Landlord savedLandlord = landlordService.save(landlord);
        return ResponseEntity.ok(savedLandlord);
    }

    /**
     * Updates a landlord based on their username.
     *
     * @param userName Username of the landlord to be updated.
     * @param updatedLandlord Updated landlord entity.
     * @return ResponseEntity containing the updated landlord or Not Found status if the landlord doesn't exist.
     */
    @PutMapping("/{userName}")
    public ResponseEntity<Landlord> updateLandlord(@PathVariable String userName, @RequestBody Landlord updatedLandlord)
    {
        if (!landlordService.existsByUserName(userName))
        {
            return ResponseEntity.notFound().build();
        }

        updatedLandlord.setUserName(userName);
        return ResponseEntity.ok(landlordService.save(updatedLandlord));
    }

    /**
     * Updates the listings of a landlord.
     *
     * @param userName Username of the landlord whose listings are to be updated.
     * @param listings New list of listings.
     * @return ResponseEntity containing the updated landlord or Not Found status if the landlord doesn't exist.
     */
    @PutMapping("/{userName}/listings")
    public ResponseEntity<Landlord> updateLandlordListings(@PathVariable String userName, @RequestBody List<Listing> listings)
    {
        return updateLandlordField(userName, landlord -> landlord.setListings(listings));
    }

    /**
     * Updates the reviews received by a landlord.
     *
     * @param userName Username of the landlord whose reviews are to be updated.
     * @param receivedReviews New list of reviews.
     * @return ResponseEntity containing the updated landlord or Not Found status if the landlord doesn't exist.
     */
    @PutMapping("/{userName}/receivedReviews")
    public ResponseEntity<Landlord> updateLandlordReceivedReviews(@PathVariable String userName, @RequestBody List<Review> receivedReviews)
    {
        return updateLandlordField(userName, landlord -> landlord.setReceivedReviews(receivedReviews));
    }

    /**
     * Deletes a landlord based on their username.
     *
     * @param userName Username of the landlord to be deleted.
     * @return No Content status if the deletion is successful, Not Found status if the landlord doesn't exist.
     */
    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteLandlord(@PathVariable String userName)
    {
        if (!landlordService.existsByUserName(userName))
        {
            return ResponseEntity.notFound().build();
        }

        landlordService.deleteByUserName(userName);
        return ResponseEntity.noContent().build();
    }

    /**
     * Utility method to update a specific field of a landlord.
     *
     * @param userName Username of the landlord to be updated.
     * @param fieldUpdater Function that describes the field update operation.
     * @return ResponseEntity containing the updated landlord or Not Found status if the landlord doesn't exist.
     */
    private ResponseEntity<Landlord> updateLandlordField(String userName, java.util.function.Consumer<Landlord> fieldUpdater)
    {
        return landlordService.findByUserName(userName)
                .map(landlord -> {
                    fieldUpdater.accept(landlord);
                    landlordService.save(landlord);
                    return ResponseEntity.ok(landlord);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}