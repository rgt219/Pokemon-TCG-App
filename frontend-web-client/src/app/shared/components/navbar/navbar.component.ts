import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // Needed for interactive directives
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  imports: [CommonModule, RouterLink, RouterLinkActive],
  standalone: true,
  selector: 'app-navbar',
  styleUrl: './navbar.component.scss',
  templateUrl: './navbar.component.html',
})

export class NavbarComponent {

  // State for interactivity
  isMenuOpen: boolean = false;
  userCredits: number = 150 // Fun placeholder for Profile stats

  // Toggle mobile menu dropdown
  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
