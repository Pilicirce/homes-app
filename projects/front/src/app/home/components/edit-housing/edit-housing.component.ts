import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HousingService } from '../../services/housing.service';
import { HousingLocation } from '../../interfaces/housinglocation';

@Component({
  selector: 'app-edit-housing',
  templateUrl: './edit-housing.component.html',
  styleUrls: ['./edit-housing.component.css']
})
export class EditHousingComponent implements OnInit {

  housingForm!: FormGroup;
  housingId!: number;

  constructor(
    private route: ActivatedRoute,
    private housingService: HousingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // 1️) Leer el id desde la URL
    this.housingId = Number(this.route.snapshot.paramMap.get('id'));

    // 2️) Inicializar el formulario (vacío por ahora)
    this.housingForm = new FormGroup({
      name: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      state: new FormControl('', Validators.required),
      photo: new FormControl(''),
      availableUnits: new FormControl(0, Validators.required),
      bedrooms: new FormControl(0, Validators.required),
      wifi: new FormControl(false),
      laundry: new FormControl(false),
      parking: new FormControl(false)
    });

    // 3️) Cargar los datos existentes y rellenar el formulario
    this.housingService.getHousingLocationById(this.housingId).subscribe({
      next: (data: HousingLocation) => {
        this.housingForm.patchValue(data); //patchValue actualiza solo los campos que coinciden, (más seguro que setValue)
      },
      error: err => console.error('Error loading housing', err)
    });
  }

  onSubmit(): void {
    if (this.housingForm.invalid) {
      this.housingForm.markAllAsTouched();
      return;
    }

    this.housingService
      .updateHousingLocation(this.housingId, this.housingForm.value) //PUT (update del CRUD)
      .subscribe({
        next: () => {
          console.log('Housing updated');
          this.router.navigate(['/details', this.housingId]);
        },
        error: err => console.error('Error updating housing', err)
      });
  }
}
