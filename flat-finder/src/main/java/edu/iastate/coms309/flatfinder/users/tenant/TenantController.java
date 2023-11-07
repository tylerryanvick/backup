package edu.iastate.coms309.flatfinder.users.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RestController for handling tenant-related requests.
 */
@RestController
@RequestMapping("/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    /**
     * Retrieve all tenants in the system.
     *
     * @return List of all tenants.
     */
    @GetMapping("/tenants")
    public List<Tenant> getAllTenants() {
        return tenantService.findAll();
    }

    /**
     * Get matching tenants based on the provided name and password.
     *
     * @param name     The tenant's name.
     * @param password The tenant's password.
     * @return List of matching tenants.
     */
    @GetMapping("/{name}/{password}")
    public List<Tenant> getTenantMatches(@PathVariable String name, @PathVariable String password) {
        Tenant tenant = getTenant(name, password);
        return getMatches(tenant);
    }

    /**
     * Find a tenant based on their name and password.
     *
     * @param name     The tenant's name.
     * @param password The tenant's password.
     * @return Matching tenant or null if not found.
     */
    private Tenant getTenant(String name, String password) {
        List<Tenant> tenantList = tenantService.findAll();
        return tenantList.stream()
                .filter(tenant -> tenant.getUserName().equals(name) && tenant.getUserPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieve tenants that match the provided tenant's preferences.
     *
     * @param tenant The reference tenant.
     * @return List of tenants that match the provided tenant's preferences.
     */
    private List<Tenant> getMatches(Tenant tenant) {
        List<Tenant> tenantList = tenantService.findAll();
        List<Tenant> tenantMatches = new ArrayList<>();
        tenantMatches.add(tenant);

        for (Tenant tenantMatch : tenantList) {
            if (tenantMatch.getUserName().equals(tenant.getUserName()) && tenantMatch.getUserPassword().equals(tenant.getUserPassword())) {
                continue;
            }

            int matchScore = 0;
            if (tenantMatch.getTenantBudget().equals(tenant.getTenantBudget())) {
                matchScore++;
            }
            if (tenantMatch.getTenantPetPreference().equals(tenant.getTenantPetPreference())) {
                matchScore++;
            }
            if (tenantMatch.getTenantBedroomPreference().equals(tenant.getTenantBedroomPreference())) {
                matchScore++;
            }
            if (tenantMatch.getTenantBathroomPreference().equals(tenant.getTenantBathroomPreference())) {
                matchScore++;
            }

            if (matchScore >= 3) {
                tenantMatches.add(tenantMatch);
            }
            if (tenantMatches.size() >= 10) {
                break;
            }
        }

        return tenantMatches;
    }

    /**
     * Create a new tenant.
     *
     * @param tenant The tenant to be created.
     * @return ResponseEntity containing the created tenant.
     */
    @PostMapping
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant) {
        tenantService.save(tenant);
        return ResponseEntity.ok(tenant);
    }

    /**
     * Update tenant details based on the provided name and password.
     *
     * @param newTenant The updated tenant details.
     * @param name      The tenant's name.
     * @param password  The tenant's password.
     * @return Updated tenant.
     */
    @PutMapping("/{name}/{password}")
    public Tenant updateTenant(@RequestBody Tenant newTenant, @PathVariable String name, @PathVariable String password) {
        Tenant oldTenant = getTenant(name, password);
        newTenant.setUserId(oldTenant.getUserId());
        return tenantService.save(newTenant);
    }

    /**
     * Delete a tenant based on the provided name and password.
     *
     * @param name     The tenant's name.
     * @param password The tenant's password.
     * @return ResponseEntity indicating the deletion status.
     */
    @DeleteMapping("/{name}/{password}")
    public ResponseEntity<Void> deleteTenant(@PathVariable String name, @PathVariable String password) {
        Tenant tenant = getTenant(name, password);
        tenantService.deleteById(tenant.getUserId());
        return ResponseEntity.noContent().build();
    }
}