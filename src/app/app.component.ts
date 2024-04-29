import { Component } from '@angular/core';
import { HomeComponent } from './home/components/home/home.component';


@Component({
  selector: 'app-root',
  //standalone: false,
 // imports: [],
 template: `
  <main>
    <header class="brand-name">
     <img class="brand-logo" src="/assets/logo.svg" alt="logo" ariahidden="true">
   </header>
   <section class="content">
      <app-home></app-home>
    </section>
  </main>
`,
  styleUrls: ['./app.component.css'],

 // imports: [HomeComponent],
  

  //en la practica que hacemos, ellos hacen la aplicación sin módulos. Yo como tengo una versión de node antigua y ademas queiro practicar, la hago con módulos
 // selector: 'app-root',
 // templateUrl: './app.component.html',
 // styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'homes-app';
}
