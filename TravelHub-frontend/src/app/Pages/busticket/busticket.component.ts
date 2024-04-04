import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bus } from '../models.service';

@Component({
  selector: 'app-busticket',
  templateUrl: './busticket.component.html',
  styleUrl: './busticket.component.css'
})
export class BusticketComponent {

  bus!: Bus;
  datePipe: any;

  constructor(private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    // Retrieve the bus object from query parameters
    this.route.queryParams.subscribe(params => {
      if (params && params['bus']) { // accessing 'bus' using index signature
        // Parse the bus object from the string representation
        this.bus = JSON.parse(params['bus'] as string); // type assertion
      }
    });
  }
  confirm(bus: Bus): void {
    console.log("hello")
    this.router.navigate(['/payment'], { queryParams: { bus: JSON.stringify(bus) } });
  }

}
