import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './sharedpages/navbar/navbar.component';
import { FootbarComponent } from './sharedpages/footbar/footbar.component';
import { HomeComponent } from './Pages/home/home.component';
import { SigninComponent } from './Pages/signin/signin.component';
import { SignupComponent } from './Pages/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient,HttpClientModule } from '@angular/common/http'
import { TrainComponent } from './Pages/train/train.component';
import { HotelsComponent } from './Pages/hotels/hotels.component';
import { CarRentalsComponent } from './Pages/car-rentals/car-rentals.component';
import { AttractionsComponent } from './Pages/attractions/attractions.component';
import { CommonModule } from '@angular/common';
import { FlightsComponent } from './Pages/flights/flights.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { buffer } from 'rxjs';
import { BusComponent } from './Pages/bus/bus.component';
import { BookingpageComponent } from './Pages/bookingpage/bookingpage.component';


@NgModule({
  declarations: [
    TrainComponent,
    AppComponent,
    HomeComponent,
    SigninComponent,
    SignupComponent,
    NavbarComponent,
    FootbarComponent,
    TrainComponent,
    HotelsComponent,
    CarRentalsComponent,
    AttractionsComponent,
    FlightsComponent,
    BusComponent,
    BookingpageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatSelectModule,
    NgxMaterialTimepickerModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
