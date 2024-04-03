import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Bus } from '../models.service';
import { Router } from '@angular/router';

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

  constructor(private busservice: TravelHubServiceService, private router: Router) { }

  ngOnInit(): void {
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
