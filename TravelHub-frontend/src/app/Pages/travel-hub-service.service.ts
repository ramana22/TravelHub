import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Bus, BusTicket, Car, HotelBooking, HotelOffer, Review, Train, TrainTicket, User } from './models.service';

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
  getHotelOffers(cityCode: string,checkInDate:string,adult:number, radius?: number): Observable<HotelOffer[]> {
    let params = new HttpParams().set('cityCode', cityCode);
    if (radius) {
      params = params.set('radius', radius.toString());
    }
    if (adult) {
      params = params.set('adult', adult.toString());
    }
    if (checkInDate) {
      params = params.set('checkInDate',checkInDate);
    }

    return this.http.get<HotelOffer[]>(`${this.baseUrl}/hotels`, { params });
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

  saveBusTicket(busTicket: BusTicket, userEmail: string): Observable<BusTicket> {
    return this.http.post<BusTicket>(`${this.baseUrl}/saveBusTicket?userEmail=${userEmail}`, busTicket);
  }

  saveTrainTicket(trainTicket: TrainTicket, userEmail: string): Observable<TrainTicket> {
    return this.http.post<TrainTicket>(`${this.baseUrl}/saveTrainTicket?userEmail=${userEmail}`, trainTicket);
  }

  saveHotelBooking(hotelBooking: HotelBooking, userEmail: string): Observable<HotelBooking> {
    return this.http.post<HotelBooking>(`${this.baseUrl}/saveHotelBooking?userEmail=${userEmail}`, hotelBooking);
  }setSelectedFlight(flight: any) {
    this.selectedFlight = flight;
  }

  getSelectedFlight() {
    return this.selectedFlight;
  }
  getRestaurants(destination: string): Observable<any> {
    return this.http.post<any>("http://localhost:8080/getfilterRestaurant", { city: destination });
  }


}
