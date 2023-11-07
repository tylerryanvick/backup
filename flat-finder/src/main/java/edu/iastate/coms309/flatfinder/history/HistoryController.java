package edu.iastate.coms309.flatfinder.history;

import edu.iastate.coms309.flatfinder.users.tenant.Tenant;
import edu.iastate.coms309.flatfinder.users.landlord.Landlord;
import edu.iastate.coms309.flatfinder.listing.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Controller class for handling history-related operations.
 */
@RestController
@RequestMapping("/histories")
public class HistoryController
{
    /**
     * The service used for history operations.
     */
    @Autowired
    private HistoryService historyService;

    /**
     * Retrieves all histories.
     * @return List of all histories.
     */
    @GetMapping
    public List<History> getAllHistories()
    {
        return historyService.findAll();
    }

    /**
     * Retrieves a history by its ID.
     * @param id The ID of the history to retrieve.
     * @return ResponseEntity containing the found history or an error if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<History> getHistoryById(@PathVariable Integer id)
    {
        return historyService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new history entry.
     * @param history The history to be created.
     * @return ResponseEntity containing the created history.
     */
    @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody History history)
    {
        History savedHistory = historyService.save(history);
        return ResponseEntity.ok(savedHistory);
    }

    /**
     * Updates an existing history.
     * @param id The ID of the history to update.
     * @param history The updated history details.
     * @return ResponseEntity containing the updated history or an error if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<History> updateHistory(@PathVariable Integer id, @RequestBody History history)
    {
        return historyService.findById(id)
                .map(existingHistory -> {
                    existingHistory.setTenant(history.getTenant());
                    existingHistory.setLandlord(history.getLandlord());
                    existingHistory.setListing(history.getListing());
                    existingHistory.setHistoryType(history.getHistoryType());
                    existingHistory.setDescription(history.getDescription());
                    History updatedHistory = historyService.save(existingHistory);
                    return ResponseEntity.ok(updatedHistory);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a history by its ID.
     * @param id The ID of the history to delete.
     * @return ResponseEntity indicating the result of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Integer id)
    {
        historyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}