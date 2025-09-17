import { User } from './user.model';

export interface Post {
  id: number;
  content: string;
  imageUrl?: string;
  createdAt: string | Date;
  updatedAt: string | Date;
  user: User;
  likeCount: number;
  likesCount: number; // Alias para compatibilidad
  likedByCurrentUser: boolean;
}

export interface PostRequest {
  content: string;
  imageUrl?: string;
}