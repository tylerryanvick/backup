package edu.iastate.coms309.flatfinder.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for CRUD operations related to the Review entity.
 */
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}