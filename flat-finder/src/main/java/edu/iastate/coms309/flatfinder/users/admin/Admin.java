package edu.iastate.coms309.flatfinder.users.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import edu.iastate.coms309.flatfinder.users.user.User;

/**
 * Represents an Admin entity in the system, which extends the base User entity.
 */
@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin extends User {

    /**
     * Permissions associated with this admin.
     */
    private String adminPermissions;
}