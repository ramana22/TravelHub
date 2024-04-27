import { Component, ElementRef, ViewChild, viewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bus, BusTicket, Car, CarBooking, FlightTicket, Hotel, HotelBooking, Payment, Train, TrainTicket, Traveler, User } from '../models.service';
import { NgForm } from '@angular/forms';
import { TravelHubServiceService } from '../travel-hub-service.service';

@Component({
  selector: 'app-paymentpage',
  templateUrl: './paymentpage.component.html',
  styleUrl: './paymentpage.component.css'
})
export class PaymentpageComponent {
  [x: string]: any;
  bus!: Bus;
  paymentMethod: any;
  price: number = 0;
  subtotal: number = 0;
  random: number = 0;
  grandtotal: number = 0;
  traveler!: Traveler;// Initialize an empty Traveler object
  isclicked: boolean = false;
  fullName!: string;
  dateOfBirth!: Date;
  people!: number;
  emailAddress!: string;
  phoneNumber!: string;
  amount=0;
  payment!: Payment;
  train!: Train;
  trainTicket!: TrainTicket;
  userEmail!: string;
  hotel!: Hotel;
  flight: any;
  car!: Car;
  grand!: number;
  carBooking!: CarBooking;
  flightTicket!: FlightTicket;
  tempuser!:User;
  checkinDate!: string;

  constructor(private route: ActivatedRoute,private router:Router,private travelservice:TravelHubServiceService) { }

  ngOnInit(): void {
    // Retrieve the bus object from query parameters
    this.route.queryParams.subscribe(params => {
      if (params && params['bus']) { // accessing 'bus' using index signature
        // Parse the bus object from the string representation
        this.bus = JSON.parse(params['bus'] as string); // type assertion
        console.log(this.bus);
        this.price = this.bus.price;
        console.log(this.price);
      }
    });
    this.route.queryParams.subscribe(params => {
      if (params && params['flight']) {
        this.flight = JSON.parse(params['flight'] as string); // type assertion
        console.log(this.flight);
        this.price = this.flight.travelerPricings[0].price.total;
        console.log(this.price);
        this.flightTicket = JSON.parse(params['flightTicket'] as string);
        console.log(this.flightTicket);
      } 
    });    
    this.route.queryParams.subscribe(params => {
      if (params && params['train']) { 
        this.train = JSON.parse(params['train'] as string); // type assertion
        console.log(this.train);
        this.price = this.train.price;
        console.log(this.price);
      }
    });
    this.route.queryParams.subscribe(params => {
      if (params && params['hotel']) {
        this.hotel = JSON.parse(params['hotel'] as string); // Parse hotel object
        console.log(this.hotel);
    
        // Access nested properties
        this.price = this.hotel.room.bill.amount; // Retrieve room price
        console.log(this.price);
    
        this.checkinDate = JSON.parse(params['checkinDate'] as string); // Parse check-in date
        console.log(this.checkinDate)
      }
    });
    
    this.route.queryParams.subscribe(params => {
      if (params && params['car']) { 
        this.car = JSON.parse(params['car'] as string); // type assertion
        console.log(this.car)
        this.price = this.car.totalPrice;
        console.log(this.price);
      }
    });
    this.userEmail = localStorage.getItem('userEmail') || '';
  }

  onSubmit(fullName: string, emailAddress: string, phoneNumber: string, dateOfBirth: Date, people: number): void {
    // Ensure traveler is properly initialized
    const traveler: Traveler = {
      fullName: fullName,
      emailAddress: emailAddress,
      phoneNumber: phoneNumber,
      dateOfBirth: new Date(dateOfBirth),
      people: people
    };
    console.log(this.bus);
    console.log(this.train);
    console.log(this.car);
    console.log(this.flight);
    console.log(this.hotel);
    if (this.bus) {
      const busTicket: BusTicket = {
        id: 0, // You may need to set this value appropriately
        bus: this.bus,
        traveler: traveler,
        payment: this.payment,
        user: new User
      };
      const userEmail = this.tempuser.email // Replace with actual user email
      console.log(userEmail)
      this.travelservice.saveBusTicket(busTicket,this.userEmail).subscribe(
        (savedBusTicket) => {
          console.log('Bus ticket saved successfully:', savedBusTicket);
          // Optionally, you can navigate to another page or perform other actions upon successful save
        },
        (error) => {
          console.error('Error saving bus ticket:', error);
          // Handle error appropriately
        }
      );
      
      // Calculate subtotal and grandtotal for bus
      this.subtotal = this.price * traveler.people;
      this.random = this.generateRandomServiceTax();
      this.grand = this.subtotal + this.random;
      this.grandtotal = Math.round(this.grand * 100) / 100;
    }
  
    if (this.train) {
      const trainTicket: TrainTicket = {
        train: this.train,
        traveler: traveler,
        payment: this.payment,
        user: new User,
        id: 0
      };
      const userEmail = this.tempuser.email // Replace with actual user email
      console.log(userEmail)
      this.travelservice.saveTrainTicket(trainTicket,this.userEmail).subscribe(
        (savedTrainTicket) => {
          console.log('Train ticket saved successfully:', savedTrainTicket);
          // Optionally, you can navigate to another page or perform other actions upon successful save
        },
        (error) => {
          console.error('Error saving train ticket:', error);
          // Handle error appropriately
        }
      );
      
      // Calculate subtotal and grandtotal for train
      this.subtotal = this.price * traveler.people;
      this.random = this.generateRandomServiceTax();
      this.grand = this.subtotal + this.random;
      this.grandtotal = Math.round(this.grand * 100) / 100;
    }
    if (this.flight) {
      this.flightTicket.traveler=traveler;
      this.travelservice.saveFlightTicket(this.flightTicket,this.userEmail).subscribe(
        (savedTrainTicket) => {
          console.log('flight ticket saved successfully:', savedTrainTicket);
          // Optionally, you can navigate to another page or perform other actions upon successful save
        },
        (error) => {
          console.error('flight saving train ticket:', error);
          // Handle error appropriately
        }
      );
      // Calculate subtotal and grandtotal for train
      this.subtotal = this.price * traveler.people;
      this.random = this.generateRandomServiceTax();
      this.grand = this.subtotal + this.random;
      this.grandtotal = Math.round(this.grand * 100) / 100;
    }
    
    if (this.hotel) {
      console.log('Hotel details', this.hotel);
      const hotelbooking: HotelBooking = {
        id:0,
        hotel: this.hotel,          // Assign the hotel object
        checkinDate: this.checkinDate,  // Assign the check-in date
        traveler: traveler,         // Assign the traveler object
        payment: this.payment       // Assign the payment object
      };      
      console.log('Hotel booking saving', hotelbooking);
      this.travelservice.saveHotelBooking(hotelbooking,this.userEmail)
          .subscribe((response) => {
              console.log('Hotel booking saved:', response);
              // Handle success or redirect
          }, (error) => {
              console.error('Error saving hotel booking:', error);
              // Handle error
          });
  
      // Calculate subtotal and grandtotal for hotel
      this.subtotal = this.price * traveler.people;
      this.random = this.generateRandomServiceTax();
      this.grand = this.subtotal + this.random;
      this.grandtotal = Math.round(this.grand * 100) / 100;
  }
  
    if (this.car) {
      const carbooking: CarBooking = {
        car: this.car,
        traveler: traveler,
        payment: this.payment,
        id: 0,
        user: new User
      };
      this.travelservice.saveCarBooking(this.carBooking,this.userEmail)
      .subscribe((result) => {
        console.log('Car booking saved successfully:', result);
        // Optionally, you can perform additional actions after the car booking is saved
      }, (error) => {
        console.error('Error occurred while saving car booking:', error);
        // Optionally, you can handle errors here
      });
      this.subtotal = this.price;
      this.random = this.generateRandomServiceTax();
      this.grand = this.subtotal + this.random;
      this.grandtotal = Math.round(this.grand * 100) / 100;
      
    }
  
    this.isclicked = true;
  }

  

  generateRandomServiceTax(): number {
    // Generate a random number between 0 and 1
    const randomTax = Math.random();
    // Round to 2 decimal places
    const roundedTax = Math.round(randomTax * 100) / 100;
    return roundedTax;
  }
  
  isButtonVisible: boolean = true;

  confirm(): void {
        console.log("Confirm button clicked");
        this.isButtonVisible = false; // Hide the button
  }

}
