import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post, PostRequest } from '../models/post.model';
import { MessageResponse } from '../models/user.model';

const API_URL = 'http://localhost:8080/api/posts';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(API_URL);
  }

  getPostById(id: number): Observable<Post> {
    return this.http.get<Post>(`${API_URL}/${id}`);
  }

  getPostsByUser(username: string): Observable<Post[]> {
    return this.http.get<Post[]>(`${API_URL}/user/${username}`);
  }

  createPost(post: PostRequest): Observable<Post> {
    return this.http.post<Post>(API_URL, post);
  }

  toggleLike(postId: number): Observable<Post> {
    return this.http.post<Post>(`${API_URL}/${postId}/like`, {});
  }

  deletePost(id: number): Observable<MessageResponse> {
    return this.http.delete<MessageResponse>(`${API_URL}/${id}`);
  }
}
