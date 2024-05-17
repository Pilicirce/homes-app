import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/components/home/home.component';
import { DetailsComponent } from './home/components/details/details.component';
import { HousingLocationComponent } from './home/components/housing-location/housing-location.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full', data: { title: 'Home page' } },
  { path: 'details/:id', component: DetailsComponent, data: { title: 'Home details' } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
