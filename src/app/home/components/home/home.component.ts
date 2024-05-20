import { AfterViewInit, Component, ElementRef, ViewChild, inject } from '@angular/core';
import { HousingLocationComponent } from '../housing-location/housing-location.component';
import { HousingLocation } from '../../interfaces/housinglocation';
import { HousingService } from '../../services/housing.service';


@Component({
  selector: 'app-home',
  template: `
<section>
<form (ngSubmit)="filterResults(filterInput.value)">
<input type="text" placeholder="Filter by city" #filter>
<!-- <button class="primary" type="button" (click)="filterResults(filter.value)">Search</button> -->
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


export class HomeComponent implements AfterViewInit {
  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';
 
housingLocationList: HousingLocation[] = [];
filteredLocationList: HousingLocation[] = [];

housingService: HousingService = inject(HousingService);

@ViewChild('filterInput') filterInput!: ElementRef; //para obtener una referencia al elemento del input en el DOM

constructor() {
  this.housingLocationList = this.housingService.getAllHousingLocations();
  this.filteredLocationList = this.housingLocationList;
  }

  ngAfterViewInit() {  // esto es para aplicar el filtro si pulsamos "Enter" tambien (en vez del boton search)
    this.filterInput.nativeElement.addEventListener('keydown', (event: KeyboardEvent) => {
      if (event.key === 'Enter') {
        event.preventDefault();
        this.filterResults(this.filterInput.nativeElement.value);
      }
    });
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
