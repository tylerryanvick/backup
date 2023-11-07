package edu.iastate.coms309.flatfinder.users.landlord;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import edu.iastate.coms309.flatfinder.users.user.User;
import edu.iastate.coms309.flatfinder.reviews.Review;
import edu.iastate.coms309.flatfinder.listing.Listing;
import java.util.List;

/**
 * Entity representing a landlord user.
 */
@Entity
@Table(name = "landlord")
@Getter
@Setter
public class Landlord extends User {

    /**
     * Listings created by the landlord.
     */
    @OneToMany(mappedBy = "listingOwner", cascade = CascadeType.ALL)
    private List<Listing> listings;

    /**
     * Reviews received by this landlord.
     */
    @OneToMany(mappedBy = "reviewedUser")
    private List<Review> receivedReviews;
}