import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { ActivatedRoute, Router } from '@angular/router';

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
  confirm(flight:any): void {
    this.router.navigate(['/payment'], { queryParams: { flight: JSON.stringify(flight) } });
  }
  
}
