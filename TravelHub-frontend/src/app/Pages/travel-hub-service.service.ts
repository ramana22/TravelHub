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
  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {
  }
  ngOnInit() {
    // Fetch userEmail from localStorage on initialization
    this.userEmail = localStorage.getItem('userEmail') || '';
  }

  public registerUserFromRemote(user: User): Observable<any> {
    this.currentuser = user;
    return this.http.post(`${this.baseUrl}/register`, user);
  }
  public loginUserFromRemote(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, user).pipe(
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
    return this.http.post(`${this.baseUrl}/saveprofile`, profile);
  }
  getProfile(email: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/getprofile?email=${email}`);
  }
  
  setCurrentUser(user: User): void {
    this.currentuser=user;
  }
  getCurrentUser() {
    return this.currentuser;
  }
  public getReviews(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getreview`);
  }
 
  saveReview(review: Review): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/saveReview`, review);
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
    return this.http.get<any[]>(`${this.baseUrl}/searchLocations?keyword=${keyword}`);
  }
 
  searchBuses(departureTerminal: string, arrivalTerminal: string,departureDate:string) {
    const searchParams = {
      departureTerminal,
      arrivalTerminal,
      departureDate
    };
    return this.http.post<Bus[]>(`${this.baseUrl}/buses`, searchParams);
  }
  searchTrains(departureStation: string, arrivalStation: string,departureDate:Date) {
    const searchParams = {
      departureStation,
      arrivalStation,
      departureDate
    };
    return this.http.post<Train[]>(`${this.baseUrl}/trains`, searchParams);
  }
  searchDestination(destination: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/getfilterlist`, { city: destination });
  }
  public getCars(pickupLocation:string,rentalStartDate:string,rentalEndDate:string):Observable<any> {
    const searchParams = {
      pickupLocation,
      rentalStartDate,
      rentalEndDate
    };
    return this.http.post(`${this.baseUrl}/cars`,searchParams);
  }

  saveBusTicket(busTicket: BusTicket,userEmail:string){
    return this.http.post<BusTicket>(`${this.baseUrl}/saveBusTicket?userEmail=${userEmail}`, busTicket);
  }

  saveTrainTicket(trainTicket: TrainTicket,userEmail:string): Observable<TrainTicket> {
    return this.http.post<TrainTicket>(`${this.baseUrl}/saveTrainTicket?userEmail=${userEmail}`, trainTicket);
  }
  saveFlightTicket(flightTicket: FlightTicket,userEmail:string): Observable<TrainTicket> {
    return this.http.post<TrainTicket>(`${this.baseUrl}/saveFlightTicket?userEmail=${userEmail}`, flightTicket);
  }

  saveHotelBooking(hotelBooking: HotelBooking,userEmail:string){
    return this.http.post<HotelBooking>(`${this.baseUrl}/saveHotelBooking?userEmail=${userEmail}`, hotelBooking);
  }
  saveCarBooking(carBooking: CarBooking, userEmail: string): Observable<CarBooking> {
    return this.http.post<CarBooking>(`${this.baseUrl}/savecarBooking?userEmail=${userEmail}`, carBooking);
  }
  
  setSelectedFlight(flight: any) {
    this.selectedFlight = flight;
  }

  getSelectedFlight() {
    return this.selectedFlight;
  }
  getRestaurants(destination: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/getfilterRestaurant`, { city: destination });
  }
  changePassword(userEmail:string, oldPassword: string, newPassword: string): Observable<any> {
    const url = `${this.baseUrl}/change`;
    const body = {userEmail, oldpass: oldPassword, newpass: newPassword };
    return this.http.post<any>(url, body);
  }
  getBookings(email: string): Observable<any> {
    const body = { email: email };
    return this.http.post<any>(`${this.baseUrl}/getBooking`, body);
  }
  getflighttickets(email: string): Observable<any> {
    const body = { email: email };
    return this.http.post<any>(`${this.baseUrl}/getflightticket`, body);
  }
  gettraintickets(email: string): Observable<any> {
    const body = { email: email };
    return this.http.post<any>(`${this.baseUrl}/gettrainticket`, body);
  }
  getbustickets(email: string): Observable<any> {
    const body = { email: email };
    return this.http.post<any>(`${this.baseUrl}/getbusticket`, body);
  }
  getcarbookings(email: string): Observable<any> {
    const body = { email: email };
    return this.http.post<any>(`${this.baseUrl}/getcarbooking`, body);
  }
  deletebusticket(email: string,id:number){
    const body = { email: email ,
                   busticketid :id
    };
    return this.http.post<any>(`${this.baseUrl}/deletebusticket`, body);

  }
  deletetrainticket(email: string,id:number){
    const body = { email: email ,
                   trainticketid :id
    };
    return this.http.post<any>(`${this.baseUrl}/deletetrainticket`, body);

  }
  deleteflightticket(email: string,id:number){
    const body = { email: email ,
                   flightticketid :id
    };
    return this.http.post<any>(`${this.baseUrl}/deleteflightticket`, body);

  }
  deletehotelbooking(email: string,id:number){
    const body = { email: email ,
                   hotelbookingid :id
    };
    return this.http.post<any>(`${this.baseUrl}/deletehotelbooking`, body);

  }
  deletecarbooking(email: string,id:number){
    const body = { email: email ,
                   carbookingid :id
    };
    return this.http.post<any>(`${this.baseUrl}/deletecarbooking`, body);

  }
  getnotify(email: string): Observable<any> {
    const body = { email: email };
    return this.http.post<any>(`${this.baseUrl}/getnotify`, body);
  }
}
