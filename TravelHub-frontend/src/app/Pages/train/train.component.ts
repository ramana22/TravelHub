import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Train } from '../models.service';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrl: './train.component.css'
})
export class TrainComponent {
  departureStation!: string;
  arrivalStation!: string;
  departureDate!: string;

  constructor(private trainService: TravelHubServiceService) { }

  onSubmit(): void {
    const requestMap = {
      departureStation: this.departureStation,
      arrivalStation: this.arrivalStation,
      departureDate: this.departureDate
    };

    this.trainService.searchTrains(requestMap).subscribe(
      (trains: Train[]) => {
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
