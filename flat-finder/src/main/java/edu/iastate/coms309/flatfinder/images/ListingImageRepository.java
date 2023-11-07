package edu.iastate.coms309.flatfinder.images;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for CRUD operations on ListingImage.
 */
public interface ListingImageRepository extends JpaRepository<ListingImage, Integer> {
}