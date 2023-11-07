package edu.iastate.coms309.flatfinder.users.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * REST Controller to manage Admin entities.
 */
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Get a list of all admins.
     *
     * @return List of Admin entities.
     */
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }

    /**
     * Get an admin by userName.
     *
     * @param userName The userName of the Admin.
     * @return Admin entity if found.
     */
    @GetMapping("/{userName}")
    public ResponseEntity<Admin> getAdminByUserName(@PathVariable String userName) {
        return adminService.findByUserName(userName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Authenticate an admin by userName and userPassword.
     *
     * @param userName The userName of the Admin.
     * @param userPassword The userPassword of the Admin.
     * @return Admin entity if authenticated.
     */
    @GetMapping("/{userName}/{userPassword}")
    public ResponseEntity<Admin> getAdminByUserNameAndUserPassword(@PathVariable String userName, @PathVariable String userPassword) {
        Admin admin = adminService.findByUserNameAndUserPassword(userName, userPassword);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new admin.
     *
     * @param admin The Admin entity to be created.
     * @return The created Admin entity.
     */
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.save(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    /**
     * Update an existing admin.
     *
     * @param userName The userName of the Admin to be updated.
     * @param updatedAdmin The updated Admin entity.
     * @return The updated Admin entity.
     */
    @PutMapping("/{userName}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable String userName, @RequestBody Admin updatedAdmin) {
        if (!adminService.existsByUserName(userName)) {
            return ResponseEntity.notFound().build();
        }

        updatedAdmin.setUserName(userName); // Assuming userName is the primary key and is immutable
        return ResponseEntity.ok(adminService.save(updatedAdmin));
    }

    /**
     * Update adminPermissions of an existing admin.
     *
     * @param userName The userName of the Admin.
     * @param adminPermissions The new adminPermissions value.
     * @return The updated Admin entity.
     */
    @PutMapping("/{userName}/adminPermissions")
    public ResponseEntity<Admin> updateAdminPermissions(@PathVariable String userName, @RequestParam String adminPermissions) {
        return updateAdminField(userName, admin -> admin.setAdminPermissions(adminPermissions));
    }

    /**
     * Delete an admin by userName.
     *
     * @param userName The userName of the Admin to be deleted.
     * @return ResponseEntity indicating the result of the delete operation.
     */
    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String userName) {
        if (!adminService.existsByUserName(userName)) {
            return ResponseEntity.notFound().build();
        }

        adminService.deleteByUserName(userName);
        return ResponseEntity.noContent().build();
    }

    /**
     * Helper method to update fields of an Admin entity.
     *
     * @param userName The userName of the Admin to be updated.
     * @param fieldUpdater A function that updates the desired field of the Admin.
     * @return The updated Admin entity or notFound status if Admin doesn't exist.
     */
    private ResponseEntity<Admin> updateAdminField(String userName, java.util.function.Consumer<Admin> fieldUpdater) {
        return adminService.findByUserName(userName)
                .map(admin -> {
                    fieldUpdater.accept(admin);
                    adminService.save(admin);
                    return ResponseEntity.ok(admin);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}