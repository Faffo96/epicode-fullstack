import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, AbstractControl, FormControl, ValidatorFn  } from '@angular/forms';
import { User } from 'src/app/models/user.interface';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.scss']
})
export class RegisterUserComponent {
  form!: FormGroup;
  user!: User;
  genders = ['Male', 'Female']

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      firstName: [null, [Validators.required, Validators.maxLength(15)]],
      lastName: [null, [Validators.required, Validators.maxLength(15)]],
      email: [null, [Validators.required, Validators.maxLength(25)]],
      password: [null, [Validators.required, Validators.minLength(8)]],
      confirmPassword: [null, Validators.required],
      gender: [null, [Validators.required, Validators.maxLength(15)]],
      profileImg: [null, this.fileTypeValidator(['jpeg', 'png', 'gif'])], // Utilizza la tua funzione di validazione
      biography: [null, [Validators.required, Validators.maxLength(700)]],
      username: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
    }, { validator: this.passwordMatchValidator });
    

    this.form.valueChanges.subscribe(status => {
      console.log(status);
    });
  }

  getErrorsC(name: string, error: string) {
    return this.form.get(name)?.errors![error];
  }

  getFormC(name: string) {
    return this.form.get(name);
  }

  submit() {
        this.user = this.form.value;
        console.log('Oggetto user: ', this.user);
        console.log('Form correttamente inviato');
        this.form.reset();
    }

// Funzione di validazione personalizzata per controllare il tipo di file
fileTypeValidator(allowedTypes: string[]): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const file = control.value as File;
    if (file instanceof File && file.type) { // Verifica se il file è definito e se ha una proprietà 'type'
      const fileType = file.type.split('/')[1].toLowerCase();
      if (allowedTypes.indexOf(fileType) === -1) {
        return { invalidFileType: true };
      }
    }
    return null; // Restituisci null se non ci sono errori
  };
}




// Funzione di validazione personalizzata per controllare se la password corrisponde al campo di conferma della password
passwordMatchValidator(control: AbstractControl): { [key: string]: any } | null {
  const password = control.get('password');
  const confirmPassword = control.get('confirmPassword');

  if (password && confirmPassword && password.value !== confirmPassword.value) {
    confirmPassword?.setErrors({ passwordMismatch: true });
  } else {
    confirmPassword?.setErrors(null);
  }

  return null;
}
}
