import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Il modulo form importa il modulo operativo generale che il componente userà

import { FormRoutingModule } from './form-routing.module'; // Importa il proprio sistema di routing
import { FormComponent } from './form.component'; // Importa il proprio component (il fatto che si chiami come il component e che sia all'interno della cartella del component NON SIGNIFICA che sia automaticamente associato al component, l'associazione deve essere esplicita)

@NgModule({
    declarations: [FormComponent],
    imports: [CommonModule, FormRoutingModule, FormsModule],
})
export class FormModule {}
