import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
    selector: 'app-form',
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.scss'],
})
export class FormComponent {
    form!: NgForm;

    name!: string;
    email!: string;

    constructor() {}

    onSubmit(form: NgForm) {
        console.log('Form inviato: ' + JSON.stringify(form.value));
    }
}
