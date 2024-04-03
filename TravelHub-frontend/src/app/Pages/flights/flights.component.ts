import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FlightOffer, LocationData } from '../models.service';

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
  searchResults: FlightOffer[]=[];
  returnDate!: string;
  tripType: string = 'one-way';
  searchResult: LocationData[]=[];
  dropdownOptions: any;
  dropdownOpen!: boolean;
  departureDropdownOpen!: boolean;
  arrivalDropdownOpen!: boolean;
  departureSearchResult!: any[];
  arrivalSearchResult!: any[];
  departureDateObject!: Date;
  starttime!: Date;
  endtime!: Date;

  constructor(private fb: FormBuilder,public service: TravelHubServiceService) {}
  isRoundTrip: boolean = false;
  searchTerm: string = '';
  airportList: { name: string, iataCode: string }[] = [];
  // Function to toggle trip type
  toggleTripType(type: string) {
    this.tripType = type;
    if(type){
      this.departureAirport='';
      this.arrivalAirport!='';
      this.departureDate!='';
      this.passengers!=0;
    }

  }

  toggleDetails(flight: any) { // Replace any with actual flight interface/type
    flight.showDetails = !flight.showDetails;
  }
  reloadPage() {
    location.reload();
  }
  ngOnInit(): void {
    
  }

  searchFlights() {
  this.searchResults=[];
  if(this.passengers <= 0){
    window.alert("please add passengers")
  }
  this.service.searchFlights(this.departureAirport, this.arrivalAirport,
                                       this.departureDate,this.returnDate, 
                                        this.passengers)
        .subscribe(
          (response) => {
            this.searchResults = response; // Store the response in searchResults
            this.departureDateObject = new Date(this.departureDate);
            this.starttime=this.generateRandomTimeWithinDate(this.departureDateObject);
            this.endtime=this.generateRandomEndTime(this.starttime);
            console.log('Search Results:', this.searchResults);
          },
          (error) => {
            console.error('Error fetching flights:', error);
          }
        );
  }
  searchFlightsoneway() {
    if(this.passengers <= 0){
      window.alert("please add passengers")
    }
    this.service.searchFlightsoneway(this.departureAirport, this.arrivalAirport,
                                         this.departureDate, 
                                          this.passengers)
          .subscribe(
            (response) => {
            this.searchResults = response; 
            this.departureDateObject = new Date(this.departureDate);
            this.starttime=this.generateRandomTimeWithinDate(this.departureDateObject);
            this.endtime=this.generateRandomEndTime(this.starttime);
              console.log('Search Results:', this.searchResults);
            },
            (error) => {
              console.error('Error fetching flights:', error);
            }
          );
  }
 
  
  searchAirports(event: any, type: string) {
    const searchTerm: string = event.target.value;
  
    if (searchTerm.trim() !== '') {
      this.service.getAirports(searchTerm)
        .subscribe(
          (response: any[]) => {
            if (type === 'departure') {
              this.departureSearchResult = response;
              this.departureDropdownOpen = true;
              this.arrivalDropdownOpen = false; // Close arrival dropdown
            } else if (type === 'arrival') {
              this.arrivalSearchResult = response;
              this.arrivalDropdownOpen = true;
              this.departureDropdownOpen = false; // Close departure dropdown
            }
            console.log(response);
          },
          (error) => {
            console.error('Error searching airports:', error);
          }
        );
    } else {
      if (type === 'departure') {
        this.departureSearchResult = [];
        this.departureDropdownOpen = false;
      } else if (type === 'arrival') {
        this.arrivalSearchResult = [];
        this.arrivalDropdownOpen = false;
      }
    }
}
// Generate a random time within a given date
generateRandomTimeWithinDate(date: Date): Date {
  const year = date.getFullYear();
  const month = date.getMonth();
  const day = date.getDate();
  const randomHour = Math.floor(Math.random() * 24);
  const randomMinute = Math.floor(Math.random() * 60);
  const randomSecond = Math.floor(Math.random() * 60);
  return new Date(year, month, day, randomHour, randomMinute, randomSecond);
}

// Generate an end time based on a given start time
generateRandomEndTime(startTime: Date): Date {
  const endTime = new Date(startTime);
  endTime.setHours(endTime.getHours() + Math.floor(Math.random() * 6) + 1); // Add random hours (1-6)
  return endTime;
}
 
}

