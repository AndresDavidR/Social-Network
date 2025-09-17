package com.redsocial.backend.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendLikeUpdate(Long postId, int likeCount, boolean userHasLiked) {
        LikeUpdateMessage message = new LikeUpdateMessage(postId, likeCount, userHasLiked);
        messagingTemplate.convertAndSend("/topic/likes/" + postId, message);
    }

    public static class LikeUpdateMessage {
        private Long postId;
        private int likeCount;
        private boolean userHasLiked;

        public LikeUpdateMessage(Long postId, int likeCount, boolean userHasLiked) {
            this.postId = postId;
            this.likeCount = likeCount;
            this.userHasLiked = userHasLiked;
        }

        // Getters
        public Long getPostId() {
            return postId;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public boolean isUserHasLiked() {
            return userHasLiked;
        }

        // Setters
        public void setPostId(Long postId) {
            this.postId = postId;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public void setUserHasLiked(boolean userHasLiked) {
            this.userHasLiked = userHasLiked;
        }
    }
}