import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarData } from 'src/app/models/car-data.interface';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  carData!: CarData;
  searchTerm: string = '';

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const model = params.get('model');
      if (model) {
        this.getCar(model);
      }
    });
  }

  async getCar(model: string) {
    try {
      const response = await fetch('../../../assets/db.json');
      const cars: CarData[] = await response.json();
      const car = cars.find((car: CarData) => car.model === model);
      if (car) {
        this.carData = car;
      } else {
        console.log('Auto non trovata');
      }
    } catch (error) {
      console.error('Errore nel recupero dei dati delle auto', error);
    }
  }

  async onSubmit() {
    if (this.searchTerm.trim() === '') {
      return;
    }
    try {
      const response = await fetch('../../../assets/db.json');
      const cars: CarData[] = await response.json();
      const car = cars.find((car: CarData) => car.model.toLowerCase() === this.searchTerm.toLowerCase());
      console.log(car)
      if (car) {
        this.router.navigate(['/details', car.model]);
      } else {
        console.log('Auto non trovata');
      }
    } catch (error) {
      console.error('Errore nel recupero dei dati delle auto', error);
    }
  }
}
