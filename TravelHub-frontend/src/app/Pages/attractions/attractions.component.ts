import { Component } from '@angular/core';

@Component({
  selector: 'app-attractions',
  templateUrl: './attractions.component.html',
  styleUrl: './attractions.component.css'
})
export class AttractionsComponent {
  generateGoogleSearchLink(location: string): string {
    return "https://www.google.com/search?q=" + encodeURIComponent(location);
  }
}
