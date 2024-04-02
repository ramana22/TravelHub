import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './Pages/home/home.component';
import { SigninComponent } from './Pages/signin/signin.component';
import { SignupComponent } from './Pages/signup/signup.component';
import { FlightsComponent } from './Pages/flights/flights.component';
import { CarRentalsComponent } from './Pages/car-rentals/car-rentals.component';
import { HotelsComponent } from './Pages/hotels/hotels.component';
import { AttractionsComponent } from './Pages/attractions/attractions.component';
import { BusComponent } from './Pages/bus/bus.component';
import { TrainComponent } from './Pages/train/train.component';
import { BookingpageComponent } from './Pages/bookingpage/bookingpage.component';


const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'signin',component:SigninComponent},
  {path:'signup',component:SignupComponent},
  {path:'flights',component:FlightsComponent},
  {path:'car',component:CarRentalsComponent},
  {path:'Hotels',component:HotelsComponent},
  {path:'bus',component:BusComponent},
  {path:'train',component:TrainComponent},
  {path:'Attract',component:AttractionsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
