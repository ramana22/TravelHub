import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  reviews: Review[] = [];
  review: Review = new Review();
  currentRating: number = 0;
  stars = [1, 2, 3, 4, 5];
  weatherData: any;
  city: string = 'Texas';
  constructor(public service: TravelHubServiceService) {}
  saveReview() {
    this.review.rating = this.currentRating;
    this.review.date = this.getCurrentDate();
    this.review.userDetails = this.service.currentuser;
    this.service.saveReview(this.review).subscribe(
      (response) => {
        console.log('review saved successfully:', response);
        window.alert('review submitted successfully');
        // Update notifications array or perform other actions as needed
      },
      (error) => {
        console.error('Error saving review', error);
        // Handle error (e.g., show an error message to the user)
      }
    );
  }
  ngOnInit() {
    this.service.getReviews().subscribe((data) => {
      this.reviews = data;
    });
  }
  getCurrentDate() {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // Months are zero-based
    const day = currentDate.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }
  rateStar(rating: number) {
    this.currentRating = rating;
    // Additional logic when the rating changes in the home component
  }
  get sortedReviews(): Review[] {
    return this.reviews
      .slice()
      .sort((a, b) => b.comment.length - a.comment.length);
  }
}
