import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  //standalone: false,
 // imports: [],
 template: `
  <main>
      <a [routerLink]="['/']">
        <header class="brand-name">
          <img class="brand-logo" src="/assets/logo.svg" alt="logo" aria-hidden="true">
        </header>
      </a>
      <section class="content">
        <router-outlet></router-outlet> <!-- Aquí se carga dinámicamente el contenido del componente de la ruta actual -->
      </section>
    </main>
  `,
  styleUrls: ['./app.component.css'],

// imports: [
//  HomeComponent,
//  RouterModule,
//  ],
  

  //en la practica que hacemos, ellos hacen la aplicación sin módulos. Yo como tengo una versión de node antigua y ademas queiro practicar, la hago con módulos
 // selector: 'app-root',
 // templateUrl: './app.component.html',
 // styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'homes-app'; //esta linea no se si debo dejarla o eliminarla despues de haber metido el enrutamiento
}
