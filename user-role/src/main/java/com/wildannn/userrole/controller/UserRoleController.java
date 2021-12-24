package com.wildannn.userrole.controller;

import com.wildannn.userrole.model.UserRole;
import com.wildannn.userrole.payload.InternalErr;
import com.wildannn.userrole.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-role")
public class UserRoleController {

    @Autowired
    private final UserRoleService userRoleService;

    @PostMapping
    public ResponseEntity<?> createUserRole(@RequestBody UserRole userRole) {
        UserRole newUserRole = userRoleService.create(userRole);
        return ResponseEntity.ok(newUserRole);
    }

    @GetMapping
    public ResponseEntity<?> listUserRole() {
        List<UserRole> userRoleList = userRoleService.findAll();
        return ResponseEntity.ok(userRoleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserRole(@PathVariable("id") String id) {
        try {
            UserRole userRole = userRoleService.findById(id);
            return ResponseEntity.ok(userRole);
        } catch (Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUserRole(@PathVariable("id") String id,@RequestBody UserRole userRole) {
        try {
            UserRole updated = userRoleService.update(id, userRole);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserRole(@PathVariable("id") String id) {
        try {
            userRoleService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
