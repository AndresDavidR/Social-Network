package com.redsocial.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostRequest {
    
    @NotBlank(message = "El contenido del post es obligatorio")
    @Size(max = 500, message = "El contenido no puede exceder 500 caracteres")
    private String content;
    
    private String imageUrl;
    
    public PostRequest() {}
    
    public PostRequest(String content, String imageUrl) {
        this.content = content;
        this.imageUrl = imageUrl;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}