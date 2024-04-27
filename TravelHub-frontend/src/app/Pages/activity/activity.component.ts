import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FlightTicket, HotelBooking, User } from '../models.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrl: './activity.component.css'
})
export class ActivityComponent {
  flightTickets!: FlightTicket;
cancelBooking(arg0: any) {
throw new Error('Method not implemented.');
}
  showhotel:boolean=false;
  showflight:boolean=false;
  showtrain:boolean=false;
  showbus:boolean=false;
  showcar:boolean=false;
  isLoggedIn!: boolean;
  user!:User;
  public userEmail!: string;
  Hotelbookings:HotelBooking[]=[];
  constructor(public _service:TravelHubServiceService,public _Router:Router,public authService:AuthService){}
  ngOnInit(): void {
    this.authService.isLoggedIn$.subscribe((isLoggedIn: boolean) => {
      this.isLoggedIn = isLoggedIn;
    });
    this.userEmail = localStorage.getItem('userEmail') || '';
  }
  togglehotel(){
    this.showhotel=!this.showhotel;
  }
  toggleflights(){
    this.showflight=!this.showflight;
  }
  toggletrains(){
    this.showtrain=!this.showtrain;
  }
  togglebuses(){
    this.showbus=!this.showbus;
  }
  togglecars(){
    this.showcar=!this.showcar;
  }
  showhotels(){
    this._service.getBookings(this.userEmail).subscribe(
      (response) => {
        this.Hotelbookings=response;
        console.log('Bookings retrieved successfully:', response);
        // Handle the response data here
      },
      (error) => {
        console.error('Error retrieving bookings:', error);
        // Handle errors here
      }
    );
  }
  showflights(){
    this._service.getflighttickets(this.userEmail).subscribe(
      (response) => {
        this.flightTickets=response;
        console.log('Bookings retrieved successfully:', response);
        // Handle the response data here
      },
      (error) => {
        console.error('Error retrieving bookings:', error);
        // Handle errors here
      }
    );
  }
  showtrains(){
    
  }
  showcars(){
    
  }
  showbuses(){
    
  }
}
