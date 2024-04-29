import { NgModule } from '@angular/core';
import { HomeComponent } from './components/home/home.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [
      HomeComponent,
     
    ],
    
    exports: [
      HomeComponent
    ],
  
    imports: [
      CommonModule, 
      FormsModule,
    ]
  })
  export class TodoModule { }

export { HomeComponent };
  