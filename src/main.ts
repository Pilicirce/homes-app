import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
//aquí se importan los módulos (?)
import { AppModule } from './app/app.module';
import { bootstrapApplication,provideProtractorTestingSupport } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';

 platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err)); 
 

/* bootstrapApplication(AppComponent,
    {
providers: [
provideProtractorTestingSupport(),
provideRouter(routeConfig)
]
}
  .catch(err => console.error(err)); */