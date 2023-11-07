package edu.iastate.coms309.flatfinder.reviews;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import edu.iastate.coms309.flatfinder.users.user.User;
import edu.iastate.coms309.flatfinder.listing.Listing;

import java.util.Date;

/**
 * Represents a review entity in the system.
 * A review can be associated with a user or a listing.
 */
@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    /**
     * The user who created the review.
     */
    @ManyToOne
    private User reviewer;

    /**
     * The user being reviewed.
     */
    @ManyToOne
    private User reviewedUser;

    /**
     * The listing being reviewed.
     */
    @ManyToOne
    private Listing reviewedListing;

    /**
     * Content of the review.
     */
    private String content;

    /**
     * Rating given in the review.
     */
    private Integer rating;

    /**
     * Date when the review was created.
     */
    private Date dateCreated;
}