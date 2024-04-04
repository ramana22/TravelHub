import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ModelsService {
  constructor() {}
}
export class User {
  'userid': number;
  'email': string;
  'password': string;
  'confirmpassword': string;
  constructor() {}
}
export class Review {
  'rating': number;
  'comment': string;
  'date': string;
  'reviewemail': string;
  'userDetails': User;
  'service': String;
}
// Define interfaces or classes to represent the structure of the flight offer data

export interface FlightOffer {
  airline: string;
  starttime:Date;
  endtime:Date;
  instantTicketingRequired: boolean;
  nonHomogeneous: boolean;
  oneWay: boolean;
  lastTicketingDate: string;
  numberOfBookableSeats: number;
  choiceProbability: number | null;
  itineraries: Itinerary[];
  includedCheckedBagsOnly: boolean;
  fareType: string[];
  corporateCodes: string[] | null;
  refundableFare: boolean;
  noRestrictionFare: boolean;
  noPenaltyFare: boolean;
  travelerPricings: TravelerPricing[];
}

export interface Itinerary {
  duration: string;
  segments: Segment[];
}

export interface Segment {
  segmentId: string;
  cabin: string;
  fareBasis: string;
  segmentClass: string;
  includedCheckedBags: number | null;
}

export interface TravelerPricing {
  travelerId: string;
  fareOption: string;
  travelerType: string;
  price: Price;
  fareDetailsBySegment: FareDetailsBySegment[];
}

export interface Price {
  currency: string;
  total: number;
  base: number;
  fees: any; // You can define a specific type for fees if needed
  grandTotal: number;
}

export interface FareDetailsBySegment {
  segmentId: string;
  cabin: string;
  fareBasis: string;
  segmentClass: string;
  includedCheckedBags: number | null;
}

export class Airport {
  name!: string;
  detailedName!: string;
  iataCode!: string;
  city!: string;
  country!: string;
  regionCode!: string;
  latitude!: number;
  longitude!: number;
  timeZoneOffset!: string;
  travelersScore!: number;
}

export interface Bus {
arrivalformattedDateTime: any;
departureformattedDateTime: any;
  busId: number; // Unique identifier for the bus
  busNumber: number; // Bus number
  operator: string; // Operator of the bus
  departureTerminal: string; // Departure terminal of the bus
  arrivalTerminal: string; // Arrival terminal of the bus
  departureDate: string; // Date of departure (format: "YYYY-MM-DD")
  departureTime: string; // Time of departure (format: "HH:mm:ss")
  arrivalDate: string; // Date of arrival (format: "YYYY-MM-DD")
  arrivalTime: string; // Time of arrival (format: "HH:mm:ss")
  price: number; // Price of the bus ticket
  total:number;
}

export interface Train {
arrivalformattedDateTime: any;
departureformattedDateTime: any;
  trainId: number; // Unique identifier for the train
  trainNumber: number; // Train number
  operator: string; // Operator of the train
  departureStation: string; // Departure station of the train
  arrivalStation: string; // Arrival station of the train
  departureDate: string; // Date of departure (format: "YYYY-MM-DD")
  departureTime: string; // Time of departure (format: "HH:mm:ss")
  arrivalDate: string; // Date of arrival (format: "YYYY-MM-DD")
  arrivalTime: string; // Time of arrival (format: "HH:mm:ss")
  price: number; // Price of the train ticket
}


export interface Car {
  id: number; // Unique identifier for the car
  carModel: string; // Model of the car
  providerName: string; // Name of the car provider
  providerPhoneNumber: string; // Phone number of the car provider
  providerEmail: string; // Email of the car provider
  providerAddress: string; // Address of the car provider
  pickupLocation: string; // Pickup location for the car
  dropOffLocation: string; // Drop-off location for the car
  rentalStartDate: string; // Start date of the car rental (format: "YYYY-MM-DD")
  rentalEndDate: string; // End date of the car rental (format: "YYYY-MM-DD")
  totalPrice: number; // Total price of the car rental
  currency: string; // Currency used for pricing
  carImageUrl: string; // URL for the image of the car
}


// hotel-offer-response.model.ts

export interface HotelOfferResponse {
  data: HotelOffer[];
}

export interface HotelOffer {
  room: any;
  showDetails: any;
  type: string;
  hotel: Hotel;
  available: boolean;
  offers: Offer[];
  self: string;
}

export interface Hotel {
  type: string;
  hotelId: string;
  chainCode: string;
  dupeId: string;
  name: string;
  price: Price;
  cityCode: string;
  latitude: number;
  longitude: number;
}

export interface Offer {
  id: string;
  checkInDate: string;
  checkOutDate: string;
  rateCode: string;
  rateFamilyEstimated: RateFamilyEstimated;
  room: Room;
  guests: Guests;
  price: Price;
  policies: Policies;
  self: string;
}

export interface RateFamilyEstimated {
  code: string;
  type: string;
}

export interface Room {
  type: string;
  typeEstimated: TypeEstimated;
  description: Description;
}

export interface TypeEstimated {
  category: string;
  beds: number;
  bedType: string;
}

export interface Description {
  text: string;
  lang: string;
}

export interface Guests {
  adults: number;
}

export interface hotelPrice {
  currency: string;
  base: string;
  total: string;
  variations: Variations;
}

export interface Variations {
  average: Average;
  changes: Change[];
}

export interface Average {
  base: string;
}

export interface Change {
  startDate: string;
  endDate: string;
  base: string;
}

export interface Policies {
  cancellations: Cancellation[];
  paymentType: string;
}

export interface Cancellation {
  description: Description;
  type: string;
}

export class Hotel{
  'hotelid':number;
  'hotelname':String;
  'rating':number;
  'address':address;
  'image':Image;
  'room':Room;
}
export class Room{
  'roomnumber':number;
  'image':Image; 
  'bill':Bill;  
}
export class Image{
  'imageUrl':string;
}
export class address{
  'street':string;
  'city':string;
  'state':string;
  'postalCode':number;
}
export class Bill{
  'amount':number;
}


// Define the TypeScript interfaces for Address and GeoCode
export interface Address {
  cityName: string;
  countryName: string;
  regionCode: string;
}

export interface GeoCode {
  latitude: number;
  longitude: number;
}

export interface LocationData {
  name: string;
  detailedName: string;
  iataCode: string;
  address: Address;
  geoCode: GeoCode;
  timeZoneOffset: string;
}



export interface Payment {
  id: number;
  paymentMethod: string;
  cardNumber: string;
  expirationDate: Date;
  cvv: string;
  amount: number;
  status: string;
}
export interface BusTicket {
  id: number;
  bus: Bus;
  traveler: Traveler;
  payment: Payment;
  user: User;
}
export interface Traveler {
  fullName: string;
  emailAddress: string;
  phoneNumber: string;
  dateOfBirth: Date;
  people: number;
}

export interface TrainTicket {
  id?: number;
  train: Train;
  user: User;
  traveler: Traveler;
  payment: Payment;
}






