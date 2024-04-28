import { Injectable } from '@angular/core';
import { Observable, map, tap } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Bus, BusTicket, Car, CarBooking, FlightTicket, HotelBooking, Profile, Review, Train, TrainTicket, User } from './models.service';
@Injectable({
  providedIn: 'root',
})
export class TravelHubServiceService {
  private selectedFlight: any;
  userEmail!: string;
  currentuser!:User;
  constructor(private http: HttpClient) {
      this.http = http.baseUrl(''); // Set base URL here
  }
  ngOnInit() {
    // Fetch userEmail from localStorage on initialization
    this.userEmail = localStorage.getItem('userEmail') || '';
  }

  public registerUserFromRemote(user: User): Observable<any> {
    this.currentuser = user;
    return this.http.post(`/register`, user);
  }
  public loginUserFromRemote(user: User): Observable<any> {
    return this.http.post(`/login`, user).pipe(
      tap(() => {
        // Assuming this.getUser returns an Observable
        this.setCurrentUser(user);
        this.userEmail = user.email; // Set userEmail
        localStorage.setItem('userEmail', this.userEmail);
      })
    );
  }
  public saveprofile(profile: Profile): Observable<any> {
    console.log(profile)
    return this.http.post(`/saveprofile`, profile);
  }
  getProfile(email: string): Observable<any> {
    return this.http.get(`/getprofile?email=${email}`);
  }
  
  setCurrentUser(user: User): void {
    this.currentuser=user;
  }
  getCurrentUser() {
    return this.currentuser;
  }
  public getReviews(): Observable<any> {
    return this.http.get(`/getreview`);
  }
  saveReview(review: Review): Observable<any> {
    return this.http.post<any>(`/saveReview`, review);
  }
  searchFlights(departureAirport: string, arrivalAirport: string, departureDate: string, returnDate: string, passengers : number): Observable<any> {
    const url = `/searchFlights`;

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
    const url = `/searchFlightsoneway`;

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
    return this.http.get<any[]>(`/searchLocations?keyword=${keyword}`);
  }
 
  searchBuses(departureTerminal: string, arrivalTerminal: string,departureDate:string) {
    const searchParams = {
      departureTerminal,
      arrivalTerminal,
      departureDate
    };
    return this.http.post<Bus[]>(`/buses`, searchParams);
  }
  searchTrains(departureStation: string, arrivalStation: string,departureDate:Date) {
    const searchParams = {
      departureStation,
      arrivalStation,
      departureDate
    };
    return this.http.post<Train[]>(`/trains`, searchParams);
  }
  searchDestination(destination: string): Observable<any> {
    return this.http.post<any>(`/getfilterlist`, { city: destination });
  }
  public getCars(pickupLocation:string,rentalStartDate:string,rentalEndDate:string):Observable<any> {
    const searchParams = {
      pickupLocation,
      rentalStartDate,
      rentalEndDate
    };
    return this.http.post("/cars",searchParams);
  }

  saveBusTicket(busTicket: BusTicket,userEmail:string){
    return this.http.post<BusTicket>(`/saveBusTicket?userEmail=${userEmail}`, busTicket);
  }

  saveTrainTicket(trainTicket: TrainTicket,userEmail:string): Observable<TrainTicket> {
    return this.http.post<TrainTicket>(`/saveTrainTicket?userEmail=${userEmail}`, trainTicket);
  }
  saveFlightTicket(flightTicket: FlightTicket,userEmail:string): Observable<TrainTicket> {
    return this.http.post<TrainTicket>(`/saveFlightTicket?userEmail=${userEmail}`, flightTicket);
  }

  saveHotelBooking(hotelBooking: HotelBooking,userEmail:string){
    return this.http.post<HotelBooking>(`/saveHotelBooking?userEmail=${userEmail}`, hotelBooking);
  }
  saveCarBooking(carBooking: CarBooking,userEmail:string): Observable<CarBooking> {

    // Construct the request body
    const requestBody = {
      carBooking: carBooking,
      userEmail: userEmail
    };

    return this.http.post<CarBooking>(`/savecarBooking`, requestBody);
  }
  setSelectedFlight(flight: any) {
    this.selectedFlight = flight;
  }

  getSelectedFlight() {
    return this.selectedFlight;
  }
  getRestaurants(destination: string): Observable<any> {
    return this.http.post<any>(`/getfilterRestaurant`, { city: destination });
  }
  changePassword(userEmail:string, oldPassword: string, newPassword: string): Observable<any> {
    const url = `/change`;
    const body = {userEmail, oldpass: oldPassword, newpass: newPassword };
    return this.http.post<any>(url, body);
  }
  getBookings(email: string): Observable<any> {
    const body = { email: email };
    return this.http.post<any>(`/getBooking`, body);
  }
  getflighttickets(email: string): Observable<any> {
    const body = { email: email };
    return this.http.post<any>(`/getflightticket`, body);
  }
}
