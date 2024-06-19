import { Component, inject } from '@angular/core';
import { HousingLocation } from '../../interfaces/housinglocation';
import { HousingService } from '../../services/housing.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})


export class HomeComponent {
  readonly baseUrl = 'https://angular.io/assets/images/tutorials/faa';
 
  housingLocationList: HousingLocation[] = [];
  filteredLocationList: HousingLocation[] = [];

  housingService: HousingService = inject(HousingService);

  constructor() {
    this.housingLocationList = this.housingService.getAllHousingLocations();
    console.log('Initial housing locations:', this.housingLocationList);
    this.filteredLocationList = this.housingLocationList;

    // this.housingService.getAllHousingLocations().subscribe((data: HousingLocation[]) => {
    //   this.housingLocationList = data;
    //   console.log('Initial housing locations:', this.housingLocationList);
    //   this.filteredLocationList = this.housingLocationList;
    // });
  }

  filterResults(text: string) {
    console.log('Filtering results for:', text);
    if (!text) {
      this.filteredLocationList = this.housingLocationList;
      console.log('No filter text, showing all locations');
      return;
    }
    this.filteredLocationList = this.housingLocationList.filter(
      housingLocation => housingLocation.city.toLowerCase().includes(text.toLowerCase())
    );
    console.log('Filtered locations:', this.filteredLocationList);
  }

  logClick() {
    console.log('Button clicked');
  }

  onSubmit(event: Event, text: string) {
    event.preventDefault();  // Evitar el comportamiento predeterminado de envío del formulario
    console.log('Form submitted with text:', text);
    this.filterResults(text);
  }
}