import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http'; // Importa HttpClientModule perché il suo component utilizzerà i metodi HTTP

import { UserListRoutingModule } from './user-list-routing.module';
import { UserListComponent } from './user-list.component';

@NgModule({
    declarations: [UserListComponent],
    imports: [CommonModule, UserListRoutingModule, HttpClientModule],
})
export class UserListModule {}
