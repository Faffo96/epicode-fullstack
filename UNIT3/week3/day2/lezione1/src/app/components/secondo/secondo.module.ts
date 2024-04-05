import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SecondoRoutingModule } from './secondo-routing.module';
import { SecondoComponent } from './secondo.component';

@NgModule({
    declarations: [SecondoComponent],
    imports: [CommonModule, SecondoRoutingModule],
})
export class SecondoModule {}
