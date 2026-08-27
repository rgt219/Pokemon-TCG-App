import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../core/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  imports: [FormsModule],
  standalone: true,
  selector: 'app-auth',
  styleUrl: './auth.component.scss',
  templateUrl: './auth.component.html',
})
export class AuthComponent {
  authService = inject(AuthService);
  router = inject(Router);

  isLoginMode = true;
  username = '';
  password = '';
  email = '';
  message = '';

  onSubmit() {
    if (this.isLoginMode) {
      this.authService.login({ username: this.username, password: this.password}).subscribe({
        next: () => {
          this.message = 'Login successful! Token saved.';
          this.router.navigate(['/']);
        },
          error: (err) => this.message = 'Login failed' + err.error
      });
    } else {
      this.authService.register({ username: this.username, email: this.email, password: this.password }).subscribe({        next: (res) => {
          this.message = res;
          this.isLoginMode = true; // Switch back to login after registering
        },
        error: (err) => this.message = 'Registration failed: ' + err.error
      })
    }
  }
}
