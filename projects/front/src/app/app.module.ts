import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/components/home/home.component';
import { DetailsComponent } from './home/components/details/details.component';
import { HousingLocationComponent } from './home/components/housing-location/housing-location.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { ApplyFormComponent } from './home/components/apply-form/apply-form.component';
import { MatPaginatorModule } from '@angular/material/paginator';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HousingLocationComponent,
    DetailsComponent,
    ApplyFormComponent, 
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule, 
    CommonModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
    MatPaginatorModule
  ],
  providers: [],
  
  bootstrap: [
    AppComponent]
})
export class AppModule { }
