import { Component } from '@angular/core';
import { AuthService } from 'src/app/Pages/auth.service';
import { TravelHubServiceService } from 'src/app/Pages/travel-hub-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  isLoggedIn: boolean = false;

  email: string | undefined;

  constructor(
    private authService: AuthService,
    private service: TravelHubServiceService
  ) {}

  ngOnInit() {
    this.authService.isLoggedIn$.subscribe((isLoggedIn: boolean) => {
      this.isLoggedIn = isLoggedIn;
    });
  }
  logout() {
    this.authService.logout();
  }
}
