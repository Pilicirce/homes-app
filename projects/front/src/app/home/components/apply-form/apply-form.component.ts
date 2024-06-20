import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-apply-form',
  templateUrl: './apply-form.component.html',
  styleUrls: ['./apply-form.component.css']
})
export class ApplyFormComponent {
  applyForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl('')
  });

  constructor(
    public dialogRef: MatDialogRef<ApplyFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  onSubmit() {
    console.log('Form submitted:', this.applyForm.value);
    this.dialogRef.close(this.applyForm.value);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
