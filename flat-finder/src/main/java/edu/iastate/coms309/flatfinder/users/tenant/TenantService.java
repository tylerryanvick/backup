package edu.iastate.coms309.flatfinder.users.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Service class handling business logic related to Tenant operations.
 */
@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    /**
     * Save a tenant entity to the database.
     *
     * @param tenant Tenant entity to be saved.
     * @return The saved tenant entity.
     */
    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    /**
     * Retrieve all tenant entities from the database.
     *
     * @return List of all tenants.
     */
    public List<Tenant> findAll() {
        return tenantRepository.findAll();
    }

    /**
     * Retrieve a tenant entity by its ID.
     *
     * @param id ID of the tenant.
     * @return An Optional containing the tenant entity if found.
     */
    public Optional<Tenant> findById(Integer id) {
        return tenantRepository.findById(id);
    }

    /**
     * Delete a tenant entity by its ID.
     *
     * @param id ID of the tenant to be deleted.
     */
    public void deleteById(Integer id) {
        tenantRepository.deleteById(id);
    }

    /**
     * Check if a tenant entity exists by its ID.
     *
     * @param id ID of the tenant.
     * @return true if the tenant exists, false otherwise.
     */
    public boolean existsById(Integer id) {
        return tenantRepository.existsById(id);
    }
}