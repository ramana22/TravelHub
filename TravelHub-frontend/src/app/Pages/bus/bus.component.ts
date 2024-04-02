import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Bus } from '../models.service';

@Component({
  selector: 'app-bus',
  templateUrl: './bus.component.html',
  styleUrl: './bus.component.css'
})
export class BusComponent {
  departureTerminal!: string;
  arrivalTerminal!: string;
  departureDate!: string;

  constructor(private trainService: TravelHubServiceService) { }

  onSubmit(): void {
    const requestMap = {
      departureTerminal: this.departureTerminal,
      arrivalTerminal: this.arrivalTerminal,
      departureDate: this.departureDate
    };

    this.trainService.searchBuses(requestMap).subscribe(
      (trains: Bus[]) => {
        // Handle the response from the service
        console.log(trains);
      },
      (error) => {
        // Handle error
        console.error(error);
      }
    );
  }
}
