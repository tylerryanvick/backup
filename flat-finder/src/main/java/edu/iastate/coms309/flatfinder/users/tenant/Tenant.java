package edu.iastate.coms309.flatfinder.users.tenant;

import edu.iastate.coms309.flatfinder.listing.Listing;
import edu.iastate.coms309.flatfinder.matches.Matches;
import edu.iastate.coms309.flatfinder.reviews.Review;
import edu.iastate.coms309.flatfinder.users.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Entity representing a tenant in the system.
 */
@Entity
@Table(name = "tenant")
@Getter
@Setter
public class Tenant extends User {

    /**
     * Budget preference of the tenant.
     */
    private String tenantBudget;

    /**
     * Address preference of the tenant.
     */
    private String tenantAddress;

    /**
     * Pet preference of the tenant.
     */
    private String tenantPetPreference;

    /**
     * Bedroom preference of the tenant.
     */
    private String tenantBedroomPreference;

    /**
     * Bathroom preference of the tenant.
     */
    private String tenantBathroomPreference;

    /**
     * Reviews written by this tenant.
     */
    @OneToMany(mappedBy = "reviewer")
    private List<Review> writtenReviews;

    /**
     * Reviews about this tenant.
     */
    @OneToMany(mappedBy = "reviewedUser")
    private List<Review> receivedReviews;

    /**
     * Listings liked by the tenant.
     */
    @OneToMany
    private List<Listing> likedListings;

    /**
     * Matches associated with the tenant.
     */
    @OneToMany
    private List<Matches> matches;
}