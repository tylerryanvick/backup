package edu.iastate.coms309.flatfinder.images;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a profile image in the application.
 */
@Entity
@Getter
@Setter
public class ProfileImage {

    /**
     * The ID of the profile image.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    /**
     * The actual data/content of the profile image.
     */
    @Lob
    private byte[] imageData;
}