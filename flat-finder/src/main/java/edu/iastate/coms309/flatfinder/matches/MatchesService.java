package edu.iastate.coms309.flatfinder.matches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing matches.
 */
@Service
public class MatchesService {

    @Autowired
    private MatchesRepository matchRepository;

    /**
     * Retrieves a match by its ID.
     *
     * @param id the ID of the match
     * @return an Optional containing the match if found
     */
    public Optional<Matches> findById(Integer id) {
        return matchRepository.findById(id);
    }

    /**
     * Saves a given match.
     *
     * @param match the match to save
     * @return the saved match
     */
    public Matches save(Matches match) {
        return matchRepository.save(match);
    }

    /**
     * Deletes a match by its ID.
     *
     * @param id the ID of the match
     */
    public void deleteById(Integer id) {
        matchRepository.deleteById(id);
    }

    /**
     * Checks if a match exists by its ID.
     *
     * @param id the ID of the match
     * @return true if the match exists, false otherwise
     */
    public boolean existsById(Integer id) {
        return matchRepository.existsById(id);
    }

    /**
     * Retrieves all matches.
     *
     * @return a list of all matches
     */
    public List<Matches> findAll() {
        return matchRepository.findAll();
    }
}