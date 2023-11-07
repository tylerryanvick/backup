package edu.iastate.coms309.flatfinder.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling history-related business logic.
 */
@Service
public class HistoryService {

    /**
     * The repository used for history database operations.
     */
    @Autowired
    private HistoryRepository historyRepository;

    /**
     * Retrieves all histories.
     * @return List of all histories.
     */
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    /**
     * Retrieves a history by its ID.
     * @param id The ID of the history to retrieve.
     * @return Optional containing the found history or empty if not found.
     */
    public Optional<History> findById(Integer id) {
        return historyRepository.findById(id);
    }

    /**
     * Saves or updates a history.
     * @param history The history to save or update.
     * @return The saved or updated history.
     */
    public History save(History history) {
        return historyRepository.save(history);
    }

    /**
     * Deletes a history by its ID.
     * @param id The ID of the history to delete.
     */
    public void deleteById(Integer id) {
        historyRepository.deleteById(id);
    }
}