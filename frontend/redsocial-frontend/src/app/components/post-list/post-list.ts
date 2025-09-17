import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PostService } from '../../services/post';
import { AuthService } from '../../services/auth';
import { WebsocketService, LikeUpdateMessage } from '../../services/websocket.service';
import { Post } from '../../models/post.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-post-list',
  imports: [CommonModule, HttpClientModule, FormsModule],
  templateUrl: './post-list.html',
  styleUrl: './post-list.scss'
})
export class PostList implements OnInit, OnDestroy {
  posts: Post[] = [];
  loading = false;
  error = '';
  private likeSubscription?: Subscription;

  constructor(
    private postService: PostService,
    private authService: AuthService,
    private websocketService: WebsocketService
  ) {}

  ngOnInit(): void {
    this.loadPosts();
    this.setupWebSocketSubscription();
  }

  ngOnDestroy(): void {
    if (this.likeSubscription) {
      this.likeSubscription.unsubscribe();
    }
  }

  setupWebSocketSubscription(): void {
    this.likeSubscription = this.websocketService.likeUpdates$.subscribe({
      next: (likeUpdate: LikeUpdateMessage | null) => {
        if (likeUpdate) {
          this.handleLikeUpdate(likeUpdate);
        }
      },
      error: (error) => {
        console.error('Error in WebSocket subscription:', error);
      }
    });
  }

  handleLikeUpdate(likeUpdate: LikeUpdateMessage): void {
    const index = this.posts.findIndex(p => p.id === likeUpdate.postId);
    if (index !== -1) {
      this.posts[index].likeCount = likeUpdate.likeCount;
      this.posts[index].likedByCurrentUser = likeUpdate.userHasLiked;
    }
  }

  loadPosts(): void {
    this.loading = true;
    this.postService.getAllPosts().subscribe({
      next: (posts) => {
        this.posts = posts;
        this.loading = false;
        
        // Suscribirse a las actualizaciones de likes para cada post
        this.posts.forEach(post => {
          this.websocketService.subscribeToPostLikes(post.id);
        });
      },
      error: (error) => {
        this.error = 'Error al cargar publicaciones';
        this.loading = false;
        console.error('Error loading posts:', error);
      }
    });
  }

  toggleLike(postId: number): void {
    this.postService.toggleLike(postId).subscribe({
      next: (updatedPost) => {
        // Actualizar solo el post específico en lugar de recargar todos
        const index = this.posts.findIndex(p => p.id === postId);
        if (index !== -1) {
          this.posts[index] = updatedPost;
        }
      },
      error: (error) => {
        console.error('Error toggling like:', error);
      }
    });
  }

  formatDate(date: string | Date): string {
    const dateObj = typeof date === 'string' ? new Date(date) : date;
    return dateObj.toLocaleDateString('es-ES', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

  getCurrentUser(): any {
    return this.authService.getCurrentUser();
  }

  trackByPostId(index: number, post: Post): number {
    return post.id;
  }
}
