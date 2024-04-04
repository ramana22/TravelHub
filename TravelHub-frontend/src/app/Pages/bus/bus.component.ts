import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Bus } from '../models.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-bus',
  templateUrl: './bus.component.html',
  styleUrl: './bus.component.css'
})
export class BusComponent {
  Buses: Bus[] = [];
  departureTerminal = "";
  arrivalTerminal = "";
  departureDate!:string;
  isLoggedIn!: boolean;

  constructor(private busservice: TravelHubServiceService, private router: Router,private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.isLoggedIn$.subscribe((isLoggedIn: boolean) => {
      this.isLoggedIn = isLoggedIn;
    });
  }
  selectdeparture(bus: Bus): void {
    if (!this.isLoggedIn) {
      const confirmLogin = window.confirm('Please login to proceed. Do you want to login now?');
      if (confirmLogin) {
        this.router.navigate(['/signin']); // Redirect to signin page if user confirms
      }
    } else {
      console.log("hello")
      this.router.navigate(['/busticket'], { queryParams: { bus: JSON.stringify(bus) } });
    }
  }

  searchBuses(): void {
    console.log('Departure Station:', this.departureTerminal);
    console.log('Arrival Station:', this.arrivalTerminal);
    console.log('Departure Date:', this.departureDate);

    if (this.departureTerminal && this.arrivalTerminal && this.departureDate) {
      this.busservice.searchBuses(this.departureTerminal, this.arrivalTerminal, this.departureDate)
        .subscribe(
          (data: Bus[]) => {
            this.Buses = data.map(Bus => ({
              ...Bus,
              departureformattedDateTime: this.formatDateTime(Bus.departureDate, Bus.departureTime),
              arrivalformattedDateTime: this.formatDateTime(Bus.arrivalDate, Bus.arrivalTime)
            }));
            console.log(this.Buses);
          },
          (error) => {
            console.error('Error:', error);
          }
        );
    } else {
      console.error('Please provide values for departure station, arrival station, and departure date.');
    }
  }
  private formatDateTime(date: string, time: string): string {
    const dateTimeString = `${date} ${time}`;
    const formattedDateTime = new Date(dateTimeString).toLocaleString('en-US', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
      hour12: true
    });
    return formattedDateTime;
  }
}
