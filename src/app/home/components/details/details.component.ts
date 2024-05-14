import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { HousingService } from '../../services/housing.service';
import { HousingLocation } from '../../interfaces/housinglocation';

@Component({
  selector: 'app-details',
  template:  `<p>details works! {{ housingLocationId }}</p>`,
  styleUrls: ['./details.component.css']
})

export class DetailsComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  housingLocationId = -1;
  constructor() {
  this.housingLocationId = Number(this.route.snapshot.params['id']);
  }
}