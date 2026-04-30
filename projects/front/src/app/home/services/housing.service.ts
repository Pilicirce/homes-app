import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HousingLocation } from '../interfaces/housinglocation';

@Injectable({
  providedIn: 'root'
})
export class HousingService { 
  //está configurado para proporcionar una lista de ubicaciones de viviendas
  baseUrl = 'https://angular.io/assets/images/tutorials/faa';

  private apiUrl = 'http://localhost:8081/api/housing';

  

constructor(private http: HttpClient) {}

  getAllHousingLocations(): Observable<HousingLocation[]> {
    return this.http.get<HousingLocation[]>(this.apiUrl);
  }

  getHousingLocationById(id: number): Observable<HousingLocation> {
    return this.http.get<HousingLocation>(`${this.apiUrl}/${id}`);
  }

  createHousingLocation(housing: HousingLocation): Observable<HousingLocation> {
    return this.http.post<HousingLocation>(this.apiUrl, housing);
  }

  updateHousingLocation(id: number, housing: HousingLocation): Observable<HousingLocation> {
    return this.http.put<HousingLocation>(`${this.apiUrl}/${id}`, housing);
  }

  deleteHousingLocation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }


  //Método de envío en housing.service.ts
  submitApplication(firstName: string, lastName: string, email: string) {
     console.log(`Homes application received: firstName: ${firstName}, lastName: ${lastName}, email: ${email}.`);
  }

}
