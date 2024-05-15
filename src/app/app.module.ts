import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/components/home/home.component';
import { HousingLocationComponent } from './home/components/housing-location/housing-location.component';
import { DetailsComponent } from './home/components/details/details.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HousingLocationComponent,
    DetailsComponent,  //Declara DetailsComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule // Importa ReactiveFormsModule aquí
  ],
  providers: [],
  
  bootstrap: [
    AppComponent]
})
export class AppModule { }
