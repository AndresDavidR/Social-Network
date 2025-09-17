package com.redsocial.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.backend.dto.MessageResponse;
import com.redsocial.backend.dto.UserResponse;
import com.redsocial.backend.model.User;
import com.redsocial.backend.repository.UserRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints for user profile management")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return user.getUsername();
        }
        return null;
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found: " + username));
            
            UserResponse userResponse = new UserResponse(user);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        try {
            String username = getCurrentUsername();
            if (username == null) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("User not authenticated"));
            }
            
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found: " + username));
            
            UserResponse userResponse = new UserResponse(user);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }
    
    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(@RequestBody UserResponse updateRequest) {
        try {
            String username = getCurrentUsername();
            if (username == null) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("User not authenticated"));
            }
            
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found: " + username));
            
            // Actualizar solo los campos permitidos
            if (updateRequest.getFirstName() != null) {
                user.setFirstName(updateRequest.getFirstName());
            }
            if (updateRequest.getLastName() != null) {
                user.setLastName(updateRequest.getLastName());
            }
            if (updateRequest.getBio() != null) {
                user.setBio(updateRequest.getBio());
            }
            if (updateRequest.getProfileImageUrl() != null) {
                user.setProfileImageUrl(updateRequest.getProfileImageUrl());
            }
            
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(new UserResponse(updatedUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error al actualizar perfil: " + e.getMessage()));
        }
    }
}
