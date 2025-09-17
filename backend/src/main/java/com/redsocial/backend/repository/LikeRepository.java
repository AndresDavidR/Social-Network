package com.redsocial.backend.repository;

import com.redsocial.backend.model.Like;
import com.redsocial.backend.model.Post;
import com.redsocial.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    
    Optional<Like> findByUserAndPost(User user, Post post);
    
    boolean existsByUserAndPost(User user, Post post);
    
    void deleteByUserAndPost(User user, Post post);
    
    long countByPost(Post post);
}