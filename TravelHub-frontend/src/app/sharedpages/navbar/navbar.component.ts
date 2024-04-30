import { Component, ElementRef, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Pages/auth.service';
import { Notify } from 'src/app/Pages/models.service';
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
  notifications: Notify[]=[];
  userEmail!: string;
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
    this.userEmail = localStorage.getItem('userEmail') || '';
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
  notify() {
    this.service.getnotify(this.userEmail).subscribe(
      (response) => {
        // Handle successful response here
        this.notifications=response;
        console.log('Notifications:', response);
      },
      (error) => {
        // Handle error response here
        console.error('Error fetching notifications:', error);
      }
    );
  }
}
