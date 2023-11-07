package edu.iastate.coms309.flatfinder.listing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import edu.iastate.coms309.flatfinder.users.landlord.Landlord;
import edu.iastate.coms309.flatfinder.images.ListingImage;

import java.util.List;

/**
 * Represents a property listing in the application.
 */
@Entity
@Getter
@Setter
public class Listing
{
    /**
     * The unique ID of the listing.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listingId;

    /**
     * The name/title of the listing.
     */
    private String listingName;

    /**
     * The landlord who owns the listing.
     */
    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private Landlord listingOwner;

    /**
     * The address of the listed property.
     */
    private String listingAddress;

    /**
     * The price of the listed property.
     */
    private float listingPrice;

    /**
     * The status of the listing (e.g., available, rented).
     */
    private String listingStatus;

    /**
     * The amenities provided with the listed property.
     */
    private String listingAmenities;

    /**
     * The pet preference for the listed property (e.g., pets allowed, no pets).
     */
    private String listingPetPreference;

    /**
     * The number of bedrooms in the listed property.
     */
    private int listingBedrooms;

    /**
     * The number of bathrooms in the listed property.
     */
    private int listingBathrooms;

    /**
     * The number of likes or favorites the listing has received.
     */
    private int listingLikes;

    /**
     * The image associated with the listing.
     */
    @OneToOne
    private ListingImage listingImage;

    /**
     * The list of applications associated with this listing.
     */
    @OneToMany(mappedBy = "listing")
    private List<ListingApplication> applications;
}