import { Component } from '@angular/core';
import {
    FormBuilder,
    FormGroup,
    Validators,
    FormArray,
    AbstractControl,
    FormControl,
} from '@angular/forms';
import { User } from './interface/user.interface';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
})
export class AppComponent {
    title = 'lezione2';

    form!: FormGroup;
    genders = ['Uomo', 'Donna'];
    forbiddenUN = ['pippo', 'paperino'];
    user!: User;

    constructor(private fb: FormBuilder) {}

    validUsername = (formC: AbstractControl) => {
        if (this.forbiddenUN.includes(formC.value)) {
            return { 'proibito': true };
        } else {
            return null;
        }
    };

    ngOnInit(): void {
        this.form = this.fb.group({
            userInfo: this.fb.group({
                username: this.fb.control(null, [
                    Validators.required,
                    this.validUsername,
                ]),
                email: this.fb.control(null, [
                    Validators.required,
                    Validators.email,
                ]),
            }),
            gender: this.fb.control(''),
            interests: this.fb.array([]),
        });

        this.form.valueChanges.subscribe((status) => {
            console.log('Stato del form: ', status);
        });
    }

    getErrorsC(name: string, error: string) {
        return this.form.get(name)?.errors![error];
    }

    getFormC(name: string) {
        return this.form.get(name);
    }

    getInterestsFields() {
        return (this.form.get('interests') as FormArray).controls;
    }

    addInterests() {
        const control = this.fb.control(null);
        (this.form.get('interests') as FormArray).push(control);
        console.log(this.getInterestsFields());
    }

    submit() {
        this.user = this.form.value;
        console.log('Oggetto user: ', this.user);
        console.log('Form correttamente inviato');
        this.form.reset();
    }
}
