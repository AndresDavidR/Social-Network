import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User, MessageResponse } from '../models/user.model';
import { AuthService } from './auth';

const API_URL = 'http://localhost:8080/api/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  private getHttpOptions() {
    const token = this.authService.getToken();
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
  }

  getUserProfile(username: string): Observable<User> {
    return this.http.get<User>(`${API_URL}${username}`);
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${API_URL}me`, this.getHttpOptions());
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(API_URL);
  }

  updateProfile(user: Partial<User>): Observable<User> {
    return this.http.put<User>(`${API_URL}me`, user, this.getHttpOptions());
  }
}
