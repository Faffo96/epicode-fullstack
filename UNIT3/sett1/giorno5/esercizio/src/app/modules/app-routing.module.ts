import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../components/home/home.component';
import { FerrariComponent } from '../components/ferrari/ferrari.component';
import { LamborghiniComponent } from '../components/lamborghini/lamborghini.component';
import { PorscheComponent } from '../components/porsche/porsche.component';
import { DetailsComponent } from '../components/details/details.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'ferrari', component: FerrariComponent },
  { path: 'lamborghini', component: LamborghiniComponent },
  { path: 'porsche', component: PorscheComponent },
  { path: 'dettaglio/:model', component: DetailsComponent },
  { path: '**', redirectTo: '' } // Se l'URL non corrisponde a nessuna route, reindirizza alla home
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
