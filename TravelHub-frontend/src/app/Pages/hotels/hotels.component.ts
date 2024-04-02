import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Hotel, HotelOffer } from '../models.service';
import { Router } from '@angular/router';

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
  
    toggleOptions() {
      this.showOptions = !this.showOptions;
    }
  
    
    constructor(private hotelService: TravelHubServiceService,public router:Router) { }

    ngOnInit() {
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
      this.searchClicked = true;
  }
  bookNow(hotel: Hotel): void {
  }

  hotelOffers: HotelOffer[] = [];
  cityCode: string='';
  radius!: number;
  checkInDate!: string;
  adult!: number;

  toggleDetails(hotel: any): void {
    hotel.showDetails = !hotel.showDetails;
  }

  onSubmit(cityCode: string,checkInDate: string,adult:number, radius?: number) {
    this.hotelService.getHotelOffers(cityCode,checkInDate,adult, radius)
      .subscribe(
        (offers: HotelOffer[]) => {
            this.hotelOffers = offers;
            console.log(this.hotelOffers); // Handle the retrieved hotel offers as needed
        },
        (error) => {
          console.error('Error fetching hotel offers:', error);
            // Handle error appropriately
        }
    );
  }
}
