package edu.iastate.coms309.flatfinder.images;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a listing image in the application.
 */
@Entity
@Getter
@Setter
public class ListingImage {

    /**
     * The ID of the listing image.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    /**
     * The actual data/content of the listing image.
     */
    @Lob
    private byte[] imageData;
}