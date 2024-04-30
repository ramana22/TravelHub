import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { BusTicket, CarBooking, FlightTicket, HotelBooking, TrainTicket, User } from '../models.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrl: './activity.component.css'
})
export class ActivityComponent {
  flightTickets: FlightTicket[]=[];
  traintickets: TrainTicket[]=[];
  bustickets: BusTicket[]=[];
  carbookings: CarBooking[]=[];
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
    this._service.gettraintickets(this.userEmail).subscribe(
      (response) => {
        this.traintickets=response;
        console.log('Bookings retrieved successfully:', response);
        // Handle the response data here
      },
      (error) => {
        console.error('Error retrieving bookings:', error);
        // Handle errors here
      }
    );
  }
  showcars(){
    this._service.getcarbookings(this.userEmail).subscribe(
      (response) => {
        this.carbookings=response;
        console.log('Bookings retrieved successfully:', response);
        // Handle the response data here
      },
      (error) => {
        console.error('Error retrieving bookings:', error);
        // Handle errors here
      }
    );
  }
  showbuses(){
    this._service.getbustickets(this.userEmail).subscribe(
      (response) => {
        this.bustickets=response;
        console.log('Bookings retrieved successfully:', response);
      },
      (error) => {
        console.error('Error retrieving bookings:', error);
      }
    );
  }
  
  cancelbusBooking(id:number){
    console.log(id)
    this._service.deletebusticket(this.userEmail, id).subscribe(
      (response) => {
        // Handle successful response
        console.log('Bus ticket deleted successfully', response);
      },
      (error) => {
        // Handle error
        console.error('Error deleting bus ticket', error);
      }
    );
  }
  canceltrainBooking(id:number){
    console.log(id)
    this._service.deletetrainticket(this.userEmail, id).subscribe(
      (response) => {
        // Handle successful response
        console.log('Bus ticket deleted successfully', response);
      },
      (error) => {
        // Handle error
        console.error('Error deleting bus ticket', error);
      }
    );
  }
  cancelflightBooking(id:number){
    console.log(id)
    this._service.deleteflightticket(this.userEmail, id).subscribe(
      (response) => {
        // Handle successful response
        console.log('Bus ticket deleted successfully', response);
      },
      (error) => {
        // Handle error
        console.error('Error deleting bus ticket', error);
      }
    );
  }
  cancelhotelBooking(id:number){
    console.log(id)
    this._service.deletehotelbooking(this.userEmail, id).subscribe(
      (response) => {
        // Handle successful response
        console.log('Bus ticket deleted successfully', response);
      },
      (error) => {
        // Handle error
        console.error('Error deleting bus ticket', error);
      }
    );
  }
  cancelcarBooking(id:number){
    console.log(id)
    this._service.deletecarbooking(this.userEmail, id).subscribe(
      (response) => {
        // Handle successful response
        console.log('Bus ticket deleted successfully', response);
      },
      (error) => {
        // Handle error
        console.error('Error deleting bus ticket', error);
      }
    );
  }
}
