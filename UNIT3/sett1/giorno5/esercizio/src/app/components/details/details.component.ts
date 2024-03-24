import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarData } from 'src/app/models/car-data.interface';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {
  carData!: CarData;

  constructor(private route: ActivatedRoute) {}

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
      const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
      const cars: CarData[] = await response.json();
      const car = cars.find((car: CarData) => car.model === model);
      if (car) {
        this.carData = car;
        console.log(this.carData);
      } else {
        console.log('Auto non trovata');
      }
    } catch (error) {
      console.error('Errore nel recupero dei dati delle auto', error);
    }
  }
}

