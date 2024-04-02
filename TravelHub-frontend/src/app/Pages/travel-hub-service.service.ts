import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Review, User } from './models.service';

@Injectable({
  providedIn: 'root',
})
export class TravelHubServiceService {
  searchAirports(searchQuery: any) {
    throw new Error('Method not implemented.');
  }
  private baseUrl = 'http://localhost:8080';
  currentuser: User = new User();
  constructor(public http: HttpClient) {}
  public registerUserFromRemote(user: User): Observable<any> {
    this.currentuser = user;
    return this.http.post('http://localhost:8080/register', user);
  }
  public loginUserFromRemote(user: User): Observable<any> {
    // Assuming this.getUser returns an Observable
    this.currentuser = user;
    return this.http.post('http://localhost:8080/login', user);
  }
  public getReviews(): Observable<any> {
    return this.http.get('http://localhost:8080/getreview');
  }
  saveReview(review: Review): Observable<any> {
    return this.http.post<any>('http://localhost:8080/saveReview', review);
  }
  searchFlights(departureAirport: string, arrivalAirport: string, departureDate: string, returnDate: string, passengers : number): Observable<any> {
    const url = `${this.baseUrl}/searchFlights`;

    // Construct the query parameters
    const params = {
      origin: departureAirport,
      destination: arrivalAirport,
      departDate: departureDate,
      returnDate: returnDate,
      adults: passengers
    };

    // Make the HTTP GET request with the query parameters
    return this.http.get(url, { params });
  }
  getAirports(keyword: string) {
    return this.http.get<any[]>(`http://localhost:8080/searchLocations?keyword=${keyword}`);
  }
}
