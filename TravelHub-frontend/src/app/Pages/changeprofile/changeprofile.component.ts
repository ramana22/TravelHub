import { Component } from '@angular/core';
import { Profile, User } from '../models.service';
import { TravelHubServiceService } from '../travel-hub-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-changeprofile',
  templateUrl: './changeprofile.component.html',
  styleUrl: './changeprofile.component.css'
})
export class ChangeprofileComponent {
  profile = new Profile();
  current!: '';
  new!: '';
  confirm!: '';
  showChangePassword:boolean=false;
  closeprofile1:Boolean=false;
  closeprofile2:Boolean=false;
  user!:User;
  public email!: string;
  currentprofile= new Profile();
  ngOnInit(): void {
    this.user = this._service.getCurrentUser();
    this.email = this.user.email;
  }
  constructor(public _service:TravelHubServiceService,public _Router:Router,public authService:AuthService){}
  toggleChangePassword() {
    this.showChangePassword = !this.showChangePassword;
  }
  togglesetpersonalinfo() {
    this.closeprofile1=!this.closeprofile1;
  }
  togglesetpersonalinfo2() {
    this.closeprofile2=!this.closeprofile2;
  }
  changePassword(oldPassword: string, newPassword: string): void {
    this._service.changePassword(this.email, oldPassword, newPassword)
      .subscribe(
        response => {
          console.log('Password changed successfully', response);
          // Handle success
        },
        error => {
          console.error('Failed to change password', error);
          // Handle error
        }
      );
  }
  getProfile() {
    this._service.getProfile(this.email).subscribe(
      (data: Profile) => {
        this.currentprofile = data;
        console.log("Response received", this.currentprofile);
      },
      error => {
        console.log("Exception occurred", error); // Log the error for debugging
      }
    );
  }
  
  
  saveChanges() {
    this._service.saveprofile(this.profile).subscribe(
      data => {
        console.log("response recieved");
      },
      error =>{
        console.log("exception occured");
        window.alert("Check yout details again")
      }
    )
    console.log('Save changes function called');

  }

  cancelChanges() {
    // Logic to cancel changes goes here
    console.log('Cancel changes function called');
    this._Router.navigate([""]);
  }
}
