import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { User } from '../models.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  user=new User();
  msg="";
  constructor(public _service:TravelHubServiceService,public _Router: Router, public authService:AuthService){}
  ngOnInit(){}
  public registerUser(){
    this._service.registerUserFromRemote(this.user).subscribe(
      data => {
        console.log("response recieved");
        this.authService.login();
        this._service.currentuser = this.user;
        this._Router.navigate([""]);
        const userEmail = data.email; // Assuming email is in response.data
        if (userEmail) {
          localStorage.setItem('userEmail', userEmail);
        } else {
          console.error("Email not found in login response.");
        }
      },
      error =>{
        console.log("exception occured");
        this.msg="Bad credentials ";
      }
    )
  }
}
