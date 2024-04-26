import { Component, ElementRef, HostListener } from '@angular/core';
import { Router } from '@angular/router';
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
  showCard!: boolean;
  notifyCard: boolean = false;

  constructor(
    private authService: AuthService,
    private el: ElementRef,
    private service: TravelHubServiceService,
    private router:Router
  ) {}

  ngOnInit() {
    this.authService.isLoggedIn$.subscribe((isLoggedIn: boolean) => {
      this.isLoggedIn = isLoggedIn;
    });
  }
  @HostListener('document:click', ['$event'])
  clickOutside(event: Event) {
    if (!this.el.nativeElement.contains(event.target)) {
      // Clicked outside the icon, close the card
      this.showCard = false;
    }
  }
  toggleCard2() {
    this.notifyCard = !this.notifyCard;
  }
  toggleCard() {
    this.showCard = !this.showCard;
  }

  logout() {
    this.authService.logout();
    this.showCard = false;
    this.router.navigate([""]);
  }
}
