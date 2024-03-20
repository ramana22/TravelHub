import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './sharedpages/navbar/navbar.component';
import { FootbarComponent } from './sharedpages/footbar/footbar.component';
import { HomeComponent } from './Pages/home/home.component';
import { SigninComponent } from './Pages/signin/signin.component';
import { SignupComponent } from './Pages/signup/signup.component';
import { FormsModule } from '@angular/forms';
import { HttpClient,HttpClientModule } from '@angular/common/http'
import { TrainComponent } from './Pages/train/train.component';
import { HotelsComponent } from './Pages/hotels/hotels.component';
import { CarRentalsComponent } from './Pages/car-rentals/car-rentals.component';
import { AttractionsComponent } from './Pages/attractions/attractions.component';

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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
