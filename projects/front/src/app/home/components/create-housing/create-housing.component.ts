import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HousingService } from '../../services/housing.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-create-housing',
  templateUrl: './create-housing.component.html',
  styleUrls: ['./create-housing.component.css']
})

export class CreateHousingComponent implements OnInit {

  housingForm!: FormGroup;
  backendErrors: any = {};

  constructor(
    private housingService: HousingService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.housingForm = new FormGroup({
      name: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      country: new FormControl('', Validators.required),
      photo: new FormControl(''),
      availableUnits: new FormControl(0, Validators.required),
      bedrooms: new FormControl(0, Validators.required),
      wifi: new FormControl(false),
      laundry: new FormControl(false),
      parking: new FormControl(false)
    });
  }

  onSubmit(): void {
    this.backendErrors = {};   //para limpiar errores previos
  if (this.housingForm.invalid) {
    this.housingForm.markAllAsTouched();
    return;
  }

  this.housingService.createHousingLocation(this.housingForm.value)
    .subscribe({
      next: () => {
        this.snackBar.open('Housing created successfully', 'Close', {
          duration: 3000,
          panelClass: ['success-snackbar']
        });
        this.router.navigate(['/']);
      },
      error: err => {
        console.error('Error creating housing', err);
        this.backendErrors = err.error?.errors;  //para mostrar los errores específicos del backend en el formulario
      }
    });
}
}
