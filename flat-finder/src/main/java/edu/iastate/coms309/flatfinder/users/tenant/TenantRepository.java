package edu.iastate.coms309.flatfinder.users.tenant;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for database operations related to the Tenant entity.
 */
public interface TenantRepository extends JpaRepository<Tenant, Integer> {
}