package edu.iastate.coms309.flatfinder.listing;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing CRUD operations for listings.
 */
public interface ListingRepository extends JpaRepository<Listing, Integer> {
}