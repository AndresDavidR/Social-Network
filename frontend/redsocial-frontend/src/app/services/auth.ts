import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { LoginRequest, SignupRequest, JwtResponse, MessageResponse, User } from '../models/user.model';

const AUTH_API = 'http://localhost:8080/api/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User | null>(this.getCurrentUser());
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  login(credentials: LoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(AUTH_API + 'signin', credentials, httpOptions)
      .pipe(
        tap(response => {
          // Guardar token y datos del usuario
          sessionStorage.setItem('auth-token', response.accessToken);
          sessionStorage.setItem('auth-user', JSON.stringify(response));
          
          // Actualizar el usuario actual
          const user: User = {
            id: response.id,
            username: response.username,
            email: response.email,
            firstName: response.firstName,
            lastName: response.lastName,
            profileImageUrl: response.profileImageUrl,
            createdAt: new Date()
          };
          this.currentUserSubject.next(user);
        })
      );
  }

  register(user: SignupRequest): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(AUTH_API + 'signup', user, httpOptions);
  }

  logout(): void {
    sessionStorage.clear();
    this.currentUserSubject.next(null);
  }

  public getToken(): string | null {
    return sessionStorage.getItem('auth-token');
  }

  public isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

  public getCurrentUser(): User | null {
    const userStr = sessionStorage.getItem('auth-user');
    if (userStr) {
      const authData = JSON.parse(userStr);
      return {
        id: authData.id,
        username: authData.username,
        email: authData.email,
        firstName: authData.firstName,
        lastName: authData.lastName,
        profileImageUrl: authData.profileImageUrl,
        createdAt: new Date()
      };
    }
    return null;
  }
}
