package edu.iastate.coms309.flatfinder.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to the Review entity.
 */
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * Constructor-based dependency injection for the ReviewRepository.
     *
     * @param reviewRepository the review repository
     */
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Retrieves a review by its ID.
     *
     * @param id the ID of the review
     * @return an Optional containing the review if found, or empty otherwise
     */
    public Optional<Review> findById(Integer id) {
        return reviewRepository.findById(id);
    }

    /**
     * Saves a review entity.
     *
     * @param review the review entity to save
     * @return the saved review entity
     */
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Deletes a review by its ID.
     *
     * @param id the ID of the review to delete
     */
    public void deleteById(Integer id) {
        reviewRepository.deleteById(id);
    }

    /**
     * Checks if a review exists by its ID.
     *
     * @param id the ID of the review
     * @return true if the review exists, false otherwise
     */
    public boolean existsById(Integer id) {
        return reviewRepository.existsById(id);
    }

    /**
     * Retrieves all reviews.
     *
     * @return a list of all reviews
     */
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}