import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Train } from '../models.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrl: './train.component.css'
})
export class TrainComponent {
  Trains: Train[] = [];
  departureStation = "";
  arrivalStation = "";
  departureDate!: Date;
  weatherData: any;
  isLoggedIn!: Boolean;

  constructor(private trainService: TravelHubServiceService, private router: Router,private authservice:AuthService) { }

  ngOnInit(): void {
    this.authservice.isLoggedIn$.subscribe((isLoggedIn: boolean) => {
      this.isLoggedIn = isLoggedIn;
    });
  }
  selectdeparture(train:Train): void {
    if (!this.isLoggedIn) {
      const confirmLogin = window.confirm('Please login to proceed. Do you want to login now?');
      if (confirmLogin) {
        this.router.navigate(['/signin']); // Redirect to signin page if user confirms
      }
    } else {
      console.log("hello")
      this.router.navigate(['/trainticket'], { queryParams: { train: JSON.stringify(train) } });
    }
  }
  searchTrains(): void {
    console.log('Departure Station:', this.departureStation);
    console.log('Arrival Station:', this.arrivalStation);
    console.log('Departure Date:', this.departureDate);

    if (this.departureStation && this.arrivalStation && this.departureDate) {
      this.trainService.searchTrains(this.departureStation, this.arrivalStation, this.departureDate)
        .subscribe(
          (data: Train[]) => {
            this.Trains = data.map(train => ({
              ...train,
              departureformattedDateTime: this.formatDateTime(train.departureDate, train.departureTime),
              arrivalformattedDateTime: this.formatDateTime(train.arrivalDate, train.arrivalTime)
            }));
            console.log(this.Trains);
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
