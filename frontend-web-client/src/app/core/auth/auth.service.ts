import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly TOKEN_KEY = 'poketome_jwt';
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/auth';

  // Saves the token to the browser's persistent storage
  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  // Retrieves the token (if it exists)
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  // Clears the token (used for logging out)
  clearToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  login(credentials: any) {
    return this.http.post<{token: string}>(`${this.apiUrl}/login`, credentials).pipe(
      // tap() silently intercepts the response to save the token before passing it to the component
      tap(response => this.setToken(response.token))
    );
  }

  register(userData: any) {
    // responseType: 'text' is required here because your Spring controller returns a plain string, not JSON
    return this.http.post(`${this.apiUrl}/register`, userData, { responseType: 'text'});
  }
}
