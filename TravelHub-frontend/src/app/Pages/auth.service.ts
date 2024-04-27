import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TravelHubServiceService } from './travel-hub-service.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this.isLoggedInSubject.asObservable();


  constructor(public service:TravelHubServiceService) {
    // Retrieve login status from localStorage on initialization
    this.isLoggedInSubject.next(localStorage.getItem('isLoggedIn') === 'true');
  }

  login() {
    // Your login logic her
    localStorage.setItem('isLoggedIn', 'true'); // Store login status in localStorage
    this.isLoggedInSubject.next(true);
  }

  logout() {
    localStorage.setItem('isLoggedIn', 'false'); // Store login status in localStorage
    this.isLoggedInSubject.next(false);
  }
}