import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './form.component'; // Importa il proprio component

const routes: Routes = [
    {
        path: '', // In una rotta lazy il path del routing module è SEMPRE path vuoto, in quanto il component chiama se stesso
        component: FormComponent // Definisce la rotta per il proprio component e lo attiva
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class FormRoutingModule {}
