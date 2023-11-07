package edu.iastate.coms309.flatfinder.matches;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing matches.
 */
public interface MatchesRepository extends JpaRepository<Matches, Integer> {
}