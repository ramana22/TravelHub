import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FlightTicket } from '../models.service';

@Component({
  selector: 'app-flightticket',
  templateUrl: './flightticket.component.html',
  styleUrl: './flightticket.component.css'
})
export class FlightticketComponent {
  getstarttime!: Date;
  getendTime!: Date;
  startdate: any;
  starttime: any;
  enddate: any;
  endtime: any;
  flight: any;
  departureDetail!: string ;

  arrivalDetail!: string;
  flightTicket: FlightTicket = new FlightTicket();

  constructor(private route: ActivatedRoute,private service:TravelHubServiceService,private router:Router) { }

  ngOnInit(): void {
    // Retrieve route parameters
    this.route.params.subscribe(params => {
      this.departureDetail = params['departureDetail'];
      this.arrivalDetail = params['arrivalDetail'];
      this.getstarttime = new Date(params['starttime']);
      const { date: startdate, time: starttime } = this.parseDateTime(params['starttime']);
      this.startdate = startdate;
      this.starttime = starttime;
      
      this.getendTime = new Date(params['endTime']);
      const { date: enddate, time: endtime } = this.parseDateTime(params['endTime']);
      this.enddate = enddate;
      this.endtime = endtime;
      this.flightTicket.origin = params['departureDetail'];
      this.flightTicket.destination = params['arrivalDetail'];
      this.flightTicket.departDate = params['starttime'];
      this.flightTicket.returnDate = params['endTime'];
      this.flightTicket.adults = params['adults']; 
    });
    
    // Retrieve flight from router's state
    this.flight = history.state.flight;
  }
  parseDateTime(dateTimeString: string): { date: string, time: string } {
    // Parse the string representation of the date
    const dateTime = new Date(dateTimeString);
  
    // Extract date parts
    const year = dateTime.getFullYear();
    const monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    const month = monthNames[dateTime.getMonth()];
    const day = dateTime.getDate();
  
    // Extract time parts
    const hours = dateTime.getHours().toString().padStart(2, '0');
    const minutes = dateTime.getMinutes().toString().padStart(2, '0');
    const seconds = dateTime.getSeconds().toString().padStart(2, '0');
  
    // Format the date and time strings
    const formattedDate = `${month} ${day} ${year}`;
    const formattedTime = `${hours}:${minutes}`;
  
    return { date: formattedDate, time: formattedTime };
  }
  confirm(flight: any): void {
    console.log(this.flightTicket)
    this.router.navigate(['/payment'], { 
      queryParams: { 
        flight: JSON.stringify(flight), // Convert flight object to JSON string
        flightTicket: JSON.stringify(this.flightTicket) // Convert flightTicket object to JSON string
      } 
    });
  }
}
