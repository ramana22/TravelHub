import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Bus, BusTicket, Car, CarBooking, FlightTicket, HotelBooking, Profile, Review, Train, TrainTicket, User } from './models.service';

@Injectable({
  providedIn: 'root',
})
export class TravelHubServiceService {
  [x: string]: any;
  private selectedFlight: any;
  searchAirports(searchQuery: any) {
    throw new Error('Method not implemented.');
  }
  private baseUrl = 'http://localhost:8080';
  currentuser!:User;
  constructor(public http: HttpClient) {}
  public registerUserFromRemote(user: User): Observable<any> {
    this.currentuser = user;
    return this.http.post('http://localhost:8080/register', user);
  }
  public loginUserFromRemote(user: User): Observable<any> {
    // Assuming this.getUser returns an Observable
    this.setCurrentUser(user)
    return this.http.post('http://localhost:8080/login', user);
  }
  public saveprofile(profile: Profile): Observable<any> {
    console.log(profile)
    return this.http.post('http://localhost:8080/saveprofile', profile);
  }
  getProfile(email: string): Observable<any> {
    return this.http.get(`http://localhost:8080/getprofile?email=${email}`);
  }
  setCurrentUser(user: User): void {
    this.currentuser = user;
  }

  getCurrentUser() {
    return this.currentuser;
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
  searchFlightsoneway(departureAirport: string, arrivalAirport: string, departureDate: string, passengers : number): Observable<any> {
    const url = `${this.baseUrl}/searchFlightsoneway`;

    // Construct the query parameters
    const params = {
      origin: departureAirport,
      destination: arrivalAirport,
      departDate: departureDate,
      adults: passengers
    };

    // Make the HTTP GET request with the query parameters
    return this.http.get(url, { params });
  }
  getAirports(keyword: string) {
    return this.http.get<any[]>(`http://localhost:8080/searchLocations?keyword=${keyword}`);
  }
 
  searchBuses(departureTerminal: string, arrivalTerminal: string,departureDate:string) {
    const searchParams = {
      departureTerminal,
      arrivalTerminal,
      departureDate
    };
    return this.http.post<Bus[]>("http://localhost:8080/buses", searchParams);
  }
  searchTrains(departureStation: string, arrivalStation: string,departureDate:Date) {
    const searchParams = {
      departureStation,
      arrivalStation,
      departureDate
    };
    return this.http.post<Train[]>("http://localhost:8080/trains", searchParams);
  }
  searchDestination(destination: string): Observable<any> {
    return this.http.post<any>("http://localhost:8080/getfilterlist", { city: destination });
  }
  public getCars(pickupLocation:string,rentalStartDate:string,rentalEndDate:string):Observable<any> {
    const searchParams = {
      pickupLocation,
      rentalStartDate,
      rentalEndDate
    };
    return this.http.post("http://localhost:8080/cars",searchParams);
  }

  saveBusTicket(busTicket: BusTicket, userEmail: string){
    return this.http.post<BusTicket>(`${this.baseUrl}/saveBusTicket?userEmail=${userEmail}`, busTicket);
  }

  saveTrainTicket(trainTicket: TrainTicket, userEmail: string): Observable<TrainTicket> {
    return this.http.post<TrainTicket>(`${this.baseUrl}/saveTrainTicket?userEmail=${userEmail}`, trainTicket);
  }
  saveFlightTicket(flightTicket: FlightTicket, userEmail: string): Observable<TrainTicket> {
    return this.http.post<TrainTicket>(`${this.baseUrl}/saveFlightTicket?userEmail=${userEmail}`, flightTicket);
  }

  saveHotelBooking(hotelBooking: HotelBooking, userEmail: string){
    return this.http.post<HotelBooking>(`${this.baseUrl}/saveHotelBooking?userEmail=${userEmail}`, hotelBooking);
  }
  saveCarBooking(carBooking: CarBooking, userEmail: string): Observable<CarBooking> {

    // Construct the request body
    const requestBody = {
      carBooking: carBooking,
      userEmail: userEmail
    };

    return this.http.post<CarBooking>(`${this.baseUrl}/savecarBooking`, requestBody);
  }
  setSelectedFlight(flight: any) {
    this.selectedFlight = flight;
  }

  getSelectedFlight() {
    return this.selectedFlight;
  }
  getRestaurants(destination: string): Observable<any> {
    return this.http.post<any>("http://localhost:8080/getfilterRestaurant", { city: destination });
  }
  changePassword(email: string, oldPassword: string, newPassword: string): Observable<any> {
    const url = `${this.baseUrl}/change`;
    const body = { email, oldpass: oldPassword, newpass: newPassword };
    return this.http.post<any>(url, body);
  }
}
