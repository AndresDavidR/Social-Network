import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user';
import { PostService } from '../../services/post';
import { AuthService } from '../../services/auth';
import { User } from '../../models/user.model';
import { Post } from '../../models/post.model';

@Component({
  selector: 'app-profile',
  imports: [CommonModule],
  templateUrl: './profile.html',
  styleUrl: './profile.scss'
})
export class ProfileComponent implements OnInit {
  user: User | null = null;
  posts: Post[] = [];
  loading = false;
  error = '';
  username = '';

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private postService: PostService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.username = params['username'];
      this.loadUserProfile();
      this.loadUserPosts();
    });
  }

  loadUserProfile(): void {
    this.loading = true;
    this.userService.getUserProfile(this.username).subscribe({
      next: (user) => {
        this.user = user;
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Error al cargar el perfil del usuario';
        this.loading = false;
        console.error('Error loading user profile:', error);
      }
    });
  }

  loadUserPosts(): void {
    this.postService.getPostsByUser(this.username).subscribe({
      next: (posts) => {
        this.posts = posts;
      },
      error: (error) => {
        console.error('Error loading user posts:', error);
      }
    });
  }

  isCurrentUser(): boolean {
    const currentUser = this.authService.getCurrentUser();
    return currentUser?.username === this.username;
  }

  formatDate(date: string | Date): string {
    const dateObj = typeof date === 'string' ? new Date(date) : date;
    return dateObj.toLocaleDateString('es-ES', {
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    });
  }
}
