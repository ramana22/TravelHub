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
