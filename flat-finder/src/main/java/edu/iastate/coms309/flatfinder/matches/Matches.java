package edu.iastate.coms309.flatfinder.matches;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import edu.iastate.coms309.flatfinder.users.tenant.Tenant;
import edu.iastate.coms309.flatfinder.listing.Listing;

/**
 * Represents a match between a Tenant and a Listing.
 */
@Entity
@Getter
@Setter
public class Matches {

    /**
     * The unique identifier for the match.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchId;

    /**
     * The Tenant associated with the match.
     */
    @ManyToOne
    private Tenant tenant;

    /**
     * The Listing associated with the match.
     */
    @ManyToOne
    private Listing listing;

    /**
     * The status of the match.
     */
    private String matchStatus;
}