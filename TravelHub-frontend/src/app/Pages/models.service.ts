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
}
// Define interfaces or classes to represent the structure of the flight offer data

export interface FlightOffer {
  airline: string;
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

