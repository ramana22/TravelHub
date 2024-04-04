import { Component, ElementRef, ViewChild, viewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bus, BusTicket, Payment, Train, TrainTicket, Traveler, User } from '../models.service';
import { NgForm } from '@angular/forms';
import { TravelHubServiceService } from '../travel-hub-service.service';

@Component({
  selector: 'app-paymentpage',
  templateUrl: './paymentpage.component.html',
  styleUrl: './paymentpage.component.css'
})
export class PaymentpageComponent {
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
      if (params && params['train']) { // accessing 'bus' using index signature
        // Parse the bus object from the string representation
        this.bus = JSON.parse(params['train'] as string); // type assertion
        console.log(this.train);
        this.price = this.train.price;
        console.log(this.price);
      }
    });
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
  
    if (this.bus) {
      const busTicket: BusTicket = {
        id: 0, // You may need to set this value appropriately
        bus: this.bus,
        traveler: traveler,
        payment: this.payment,
        user: this.travelservice.currentuser// Assuming currentUser holds the current user
      };
      const userEmail = this.travelservice.currentuser.email; // Replace with actual user email
      this.travelservice.saveBusTicket(busTicket, userEmail).subscribe(
        (savedBusTicket) => {
          console.log('Bus ticket saved successfully:', savedBusTicket);
          // Optionally, you can navigate to another page or perform other actions upon successful save
        },
        (error) => {
          console.error('Error saving bus ticket:', error);
          // Handle error appropriately
        }
      );
    }
  
    if (this.train) {
      const trainTicket: TrainTicket = {
        train: this.train,
        traveler: traveler,
        payment: this.payment,
        user: this.travelservice.currentuser // Assuming currentUser holds the current user
      };
      const userEmail = this.travelservice.currentuser.email; // Replace with actual user email
      this.travelservice.saveTrainTicket(trainTicket, userEmail).subscribe(
        (savedTrainTicket) => {
          console.log('Train ticket saved successfully:', savedTrainTicket);
          // Optionally, you can navigate to another page or perform other actions upon successful save
        },
        (error) => {
          console.error('Error saving train ticket:', error);
          // Handle error appropriately
        }
      );
    }
  
    // Calculate subtotal and grandtotal
    this.subtotal = this.price * traveler.people;
    this.random = this.generateRandomServiceTax();
    this.grandtotal = this.subtotal + this.random;
  
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
