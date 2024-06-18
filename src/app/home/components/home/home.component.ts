import { Component, inject } from '@angular/core';
import { HousingLocation } from '../../interfaces/housinglocation';
import { HousingService } from '../../services/housing.service';


@Component({
  selector: 'app-home',
  template: `
<section>
<form (ngSubmit)="filterResults(filterInput.value)">
<input type="text" placeholder="Filter by city" #filterInput>
<!-- <button class="primary" type="button" (click)="filterResults(filterInput.value)">Search</button> -->
<button class="primary" type="submit">Search</button>
</form>
</section>
<section class="results">
<app-housing-location *ngFor="let housingLocation of filteredLocationList" 
      [housingLocation]="housingLocation"></app-housing-location>
</section>
`,

  styleUrls: ['./home.component.css'],
})


export class HomeComponent {
  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';
 
housingLocationList: HousingLocation[] = [];
filteredLocationList: HousingLocation[] = [];

housingService: HousingService = inject(HousingService);

constructor() {
  this.housingLocationList = this.housingService.getAllHousingLocations();
  this.filteredLocationList = this.housingLocationList;
  }

  
  filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = this.housingLocationList;
      return;
    }
    this.filteredLocationList = this.housingLocationList.filter(
      housingLocation => housingLocation?.city.toLowerCase().includes(text.toLowerCase())
    );
  }
}
