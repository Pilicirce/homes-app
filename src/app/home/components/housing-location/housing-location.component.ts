import { Component, Input } from '@angular/core';
import { HousingLocation } from '../../interfaces/housinglocation';

@Component({
  selector: 'app-housing-location',
  templateUrl: './housing-location.component.html',
  styleUrls: ['./housing-location.component.css']
})
export class HousingLocationComponent {

  @Input() 
    housingLocation!: HousingLocation; 
    //ojo al operador !, porque la entrada espera que se pase el valor.
    //el valor de esta propiedad no será nulo ni indefinido
}
