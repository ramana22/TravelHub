import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car-rentals',
  templateUrl: './car-rentals.component.html',
  styleUrl: './car-rentals.component.css'
})
export class CarRentalsComponent {
  cars: any[] = []; 
  pickup = "";
  selectedTime = "";
  pickupLocation!: string;
  rentalstartDate!: string;
  rentalendDate!: string;


  constructor(private hotelService:TravelHubServiceService,public router:Router) { }


  searchCars() {
    console.log('Pick up location:', this.pickupLocation);
    console.log(this.rentalendDate)
    console.log(this.rentalstartDate)
    this.hotelService.getCars(this.pickupLocation,this.rentalstartDate,this.rentalendDate).subscribe(
      (data) => {
          this.cars = data;  // Assuming data is an array of hotels
          console.log("cars   :",this.cars)
      },
        (error) => {
          console.error('Error:', error);
        }
      );
  }

}
