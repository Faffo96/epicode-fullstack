import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms'; // I TD Forms sono delle istanze NgForm

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
})
export class AppComponent {
    title = 'lezione1';

    @ViewChild('form', {static: true}) form!: NgForm; // Attraverso il decoratore ViewChild il component intercetta i cambiamenti nell'oggetto indicato (form) e può gestirli; static true in quanto il component deve leggere i valori come statici

    userForm = {
        username: '',
        email: '',
        question: '',
    }

    genders = [
        {
            label: 'uomo',
            value: 'uomo'
        },
        {
            label: 'donna',
            value: 'donna'
        }
    ]

    answer = '';

    // L'oggetto user è utilizzato soltanto per la stampa
    user: any = {
        username: '',
        email: '',
        question: '',
        gender: '',
        answer: ''
    }

    ngOnInit(): void {
        // Metodo usato per tracciare la validità del form
        this.form.statusChanges?.subscribe(status => {
            console.log('Stato del form: ', status);
        });
    }

    generateUsername() {
        this.form.form.patchValue(
            {
                userInfo: {
                    username: 'Pippo',
                    email: 'pippo@pippo.com'
                }
            }
        )
    }

    submit() {
        console.log(this.form.value);

        // I valori dell'oggetto user sono definiti soltanto in fuznione della stampa
        this.user.username = this.form.value.userInfo.username;
        this.user.email = this.form.value.userInfo.email;
        this.user.question = this.form.value.userInfo.question;
        this.user.gender = this.form.value.userInfo.gender;
        this.user.answer = this.form.value.userInfo.answer;

        console.log('Form correttamente inviato: ', this.form);

        this.form.reset();
    }

}
