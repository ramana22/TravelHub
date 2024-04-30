import { Component } from '@angular/core';

@Component({
  selector: 'app-attractions',
  templateUrl: './attractions.component.html',
  styleUrl: './attractions.component.css'
})
export class AttractionsComponent {
  generateGoogleSearchLink(restaurantName: string): string {
    const location = restaurantName; // You can modify this as per your requirement
    return "https://www.google.com/search?q=" + encodeURIComponent(location);
  }
}
