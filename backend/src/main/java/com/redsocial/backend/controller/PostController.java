package com.redsocial.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redsocial.backend.dto.MessageResponse;
import com.redsocial.backend.dto.PostRequest;
import com.redsocial.backend.dto.PostResponse;
import com.redsocial.backend.model.User;
import com.redsocial.backend.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
@Tag(name = "Posts", description = "Endpoints for post management (CRUD operations, likes)")
@SecurityRequirement(name = "Bearer Authentication")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private WebSocketController webSocketController;
    
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return user.getUsername();
        }
        return null;
    }
    
    @PostMapping
    @Operation(summary = "Create post", description = "Create a new post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid data or user not authenticated"),
        @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token")
    })
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest postRequest) {
        try {
            String username = getCurrentUsername();
            if (username == null) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("User not authenticated"));
            }
            
            PostResponse post = postService.createPost(postRequest, username);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error creating post: " + e.getMessage()));
        }
    }
    
    @GetMapping
    @Operation(summary = "Get all posts", description = "List all posts ordered by date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posts list retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token")
    })
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        String currentUsername = getCurrentUsername(); // Obtener usuario autenticado
        List<PostResponse> posts = postService.getAllPosts(currentUsername);
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/paginated")
    public ResponseEntity<Page<PostResponse>> getAllPostsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        String currentUsername = getCurrentUsername(); // Obtener usuario autenticado
        Pageable pageable = PageRequest.of(page, size);
        Page<PostResponse> posts = postService.getAllPosts(pageable, currentUsername);
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        try {
            String currentUsername = getCurrentUsername(); // Obtener usuario autenticado
            PostResponse post = postService.getPostById(id, currentUsername);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUser(@PathVariable String username) {
        String currentUsername = getCurrentUsername(); // Obtener usuario autenticado
        List<PostResponse> posts = postService.getPostsByUser(username, currentUsername);
        return ResponseEntity.ok(posts);
    }
    
    @PostMapping("/{id}/like")
    @Operation(summary = "Toggle post like", description = "Like or unlike a post (sends real-time WebSocket events)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Like updated successfully"),
        @ApiResponse(responseCode = "400", description = "Error processing like or user not authenticated"),
        @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token"),
        @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public ResponseEntity<?> toggleLike(@PathVariable Long id) {
        try {
            String username = getCurrentUsername();
            if (username == null) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("User not authenticated"));
            }
            
            PostResponse post = postService.toggleLike(id, username);
            
            // Enviar evento WebSocket para actualización en tiempo real
            webSocketController.sendLikeUpdate(id, post.getLikeCount(), post.isLikedByCurrentUser());
            
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error al procesar like: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        try {
            String username = getCurrentUsername();
            if (username == null) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("User not authenticated"));
            }
            
            postService.deletePost(id, username);
            return ResponseEntity.ok(new MessageResponse("Post deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error al eliminar post: " + e.getMessage()));
        }
    }
}
