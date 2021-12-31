package com.wildannn.user.controller;

import com.wildannn.user.entity.UserRole;
import com.wildannn.user.handler.MessageResponse;
import com.wildannn.user.model.UserRoleModel;
import com.wildannn.user.payload.ErrorResponse;
import com.wildannn.user.payload.ResponseService;
import com.wildannn.user.payload.UserRoleResponse;
import com.wildannn.user.service.UserRoleService;
import com.wildannn.user.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class UserRoleController {

    private final UserRoleService userRoleService;
    private final ResponseService responseService;
    private final ErrorMessageService errorMessageService;

    @PostMapping
    public ResponseEntity<?> createUserRole(@RequestBody UserRole userRole) {
        UserRole role = userRoleService.create(userRole);
        UserRoleModel model = userRoleService.convertToModel(role);
        UserRoleResponse response = responseService
                .makeUserRoleResponse(MessageResponse.CREATED_ROLE, model);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<?> listUserRole() {
        List<UserRole> roles = userRoleService.findAll();
        List<UserRoleModel> modelList = userRoleService.convertToModels(roles);
        UserRoleResponse response = responseService
                .makeUserRolesResponse(MessageResponse.ALL_ROLE, modelList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserRole(@PathVariable("id") String id) {
        try {
            UserRole role = userRoleService.findById(id);
            UserRoleModel model = userRoleService.convertToModel(role);
            UserRoleResponse response = responseService
                    .makeUserRoleResponse(MessageResponse.ONE_ROLE, model);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUserRole(@PathVariable("id") String id,@RequestBody UserRole userRole) {
        try {
            UserRole updated = userRoleService.update(id, userRole);
            UserRoleModel model = userRoleService.convertToModel(updated);
            UserRoleResponse response = responseService
                    .makeUserRoleResponse(MessageResponse.UPDATED_ROLE, model);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserRole(@PathVariable("id") String id) {
        try {
            userRoleService.delete(id);
            UserRoleResponse response = responseService
                    .makeUserRoleResponse(MessageResponse.DELETED_ROLE, null);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}
