import { Component } from '@angular/core';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { User } from '../models.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  user=new User();
  msg="";
  constructor(public _service:TravelHubServiceService,public _Router:Router,public authService:AuthService){}
  ngOnInit(){}
  public loginUser(){
    this._service.loginUserFromRemote(this.user).subscribe(
      data => {
        console.log("response recieved");
        this.authService.login();
        this._service.currentuser = this.user;
        this._Router.navigate([""]);
      },
      error =>{
        console.log("exception occured");
        window.alert("Check yout details again")
        this.msg="user already exists";
      }
    )
  }
  
}
