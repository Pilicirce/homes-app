import { Component, OnInit, inject } from '@angular/core';
import { HousingLocation } from '../../interfaces/housinglocation';
import { HousingService } from '../../services/housing.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})


export class HomeComponent implements OnInit {
 
  housingLocationList: HousingLocation[] = [];
  filteredLocationList: HousingLocation[] = [];
  pagedHousingLocationList: HousingLocation[] = [];
  pageSize = 6;
  currentPage = 0;

constructor(private housingService: HousingService) {}


  ngOnInit(): void {
    this.housingService.getAllHousingLocations().subscribe({
      
next: data => {
      this.housingLocationList = data;
      this.filteredLocationList = data;
      this.updatePagedList();
    },
      error: err => console.error('Error loading housing locations', err)
    });
  }

  //El text es un campoc opcional, que se utiliza para filtrar los resultados de las ubicaciones de viviendas. Si se proporciona un texto, se filtran las ubicaciones para incluir solo aquellas cuyo nombre de ciudad incluye el texto (ignorando mayúsculas y minúsculas). Si no se proporciona texto, se muestran todas las ubicaciones. Después de filtrar, se actualiza la lista paginada para reflejar los resultados filtrados.
  filterResults(text: string) {
    console.log('Filtering results for:', text);
    this.filteredLocationList = text ? this.housingLocationList.filter(
      housingLocation => housingLocation.city.toLowerCase().includes(text.toLowerCase())
    ) : this.housingLocationList;
    console.log('Filtered locations:', this.filteredLocationList);
    this.updatePagedList();
  }

  handlePageEvent(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.updatePagedList();
  }

  updatePagedList() {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pagedHousingLocationList = this.filteredLocationList.slice(startIndex, endIndex);
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