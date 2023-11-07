package edu.iastate.coms309.flatfinder.reviews;

import edu.iastate.coms309.flatfinder.users.user.User;
import edu.iastate.coms309.flatfinder.listing.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Controller class that handles HTTP requests related to reviews.
 */
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Retrieves all reviews.
     * @return A list of all reviews.
     */
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    /**
     * Retrieves a specific review by its ID.
     * @param id The ID of the review.
     * @return The review if found, otherwise a 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        return reviewService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new review.
     * @param review The review to be created.
     * @return The created review.
     */
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review savedReview = reviewService.save(review);
        return ResponseEntity.ok(savedReview);
    }

    /**
     * Updates a specific review.
     * @param id The ID of the review.
     * @param updatedReview The updated review.
     * @return The updated review if found, otherwise a 404 Not Found response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @RequestBody Review updatedReview) {
        if (!reviewService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedReview.setReviewId(id);
        return ResponseEntity.ok(reviewService.save(updatedReview));
    }

    /**
     * Updates the reviewer of a specific review.
     * @param id The ID of the review.
     * @param reviewer The updated reviewer.
     * @return The updated review if found, otherwise a 404 Not Found response.
     */
    @PutMapping("/{id}/reviewer")
    public ResponseEntity<Review> updateReviewer(@PathVariable Integer id, @RequestBody User reviewer) {
        return updateReviewField(id, review -> review.setReviewer(reviewer));
    }

    /**
     * Updates the reviewed user of a specific review.
     * @param id The ID of the review.
     * @param reviewedUser The updated reviewed user.
     * @return The updated review if found, otherwise a 404 Not Found response.
     */
    @PutMapping("/{id}/reviewedUser")
    public ResponseEntity<Review> updateReviewedUser(@PathVariable Integer id, @RequestBody User reviewedUser) {
        return updateReviewField(id, review -> review.setReviewedUser(reviewedUser));
    }

    /**
     * Updates the reviewed listing of a specific review.
     * @param id The ID of the review.
     * @param reviewedListing The updated reviewed listing.
     * @return The updated review if found, otherwise a 404 Not Found response.
     */
    @PutMapping("/{id}/reviewedListing")
    public ResponseEntity<Review> updateReviewedListing(@PathVariable Integer id, @RequestBody Listing reviewedListing) {
        return updateReviewField(id, review -> review.setReviewedListing(reviewedListing));
    }

    /**
     * Updates the content of a specific review.
     * @param id The ID of the review.
     * @param content The updated content.
     * @return The updated review if found, otherwise a 404 Not Found response.
     */
    @PutMapping("/{id}/content")
    public ResponseEntity<Review> updateContent(@PathVariable Integer id, @RequestParam String content) {
        return updateReviewField(id, review -> review.setContent(content));
    }

    /**
     * Updates the rating of a specific review.
     * @param id The ID of the review.
     * @param rating The updated rating.
     * @return The updated review if found, otherwise a 404 Not Found response.
     */
    @PutMapping("/{id}/rating")
    public ResponseEntity<Review> updateRating(@PathVariable Integer id, @RequestParam Integer rating) {
        return updateReviewField(id, review -> review.setRating(rating));
    }

    /**
     * Updates the date created of a specific review.
     * @param id The ID of the review.
     * @param dateCreated The updated date of creation.
     * @return The updated review if found, otherwise a 404 Not Found response.
     */
    @PutMapping("/{id}/dateCreated")
    public ResponseEntity<Review> updateDateCreated(@PathVariable Integer id, @RequestParam Date dateCreated) {
        return updateReviewField(id, review -> review.setDateCreated(dateCreated));
    }

    /**
     * Deletes a specific review by its ID.
     * @param id The ID of the review to delete.
     * @return A 204 No Content response if the review was deleted, otherwise a 404 Not Found response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        if (!reviewService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Helper method to update specific fields of a review.
     * @param id The ID of the review.
     * @param fieldUpdater The function to apply the update to the review.
     * @return The updated review if found, otherwise a 404 Not Found response.
     */
    private ResponseEntity<Review> updateReviewField(Integer id, java.util.function.Consumer<Review> fieldUpdater) {
        return reviewService.findById(id)
                .map(review -> {
                    fieldUpdater.accept(review);
                    reviewService.save(review);
                    return ResponseEntity.ok(review);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}