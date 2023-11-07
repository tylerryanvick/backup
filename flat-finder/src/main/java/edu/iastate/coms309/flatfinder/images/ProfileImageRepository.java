package edu.iastate.coms309.flatfinder.images;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for CRUD operations on ProfileImage.
 */
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Integer> {
}