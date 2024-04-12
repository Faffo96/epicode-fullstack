import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { getAuth, provideAuth } from '@angular/fire/auth';

const firebaseConfig = {
  apiKey: "AIzaSyAXhRwC6dmETqeXVMmcL35GKgkZeeiuUUc",
  authDomain: "netflix-clone-ca824.firebaseapp.com",
  projectId: "netflix-clone-ca824",
  storageBucket: "netflix-clone-ca824.appspot.com",
  messagingSenderId: "919652111338",
  appId: "1:919652111338:web:3874ea9651f648ef3b9893",
  measurementId: "G-HF1BKB3C1V"
};

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    importProvidersFrom([
      provideFirebaseApp(() => initializeApp(firebaseConfig)),
      provideAuth(() => getAuth())
    ])
  ]
};
