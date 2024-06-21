import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HousingLocation } from '../interfaces/housinglocation';

@Injectable({
  providedIn: 'root'
})
export class HousingService { 
  //está configurado para proporcionar una lista de ubicaciones de viviendas
  baseUrl = 'https://angular.io/assets/images/tutorials/faa';

 // private baseUrl = 'https://api.realtor.com/housing'; // EJEMPLO!! no es la buena! URL de mi API REST (Realtor.com API)

  
  housingLocationList: HousingLocation[] = [
    {
        id: 0,
        name: 'Acme Fresh Start Housing',
        city: 'Chicago',
        state: 'IL',
        photo: `${this.baseUrl}/bernard-hermant-CLKGGwIBTaY-unsplash.jpg`,
        availableUnits: 4,
        wifi: true,
        laundry: true,
        bedrooms: 4,
        parking: true
    },
    {
        id: 1,
        name: 'A113 Transitional Housing',
        city: 'Santa Monica',
        state: 'CA',
        photo: `${this.baseUrl}/brandon-griggs-wR11KBaB86U-unsplash.jpg`,
        availableUnits: 0,
        wifi: false,
        laundry: true,
        bedrooms: 1,
        parking: false
    },
    {
        id: 2,
        name: 'Warm Beds Housing Support',
        city: 'Juneau',
        state: 'AK',
        photo: `${this.baseUrl}/i-do-nothing-but-love-lAyXdl1-Wmc-unsplash.jpg`,
        availableUnits: 1,
        wifi: false,
        laundry: false,
        bedrooms: 3,
        parking: true
    },
    {
        id: 3,
        name: 'Homesteady Housing',
        city: 'Chicago',
        state: 'IL',
        photo: `${this.baseUrl}/ian-macdonald-W8z6aiwfi1E-unsplash.jpg`,
        availableUnits: 1,
        wifi: true,
        laundry: false,
        bedrooms: 2,
        parking: true
    },
    {
        id: 4,
        name: 'Happy Homes Group',
        city: 'Gary',
        state: 'IN',
        photo: `${this.baseUrl}/krzysztof-hepner-978RAXoXnH4-unsplash.jpg`,
        availableUnits: 1,
        wifi: true,
        laundry: false,
        bedrooms: 2,
        parking: false
    },
    {
        id: 5,
        name: 'Hopeful Apartment Group',
        city: 'Oakland',
        state: 'CA',
        photo: `${this.baseUrl}/r-architecture-JvQ0Q5IkeMM-unsplash.jpg`,
        availableUnits: 2,
        wifi: true,
        laundry: true,
        bedrooms: 1,
        parking: true
    },
    {
        id: 6,
        name: 'Seriously Safe Towns',
        city: 'Oakland',
        state: 'CA',
        photo: `${this.baseUrl}/phil-hearing-IYfp2Ixe9nM-unsplash.jpg`,
        availableUnits: 5,
        wifi: true,
        laundry: true,
        bedrooms: 3,
        parking: true
    },
    {
        id: 7,
        name: 'Hopeful Housing Solutions',
        city: 'Oakland',
        state: 'CA',
        photo: `${this.baseUrl}/r-architecture-GGupkreKwxA-unsplash.jpg`,
        availableUnits: 2,
        wifi: true,
        laundry: true,
        bedrooms: 3,
        parking: true
    },
    {
        id: 8,
        name: 'Seriously Safe Towns',
        city: 'Oakland',
        state: 'CA',
        photo: `${this.baseUrl}/saru-robert-9rP3mxf8qWI-unsplash.jpg`,
        availableUnits: 10,
        wifi: false,
        laundry: false,
        bedrooms: 2,
        parking: false
    },
    {
    id: 9,
    name: 'Capital Safe Towns',
    city: 'Portland',
    state: 'OR',
    photo: `${this.baseUrl}/webaliser-_TPTXZd9mOo-unsplash.jpg`,
    availableUnits: 6,
    wifi: true,
    laundry: true,
    bedrooms: 5,
    parking: true
    }
    ];


    constructor() { }
    
   getAllHousingLocations(): HousingLocation[] {
       return this.housingLocationList;
       }

    // getAllHousingLocations(): Observable<HousingLocation[]> {
    //     return this.http.get<HousingLocation[]>(this.baseUrl);
    // }


   getHousingLocationById(id: number): HousingLocation | undefined {
      return this.housingLocationList.find(housingLocation => housingLocation.id === id);
      }

    // getHousingLocationById(id: number): Observable<HousingLocation> {
    //     return this.http.get<HousingLocation>(`${this.baseUrl}/${id}`);
    // }

  
    // addHousingLocation(housingLocation: HousingLocation): Observable<HousingLocation> {
    //     return this.http.post<HousingLocation>(this.baseUrl, housingLocation);
    //   }


    // updateHousingLocation(housingLocation: HousingLocation): Observable<HousingLocation> {
    //     return this.http.put<HousingLocation>(`${this.baseUrl}/${housingLocation.id}`, housingLocation);
    //   }


    // deleteHousingLocation(id: number): Observable<void> {
    // return this.http.delete<void>(`${this.baseUrl}/${id}`);
    //  }


  //Método de envío en housing.service.ts
  submitApplication(firstName: string, lastName: string, email: string) {
     console.log(`Homes application received: firstName: ${firstName}, lastName: ${lastName}, email: ${email}.`);
  }

}
