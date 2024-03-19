import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModelsService {

  constructor() { }
}
export class User {
  'userid':number;
  'email':string;
  'password':string;
  'confirmpassword':string;
  constructor(){}
}
