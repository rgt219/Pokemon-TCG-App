import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common'; // Needed for interactive directives
import { RouterLink, RouterLinkActive, Router } from '@angular/router';
import { AuthService } from '../../../core/auth/auth.service';

@Component({
  imports: [CommonModule, RouterLink, RouterLinkActive],
  standalone: true,
  selector: 'app-navbar',
  styleUrl: './navbar.component.scss',
  templateUrl: './navbar.component.html',
})

export class NavbarComponent {

  authService = inject(AuthService);
  router = inject(Router);

  // State for interactivity
  isMenuOpen: boolean = false;
  userCredits: number = 150 // Fun placeholder for Profile stats

  // Toggle mobile menu dropdown
  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  get isLoggedIn(): boolean {
    return !!this.authService.getToken();
  }

  logout(): void {
    this.authService.clearToken();
    this.router.navigate(['/auth']);
  }
}
