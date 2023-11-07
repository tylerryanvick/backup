package edu.iastate.coms309.flatfinder.history;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import edu.iastate.coms309.flatfinder.users.tenant.Tenant;
import edu.iastate.coms309.flatfinder.users.landlord.Landlord;
import edu.iastate.coms309.flatfinder.listing.Listing;

/**
 * Represents a history of user activity.
 */
@Entity
@Getter
@Setter
public class History
{
    /**
     * Represents the ID of the history.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    /**
     * Represents the Tenant of the history.
     */
    @ManyToOne
    private Tenant tenant;

    /**
     * Represents the Landlord of the history.
     */
    @ManyToOne
    private Landlord landlord;

    /**
     * Represents the Listing of the history.
     */
    @ManyToOne
    private Listing listing;

    /**
     * Represents the type of the history.
     */
    private String historyType;  // E.g., "Listing", "Roommate", "Landlord"

    /**
     * Represents the description of the history.
     */
    private String description;
}