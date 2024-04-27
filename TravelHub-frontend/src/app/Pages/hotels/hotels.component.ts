import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Hotel } from '../models.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrl: './hotels.component.css'
})
export class HotelsComponent {
    searchClicked: boolean = false;
    hotels: any[] = []; 
    filterhotels:any[]=[];
    restaurants: any[] = [];
    destination: string = "";
    showOptions: boolean = false;
    city: string="";
    isLoggedIn!: boolean;
  
    toggleOptions() {
      this.showOptions = !this.showOptions;
    }
  
    
    constructor(private hotelService: TravelHubServiceService,public router:Router,private authservice:AuthService) { }

    ngOnInit() {
      this.authservice.isLoggedIn$.subscribe((isLoggedIn: boolean) => {
        this.isLoggedIn = isLoggedIn;
      });
    }
  
    search(): void {
      this.searchClicked = true;
      console.log(this.destination);
      this.hotelService.searchDestination(this.destination).subscribe(
        (data) => {
          this.hotels = data; 
          console.log(this.hotels)
          console.log(this.destination) // Assuming data is an array of hotels
        },
        (error) => {
          console.error('Error:', error);
        }
      );
      this.hotelService.getRestaurants(this.destination).subscribe(
        (data) => {
          this.restaurants = data;  // Assuming data is an array of hotels
          console.log(this.restaurants)
        },
        (error) => {
          console.error('Error:', error);
        }
      );
      this.searchClicked = true;
  }
  bookNow(hotel: Hotel): void {
  }

  cityCode: string='';
  radius!: number;
  checkInDate!: string;
  adult!: number;

  toggleDetails(hotel: any): void {
    hotel.showDetails = !hotel.showDetails;
  }


  checkout(hotel: Hotel): void {
    if (!this.isLoggedIn) {
      const confirmLogin = window.confirm('Please login to proceed. Do you want to login now?');
      if (confirmLogin) {
        this.router.navigate(['/signin']); // Redirect to signin page if user confirms
      }
    } else {
      // Navigate to hotel booking page with hotel and check-in date as query parameters
      this.router.navigate(['/hotelbooking'], { 
        queryParams: { 
          hotel: JSON.stringify(hotel), // Convert hotel object to string
          checkinDate: JSON.stringify(this.checkInDate) // Pass check-in date
        } 
      });
    }
  }
  
  generateGoogleSearchLink(restaurantName: string): string {
    const location = restaurantName; // You can modify this as per your requirement
    return "https://www.google.com/search?q=" + encodeURIComponent(location) + "+restaurant";
  }
}
