import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HousingService } from '../../services/housing.service';
import { HousingLocation } from '../../interfaces/housinglocation';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ApplyFormComponent } from '../apply-form/apply-form.component';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})

export class DetailsComponent implements OnInit {
  // applyForm = new FormGroup({
  //   firstName: new FormControl(''),
  //   lastName: new FormControl(''),
  //   email: new FormControl('')
  // });

  housingLocation: HousingLocation | undefined;

  constructor(
    private housingService: HousingService, 
    private route: ActivatedRoute,
    public dialog: MatDialog
  ) { }
  
  ngOnInit() {
    const housingLocationId = Number(this.route.snapshot.paramMap.get('id'));
    this.housingLocation = this.housingService.getHousingLocationById(housingLocationId);
    // this.housingService.getHousingLocationById(housingLocationId).subscribe((data: HousingLocation) => {
    //   this.housingLocation = data;
    //   console.log('Housing location details:', this.housingLocation);
    // });
  }

  openApplyDialog(): void {
    const dialogRef = this.dialog.open(ApplyFormComponent, {
      width: '250px',
      data: { location: this.housingLocation }
    });

  // submitApplication() {
  //   this.housingService.submitApplication(
  //     this.applyForm.value.firstName ?? '',
  //     this.applyForm.value.lastName ?? '',
  //     this.applyForm.value.email ?? ''
  //   );
  // }

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    if (result) {
      this.housingService.submitApplication(result.firstName, result.lastName, result.email);
    }
  });
  }
}