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

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      console.log('Query parameters:', params); // Log all query parameters
      if (params && params['hotel']) { 
        console.log('Hotel parameter found:');
        this.hotel = JSON.parse(params['hotel'] as string); 
        console.log(this.hotel);
      } else {
        console.log('Hotel parameter not found');
      }
    });
  }
  checkout(hotel: Hotel) {
    this.router.navigate(['/payment'], { queryParams: { hotel: JSON.stringify(hotel) } });
  }
  
}
