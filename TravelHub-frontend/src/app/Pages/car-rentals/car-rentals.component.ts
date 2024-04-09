import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Car } from '../models.service';

@Component({
  selector: 'app-car-rentals',
  templateUrl: './car-rentals.component.html',
  styleUrl: './car-rentals.component.css'
})
export class CarRentalsComponent {
  cars: Car[] = []; 
  pickup = "";
  selectedTime = "";
  pickupLocation!: string;
  rentalstartDate!: string;
  rentalendDate!: string;
  isLoggedIn!: boolean;


  constructor(private hotelService:TravelHubServiceService,public router:Router,private authService:AuthService) { }
  ngOnInit(): void {
    this.authService.isLoggedIn$.subscribe((isLoggedIn: boolean) => {
      this.isLoggedIn = isLoggedIn;
    });
  }

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
  confirm(car: Car) {
    if (!this.isLoggedIn) {
      const confirmLogin = window.confirm('Please login to proceed. Do you want to login now?');
      if (confirmLogin) {
        this.router.navigate(['/signin']); // Redirect to signin page if user confirms
      }
    } else {
      this.router.navigate(['/carbooking'], {
        queryParams: {
          car: JSON.stringify(car),
          rentalStartDate: this.rentalstartDate,
          rentalEndDate: this.rentalendDate
        }
      });
    } 
  }

}
