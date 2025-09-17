import { Component, EventEmitter, Output, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PostService } from '../../services/post';
import { AuthService } from '../../services/auth';
import { UserService } from '../../services/user';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-create-post',
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './create-post.html',
  styleUrl: './create-post.scss'
})
export class CreatePost implements OnInit {
  @Output() postCreated = new EventEmitter<void>();

  content = '';
  imageUrl = '';
  isSubmitting = false;
  error = '';
  success = '';
  characterCount = 0;
  maxCharacters = 500;
  currentUser: User | null = null;

  constructor(
    private postService: PostService,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
  }

  loadCurrentUser(): void {
    if (this.authService.isLoggedIn()) {
      this.userService.getCurrentUser().subscribe({
        next: (user) => {
          this.currentUser = user;
          console.log('Current user loaded from backend:', user);
        },
        error: (error) => {
          console.error('Error loading current user:', error);
          // Fallback to auth service
          this.currentUser = this.authService.getCurrentUser();
        }
      });
    }
  }

  onContentChange(): void {
    this.characterCount = this.content.length;
    this.error = '';
  }

  onSubmit(): void {
    if (this.content.trim().length === 0) {
      this.error = 'El contenido de la publicación no puede estar vacío';
      return;
    }

    if (this.content.length > this.maxCharacters) {
      this.error = `El contenido no puede exceder ${this.maxCharacters} caracteres`;
      return;
    }

    this.isSubmitting = true;
    this.error = '';

    const postData = {
      content: this.content.trim(),
      imageUrl: this.imageUrl.trim() || undefined
    };

    this.postService.createPost(postData).subscribe({
      next: (response) => {
        this.success = 'Publicación creada exitosamente';
        this.resetForm();
        this.postCreated.emit();
        
        // Limpiar mensaje de éxito después de 3 segundos
        setTimeout(() => {
          this.success = '';
        }, 3000);
      },
      error: (error) => {
        this.error = 'Error al crear la publicación. Inténtalo de nuevo.';
        console.error('Error creating post:', error);
      },
      complete: () => {
        this.isSubmitting = false;
      }
    });
  }

  resetForm(): void {
    this.content = '';
    this.imageUrl = '';
    this.characterCount = 0;
    this.error = '';
  }

  getCurrentUser(): User | null {
    return this.currentUser;
  }

  isValidUrl(url: string): boolean {
    if (!url) return true; // URL vacía es válida
    try {
      new URL(url);
      return true;
    } catch {
      return false;
    }
  }

  onImageUrlChange(): void {
    if (this.imageUrl && !this.isValidUrl(this.imageUrl)) {
      this.error = 'Por favor, ingresa una URL de imagen válida';
    } else {
      this.error = '';
    }
  }
}
