import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HousingService } from '../../services/housing.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-housing',
  templateUrl: './create-housing.component.html',
  styleUrls: ['./create-housing.component.css']
})

export class CreateHousingComponent implements OnInit {

  housingForm!: FormGroup;

  constructor(
    private housingService: HousingService,
    private router: Router
  ) {}

  ngOnInit(): void {
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
  }

  onSubmit(): void {
    if (this.housingForm.valid) {
      this.housingService.createHousingLocation(this.housingForm.value)
        .subscribe({
          next: () => {
            console.log('Housing created');
            this.router.navigate(['/']);
          },
          error: err => console.error('Error creating housing', err)
        });
    }
  }
}
