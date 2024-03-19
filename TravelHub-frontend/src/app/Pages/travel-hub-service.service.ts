import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'c:/Users/Sivva Nagadinesh/Vscode/TravelHub/TravelHub-frontend/src/app/Pages/models.service';

@Injectable({
  providedIn: 'root'
})
export class TravelHubServiceService {
  currentuser: User = new User ;
  constructor(public http :HttpClient) {
  }
  public registerUserFromRemote(user:User):Observable<any>{
    this.currentuser=user;
    return this.http.post("http://localhost:8080/register",user);
  }
  public loginUserFromRemote(user: User): Observable<any> {
    // Assuming this.getUser returns an Observable
    this.currentuser=user;
    return this.http.post("http://localhost:8080/login", user);
  }
}
