import { Component } from '@angular/core';
import { Train } from '../models.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-trainticket',
  templateUrl: './trainticket.component.html',
  styleUrl: './trainticket.component.css'
})
export class TrainticketComponent {
  train!: Train;
  datePipe: any;

  constructor(private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    // Retrieve the bus object from query parameters
    
    this.route.queryParams.subscribe(params => {
      if (params && params['train']) { // accessing 'bus' using index signature
        // Parse the bus object from the string representation
        this.train = JSON.parse(params['train'] as string); // type assertion
      }
    });
  }
  confirm(train:Train): void {
    console.log("hello")
    this.router.navigate(['/payment'], { queryParams: { train: JSON.stringify(train) } });
  }

}
