import { Component, ElementRef, ViewChild, viewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bus, BusTicket, Car, CarBooking, EmailRequest, FlightTicket, Hotel, HotelBooking, Payment, Train, TrainTicket, Traveler, User } from '../models.service';
import { NgForm } from '@angular/forms';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

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

  constructor(private http: HttpClient,private route: ActivatedRoute,private router:Router,private travelservice:TravelHubServiceService) { }

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
    this.userEmail = traveler.emailAddress;
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
      const msg =this.constructBusTicketMessage(busTicket)
      const useremail=traveler.emailAddress;
      const sub = "BusTicket Information";
      this.sendEmailOnPaymentCompletion(useremail,msg,sub).subscribe(() => {
        console.log('Email sent successfully.');
      }, (error: any) => {
        console.error('Failed to send email:', error);
      });
    
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
      const msg =this.constructTrainBookingMessage(trainTicket)
      const useremail=traveler.emailAddress;
      const sub = "TrainTicket Information";
      this.sendEmailOnPaymentCompletion(useremail,msg,sub).subscribe(() => {
        console.log('Email sent successfully.');
      }, (error: any) => {
        console.error('Failed to send email:', error);
      });
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
      const msg =this.constructFlightBookingMessage(this.flightTicket);
      const useremail=traveler.emailAddress;
      const sub = "FlightTicket Information";
      this.sendEmailOnPaymentCompletion(useremail,msg,sub).subscribe(() => {
        console.log('Email sent successfully.');
      }, (error: any) => {
        console.error('Failed to send email:', error);
      });
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
      const msg =this.constructHotelBookingMessage(hotelbooking);
      const useremail=traveler.emailAddress;
      const sub = "HotelBooking Information";
      this.sendEmailOnPaymentCompletion(useremail,msg,sub).subscribe(() => {
        console.log('Email sent successfully.');
      }, (error: any) => {
        console.error('Failed to send email:', error);
      });
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
      const msg =this.constructCarBookingMessage(carbooking);
      const useremail=traveler.emailAddress;
      const sub = "CarBooking Information";
      this.sendEmailOnPaymentCompletion(useremail,msg,sub).subscribe(() => {
        console.log('Email sent successfully.');
      }, (error: any) => {
        console.error('Failed to send email:', error);
      });
      this.travelservice.saveCarBooking(carbooking,this.userEmail)
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

  sendEmailOnPaymentCompletion( payerEmail: string,message:string,sub:string): Observable<any> {
    // Construct the email request object
    const emailRequest: EmailRequest = {
      to: payerEmail , // Replace with the actual recipient email address
      subject: sub, // Subject of the email
      text:message // Body text of the email
    };
  
    // Make an HTTP POST request to your server-side endpoint
    const url = 'https://travel.up.railway.app/sendEmail'; // Assuming your Angular app is served from the same host as your Spring Boot backend
    return this.http.post<any>(url, emailRequest);
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
  constructBusTicketMessage(busTicket: BusTicket): string {
    let message = "Dear User,\n\n";
    message += "Thank you for booking a bus ticket with us. Below are the details of your booked ticket:\n\n";
    message += `Bus Ticket ID: ${busTicket.bus.busId}\n`;
    message += `Departure Station: ${busTicket.bus.departureTerminal}\n`;
    message += `Arrival Station: ${busTicket.bus.arrivalTerminal}\n`;
    message += `Departure Time: ${busTicket.bus.departureTime}\n`;
    message += `Arrival Time: ${busTicket.bus.arrivalTime}\n`;
    message += `Operator: ${busTicket.bus.operator}\n`;
    message += `Price: $${busTicket.bus.price}\n\n`;
    message += "If you have any questions or need further assistance, feel free to contact us.\n\n";
    message += "Best regards,\nThe Travel Team";
  
    return message;
  }
  constructHotelBookingMessage(hotelBooking: HotelBooking): string {
    let message = "Dear User,\n\n";
    message += "Thank you for booking a hotel with us. Below are the details of your booked hotel:\n\n";
    message += `Booking ID: ${hotelBooking.hotel?.hotelid}\n`;
    message += `Hotel Name: ${hotelBooking.hotel?.name}\n`;
    message += `Check-in Date: ${hotelBooking.checkinDate}\n`;
    message += `Room Number: ${hotelBooking.hotel?.room?.roomNumber}\n`;
    message += `Bill: ${hotelBooking.hotel?.room?.bill?.amount}\n\n`;
    message += "If you have any questions or need further assistance, feel free to contact us.\n\n";
    message += "Best regards,\nThe Travel Team";
  
    return message;
  }
  
  constructFlightBookingMessage(flightBooking: FlightTicket): string {
    let message = "Dear User,\n\n";
    message += "Thank you for booking a flight with us. Below are the details of your booked flight:\n\n";
    message += `Booking ID: ${flightBooking.id}\n`;
    message += `Departure: ${flightBooking.origin}\n`;
    message += `Destination: ${flightBooking.destination}\n`;
    message += `Departure Date: ${flightBooking.departDate}\n`;
    message += `Return Date: ${flightBooking.returnDate}\n`;
    message += `Number of Travelers: ${flightBooking.traveler.people}\n`;
    message += `Full Name: ${flightBooking.traveler.fullName}\n`;
    message += `Date of Birth: ${flightBooking.traveler.dateOfBirth}\n`;
    message += `Email Address: ${flightBooking.traveler.emailAddress}\n`;
    message += `Phone Number: ${flightBooking.traveler.phoneNumber}\n\n`;
    message += "If you have any questions or need further assistance, feel free to contact us.\n\n";
    message += "Best regards,\nThe Travel Team";
  
    return message;
  }
  
  constructCarBookingMessage(carBooking: CarBooking): string {
    let message = "Dear User,\n\n";
    message += "Thank you for booking a car with us. Below are the details of your booked car:\n\n";
    message += `Car Booking ID: ${carBooking.car.id}\n`;
    message += `Car Model: ${carBooking.car.carModel}\n`;
    message += `Pickup Location: ${carBooking.car.pickupLocation}\n`;
    message += `Rental Start Date: ${carBooking.car.rentalStartDate}\n`;
    message += `Rental End Date: ${carBooking.car.rentalEndDate}\n`;
    message += `Total Price: ${carBooking.car.totalPrice}\n\n`;
    message += "If you have any questions or need further assistance, feel free to contact us.\n\n";
    message += "Best regards,\nThe Travel Team";
  
    return message;
  }
  
  constructTrainBookingMessage(trainTicket: TrainTicket): string {
    let message = "Dear User,\n\n";
    message += "Thank you for booking a train ticket with us. Below are the details of your booked ticket:\n\n";
    message += `Train Ticket ID: ${trainTicket.train.trainId}\n`;
    message += `Departure Station: ${trainTicket.train.departureStation}\n`;
    message += `Arrival Station: ${trainTicket.train.arrivalStation}\n`;
    message += `Departure Time: ${trainTicket.train.departureTime}\n`;
    message += `Arrival Time: ${trainTicket.train.arrivalTime}\n`;
    message += `Operator: ${trainTicket.train.operator}\n`;
    message += `Price: ${trainTicket.train.price}\n\n`;
    message += "If you have any questions or need further assistance, feel free to contact us.\n\n";
    message += "Best regards,\nThe Travel Team";
  
    return message;
  }
  
}



