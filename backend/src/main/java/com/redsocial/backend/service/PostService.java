package com.redsocial.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redsocial.backend.dto.PostRequest;
import com.redsocial.backend.dto.PostResponse;
import com.redsocial.backend.model.Like;
import com.redsocial.backend.model.Post;
import com.redsocial.backend.model.User;
import com.redsocial.backend.repository.LikeRepository;
import com.redsocial.backend.repository.PostRepository;
import com.redsocial.backend.repository.UserRepository;

@Service
@Transactional
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LikeRepository likeRepository;
    
    public PostResponse createPost(PostRequest postRequest, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        
        Post post = new Post(postRequest.getContent(), user);
        if (postRequest.getImageUrl() != null) {
            post.setImageUrl(postRequest.getImageUrl());
        }
        
        Post savedPost = postRepository.save(post);
        return new PostResponse(savedPost, false);
    }
    
    public List<PostResponse> getAllPosts(String currentUsername) {
        List<Post> posts = postRepository.findAllOrderByCreatedAtDesc();
        
        User currentUser = null;
        if (currentUsername != null) {
            currentUser = userRepository.findByUsername(currentUsername).orElse(null);
        }
        
        final User finalCurrentUser = currentUser;
        return posts.stream()
                .map(post -> {
                    boolean isLiked = finalCurrentUser != null && 
                            likeRepository.existsByUserAndPost(finalCurrentUser, post);
                    return new PostResponse(post, isLiked);
                })
                .collect(Collectors.toList());
    }
    
    public Page<PostResponse> getAllPosts(Pageable pageable, String currentUsername) {
        Page<Post> posts = postRepository.findAllOrderByCreatedAtDesc(pageable);
        
        User currentUser = null;
        if (currentUsername != null) {
            currentUser = userRepository.findByUsername(currentUsername).orElse(null);
        }
        
        final User finalCurrentUser = currentUser;
        return posts.map(post -> {
            boolean isLiked = finalCurrentUser != null && 
                    likeRepository.existsByUserAndPost(finalCurrentUser, post);
            return new PostResponse(post, isLiked);
        });
    }
    
    public List<PostResponse> getPostsByUser(String username, String currentUsername) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        
        List<Post> posts = postRepository.findByUserOrderByCreatedAtDesc(user);
        
        User currentUser = null;
        if (currentUsername != null) {
            currentUser = userRepository.findByUsername(currentUsername).orElse(null);
        }
        
        final User finalCurrentUser = currentUser;
        return posts.stream()
                .map(post -> {
                    boolean isLiked = finalCurrentUser != null && 
                            likeRepository.existsByUserAndPost(finalCurrentUser, post);
                    return new PostResponse(post, isLiked);
                })
                .collect(Collectors.toList());
    }
    
    public PostResponse getPostById(Long id, String currentUsername) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post no encontrado con id: " + id));
        
        User currentUser = null;
        if (currentUsername != null) {
            currentUser = userRepository.findByUsername(currentUsername).orElse(null);
        }
        
        boolean isLiked = currentUser != null && 
                likeRepository.existsByUserAndPost(currentUser, post);
        
        return new PostResponse(post, isLiked);
    }
    
    public PostResponse toggleLike(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado con id: " + postId));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        
        Optional<Like> existingLike = likeRepository.findByUserAndPost(user, post);
        
        if (existingLike.isPresent()) {
            // Si ya existe el like, lo eliminamos
            likeRepository.delete(existingLike.get());
            return new PostResponse(postRepository.findById(postId).get(), false);
        } else {
            // Si no existe, creamos un nuevo like
            Like like = new Like(user, post);
            likeRepository.save(like);
            return new PostResponse(postRepository.findById(postId).get(), true);
        }
    }
    
    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post no encontrado con id: " + id));
        
        if (!post.getUser().getUsername().equals(username)) {
            throw new RuntimeException("No tienes permisos para eliminar este post");
        }
        
        postRepository.delete(post);
    }
}