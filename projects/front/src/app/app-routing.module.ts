import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/components/home/home.component';
import { DetailsComponent } from './home/components/details/details.component';
import { HousingLocationComponent } from './home/components/housing-location/housing-location.component';
import { CreateHousingComponent } from './home/components/create-housing/create-housing.component';
import { EditHousingComponent } from './home/components/edit-housing/edit-housing.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full', data: { title: 'Home page' } },
  { path: 'details/:id', component: DetailsComponent, data: { title: 'Home details' } },
  { path: 'create', component: CreateHousingComponent, data: { title: 'Create Housing' } },
  { path: 'edit/:id', component: EditHousingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
