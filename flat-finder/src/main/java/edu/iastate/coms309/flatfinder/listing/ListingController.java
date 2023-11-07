package edu.iastate.coms309.flatfinder.listing;

import edu.iastate.coms309.flatfinder.users.landlord.Landlord;
import edu.iastate.coms309.flatfinder.images.ListingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Controller to handle listing related requests.
 */
@RestController
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    /**
     * Retrieves all listings.
     *
     * @return a list of all listings.
     */
    @GetMapping
    public List<Listing> getAllListings() {
        return listingService.findAll();
    }

    /**
     * Retrieves a listing by its ID.
     *
     * @param id the ID of the listing.
     * @return the listing with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Integer id) {
        return listingService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new listing.
     *
     * @param listing the listing to be created.
     * @return the created listing.
     */
    @PostMapping
    public ResponseEntity<Listing> createListing(@RequestBody Listing listing) {
        Listing savedListing = listingService.save(listing);
        return ResponseEntity.ok(savedListing);
    }

    /**
     * Updates a listing by its ID.
     *
     * @param id the ID of the listing to be updated.
     * @param updatedListing the new listing data.
     * @return the updated listing.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Integer id, @RequestBody Listing updatedListing) {
        if (!listingService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedListing.setListingId(id);
        return ResponseEntity.ok(listingService.save(updatedListing));
    }

    /**
     * Updates the name of a listing.
     *
     * @param id the ID of the listing.
     * @param listingName the new name for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingName")
    public ResponseEntity<Listing> updateListingName(@PathVariable Integer id, @RequestParam String listingName) {
        return updateListingField(id, listing -> listing.setListingName(listingName));
    }

    /**
     * Updates the owner of a listing.
     *
     * @param id the ID of the listing.
     * @param listingOwner the new owner for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingOwner")
    public ResponseEntity<Listing> updateListingOwner(@PathVariable Integer id, @RequestBody Landlord listingOwner) {
        return updateListingField(id, listing -> listing.setListingOwner(listingOwner));
    }

    /**
     * Updates the address of a listing.
     *
     * @param id the ID of the listing.
     * @param listingAddress the new address for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingAddress")
    public ResponseEntity<Listing> updateListingAddress(@PathVariable Integer id, @RequestParam String listingAddress) {
        return updateListingField(id, listing -> listing.setListingAddress(listingAddress));
    }

    /**
     * Updates the price of a listing.
     *
     * @param id the ID of the listing.
     * @param listingPrice the new price for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingPrice")
    public ResponseEntity<Listing> updateListingPrice(@PathVariable Integer id, @RequestParam float listingPrice) {
        return updateListingField(id, listing -> listing.setListingPrice(listingPrice));
    }

    /**
     * Updates the status of a listing.
     *
     * @param id the ID of the listing.
     * @param listingStatus the new status for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingStatus")
    public ResponseEntity<Listing> updateListingStatus(@PathVariable Integer id, @RequestParam String listingStatus) {
        return updateListingField(id, listing -> listing.setListingStatus(listingStatus));
    }

    /**
     * Updates the amenities of a listing.
     *
     * @param id the ID of the listing.
     * @param listingAmenities the new amenities for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingAmenities")
    public ResponseEntity<Listing> updateListingAmenities(@PathVariable Integer id, @RequestParam String listingAmenities) {
        return updateListingField(id, listing -> listing.setListingAmenities(listingAmenities));
    }

    /**
     * Updates the pet preference of a listing.
     *
     * @param id the ID of the listing.
     * @param listingPetPreference the new pet preference for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingPetPreference")
    public ResponseEntity<Listing> updateListingPetPreference(@PathVariable Integer id, @RequestParam String listingPetPreference) {
        return updateListingField(id, listing -> listing.setListingPetPreference(listingPetPreference));
    }

    /**
     * Updates the number of bedrooms in a listing.
     *
     * @param id the ID of the listing.
     * @param listingBedrooms the new number of bedrooms for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingBedrooms")
    public ResponseEntity<Listing> updateListingBedrooms(@PathVariable Integer id, @RequestParam int listingBedrooms) {
        return updateListingField(id, listing -> listing.setListingBedrooms(listingBedrooms));
    }

    /**
     * Updates the number of bathrooms in a listing.
     *
     * @param id the ID of the listing.
     * @param listingBathrooms the new number of bathrooms for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingBathrooms")
    public ResponseEntity<Listing> updateListingBathrooms(@PathVariable Integer id, @RequestParam int listingBathrooms) {
        return updateListingField(id, listing -> listing.setListingBathrooms(listingBathrooms));
    }

    /**
     * Updates the image of a listing.
     *
     * @param id the ID of the listing.
     * @param listingImage the new image for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/listingImage")
    public ResponseEntity<Listing> updateListingImage(@PathVariable Integer id, @RequestBody ListingImage listingImage) {
        return updateListingField(id, listing -> listing.setListingImage(listingImage));
    }

    /**
     * Updates the applications associated with a listing.
     *
     * @param id the ID of the listing.
     * @param applications the new applications for the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/applications")
    public ResponseEntity<Listing> updateApplications(@PathVariable Integer id, @RequestBody List<ListingApplication> applications) {
        return updateListingField(id, listing -> listing.setApplications(applications));
    }

    /**
     * Increments the number of likes for a listing.
     *
     * @param id the ID of the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/incrementLikes")
    public ResponseEntity<Listing> incrementLikes(@PathVariable Integer id) {
        return updateListingField(id, listing -> listing.setListingLikes(listing.getListingLikes() + 1));
    }

    /**
     * Decrements the number of likes for a listing if greater than 0.
     *
     * @param id the ID of the listing.
     * @return the updated listing.
     */
    @PutMapping("/{id}/decrementLikes")
    public ResponseEntity<Listing> decrementLikes(@PathVariable Integer id) {
        return updateListingField(id, listing -> {
            if(listing.getListingLikes() > 0) {
                listing.setListingLikes(listing.getListingLikes() - 1);
            }
        });
    }

    /**
     * Deletes a listing by its ID.
     *
     * @param id the ID of the listing to delete.
     * @return a response indicating the result of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Integer id) {
        if (!listingService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        listingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates a specific field in a listing.
     *
     * @param id the ID of the listing.
     * @param fieldUpdater the function to update the specific field.
     * @return the updated listing.
     */
    private ResponseEntity<Listing> updateListingField(Integer id, java.util.function.Consumer<Listing> fieldUpdater) {
        return listingService.findById(id)
                .map(listing -> {
                    fieldUpdater.accept(listing);
                    listingService.save(listing);
                    return ResponseEntity.ok(listing);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}