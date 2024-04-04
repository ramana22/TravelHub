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
import { BusticketComponent } from './Pages/busticket/busticket.component';
import { TrainticketComponent } from './Pages/trainticket/trainticket.component';
import { CarbookingComponent } from './Pages/carbooking/carbooking.component';
import { HotelbookingComponent } from './Pages/hotelbooking/hotelbooking.component';


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
  {path:'busticket',component:BusticketComponent},
  {path:'trainticket',component:TrainticketComponent},
  {path:'carbooking',component:CarbookingComponent},
  {path:'hotelbooking',component:HotelbookingComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
