import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from '../models.service';

@Component({
  selector: 'app-carbooking',
  templateUrl: './carbooking.component.html',
  styleUrl: './carbooking.component.css'
})
export class CarbookingComponent {
  car!: Car;
  datePipe: any;
  rentalstartDate!: Date;
  rentalendDate!: Date;

  constructor(private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    // Retrieve the bus object from query parameters
    this.route.queryParams.subscribe(params => {
      if (params && params['car']) { // Check if 'car' parameter exists
        // Parse the car object from the string representation
        this.car = JSON.parse(params['car'] as string); // Type assertion
      }
      if (params && params['rentalStartDate']) { // Check if 'rentalStartDate' parameter exists
        this.rentalstartDate = params['rentalStartDate'];
      }
      if (params && params['rentalEndDate']) { // Check if 'rentalEndDate' parameter exists
        this.rentalendDate = params['rentalEndDate'];
      }
    });
  }
  confirm(car: Car): void {
    console.log("hello")
    this.router.navigate(['/payment'], { queryParams: { car: JSON.stringify(car) } });
  }
}
