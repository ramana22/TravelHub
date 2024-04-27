import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Hotel } from '../models.service';

@Component({
  selector: 'app-hotelbooking',
  templateUrl: './hotelbooking.component.html',
  styleUrl: './hotelbooking.component.css'
})
export class HotelbookingComponent {
  hotel!: Hotel; // Update type to any or Hotel depending on your data structure
  checkInDate!: string;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      console.log('Query parameters:', params); // Log all query parameters
      if (params && params['hotel']) { 
        console.log('Hotel parameter found:');
        try {
          this.hotel = JSON.parse(params['hotel'] as string); // Parse hotel parameter from JSON string
          this.checkInDate = JSON.parse(params['checkinDate'] as string); // Parse checkinDate parameter
          console.log(this.hotel); // Log hotel details
        } catch (error) {
          console.error('Error parsing query parameters:', error); // Log parsing error
        }
      } else {
        console.log('Hotel parameter not found');
      }
    });        
  }
  checkout(hotel: Hotel) {
    this.router.navigate(['/payment'], { 
      queryParams: { 
        hotel: JSON.stringify(hotel), // Stringify the hotel object
        checkinDate: JSON.stringify(this.checkInDate) // Pass the check-in date
      } 
    });
  }
  
}
