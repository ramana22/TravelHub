import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Review, User } from './models.service';

@Injectable({
  providedIn: 'root',
})
export class TravelHubServiceService {
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
    return this.http.get('http://localhost:8098/getreview');
  }
  saveReview(review: Review): Observable<any> {
    return this.http.post<any>('http://localhost:8098/saveReview', review);
  }
}
