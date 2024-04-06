import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray, AbstractControl, FormControl, ValidatorFn } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.interface';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.scss']
})
export class RegisterUserComponent {
  signupForm!: FormGroup;
  user!: User;
  genders = ['Male', 'Female']
  languages = ['English', 'Español', 'Français', 'Deutsch', 'Italiano']

  constructor(private fb: FormBuilder, private authSrv: AuthService, private router: Router) { }

  onSubmit(form: FormGroup) {
    console.log(form.value);
    try {
        this.authSrv.signup(form.value).subscribe();
        this.router.navigate(['/login']);
    } catch (error) {
        console.error(error);
    }
}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      firstName: [null, [Validators.required, Validators.maxLength(15)]],
      lastName: [null, [Validators.required, Validators.maxLength(15)]],
      email: [null, [Validators.required, Validators.maxLength(25)]],
      password: [null, [Validators.required, Validators.minLength(8)]],
      confirmPassword: [null, Validators.required],
      gender: [null, [Validators.required]],
      language: [null, [Validators.required]],
      profileImg: [null, this.fileTypeValidator(['jpeg', 'png', 'gif'])], // Utilizza la tua funzione di validazione
      biography: [null, [Validators.required, Validators.maxLength(700)]],
      username: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
    }, { validator: this.passwordMatchValidator });
    

    this.signupForm.valueChanges.subscribe(status => {
      /* console.log(status); */
    });
  }

  getErrorsC(name: string, error: string) {
    return this.signupForm.get(name)?.errors![error];
  }

  getFormC(name: string) {
    return this.signupForm.get(name);
  }

  submit() {
        this.user = this.signupForm.value;
        console.log('Oggetto user: ', this.user);
        console.log('Form correttamente inviato');
        this.signupForm.reset();
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
