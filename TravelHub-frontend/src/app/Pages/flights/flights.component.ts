import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Airport } from '../models.service';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrl: './flights.component.css'
})
export class FlightsComponent {
  departureAirport!: string;
  arrivalAirport!: string;
  departureDate!: string;
  passengers!: number;
  searchResults: any;
  returnDate!: string;
  tripType: string = 'one-way';
  searchResult: any;

  constructor(private fb: FormBuilder,public service: TravelHubServiceService) {}
  isRoundTrip: boolean = false;
  searchTerm: string = '';
  airportList: { name: string, iataCode: string }[] = [];
  // Function to toggle trip type
  toggleTripType(type: string) {
    this.tripType = type;
  }
  toggleDetails(flight: any) { // Replace any with actual flight interface/type
    flight.showDetails = !flight.showDetails;
  }
  ngOnInit(): void {
    
  }

  searchFlights() {
  this.service.searchFlights(this.departureAirport, this.arrivalAirport,
                                       this.departureDate,this.returnDate, 
                                        this.passengers)
        .subscribe(
          (response) => {
            this.searchResults = response; // Store the response in searchResults
            console.log('Search Results:', this.searchResults);
          },
          (error) => {
            console.error('Error fetching flights:', error);
          }
        );
  }
  
  searchAirports(event: any) {
    const searchTerm: string = event.target.value; // Get the input value from the event

    if (searchTerm.trim() !== '') {
      this.service.getAirports(searchTerm)
        .subscribe(
          (response:any) => {
            this.searchResult = response;
            console.log(this.searchResults) // Update search results with the response from the server
          },
          (error) => {
            console.error('Error searching airports:', error);
          }
        );
    } else {
      this.searchResults = []; // Clear search results if the search term is empty
    }
  }
}
