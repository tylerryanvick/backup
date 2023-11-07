package edu.iastate.coms309.flatfinder.matches;

import edu.iastate.coms309.flatfinder.listing.Listing;
import edu.iastate.coms309.flatfinder.users.tenant.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing matches.
 */
@RestController
@RequestMapping("/matches")
public class MatchesController {

    @Autowired
    private MatchesService matchService;

    /**
     * Gets all matches.
     *
     * @return a list of matches.
     */
    @GetMapping
    public List<Matches> getAllMatches() {
        return matchService.findAll();
    }

    /**
     * Gets a specific match by its ID.
     *
     * @param id the ID of the match.
     * @return the match or a not found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Matches> getMatchById(@PathVariable Integer id) {
        return matchService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new match.
     *
     * @param match the match to create.
     * @return the created match.
     */
    @PostMapping
    public ResponseEntity<Matches> createMatch(@RequestBody Matches match) {
        Matches savedMatch = matchService.save(match);
        return ResponseEntity.ok(savedMatch);
    }

    /**
     * Updates a specific match.
     *
     * @param id            the ID of the match.
     * @param updatedMatch the updated match data.
     * @return the updated match or a not found response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Matches> updateMatch(@PathVariable Integer id, @RequestBody Matches updatedMatch) {
        if (!matchService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedMatch.setMatchId(id);
        return ResponseEntity.ok(matchService.save(updatedMatch));
    }

    /**
     * Updates the tenant of a match.
     *
     * @param id     the ID of the match.
     * @param tenant the updated tenant.
     * @return the updated match or a not found response.
     */
    @PutMapping("/{id}/tenant")
    public ResponseEntity<Matches> updateTenant(@PathVariable Integer id, @RequestBody Tenant tenant) {
        return updateMatchField(id, match -> match.setTenant(tenant));
    }

    /**
     * Updates the listing of a match.
     *
     * @param id      the ID of the match.
     * @param listing the updated listing.
     * @return the updated match or a not found response.
     */
    @PutMapping("/{id}/listing")
    public ResponseEntity<Matches> updateListing(@PathVariable Integer id, @RequestBody Listing listing) {
        return updateMatchField(id, match -> match.setListing(listing));
    }

    /**
     * Updates the status of a match.
     *
     * @param id          the ID of the match.
     * @param matchStatus the updated status.
     * @return the updated match or a not found response.
     */
    @PutMapping("/{id}/matchStatus")
    public ResponseEntity<Matches> updateMatchStatus(@PathVariable Integer id, @RequestParam String matchStatus) {
        return updateMatchField(id, match -> match.setMatchStatus(matchStatus));
    }

    /**
     * Deletes a specific match.
     *
     * @param id the ID of the match.
     * @return a no content response or a not found response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Integer id) {
        if (!matchService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        matchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * A helper method to update fields of a match.
     *
     * @param id          the ID of the match.
     * @param fieldUpdater a function that updates a specific field of a match.
     * @return the updated match or a not found response.
     */
    private ResponseEntity<Matches> updateMatchField(Integer id, java.util.function.Consumer<Matches> fieldUpdater) {
        return matchService.findById(id)
                .map(match -> {
                    fieldUpdater.accept(match);
                    matchService.save(match);
                    return ResponseEntity.ok(match);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}