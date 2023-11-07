package edu.iastate.coms309.flatfinder.history;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for CRUD operations on History.
 */
public interface HistoryRepository extends JpaRepository<History, Integer> {
}