import { Component } from '@angular/core';
import { CarData } from 'src/app/models/car-data.interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  lamborghiniCar!: CarData;
  ferrariCar!: CarData;
  porscheCar!: CarData;

  lamborghini: CarData[] = [];
  ferrari: CarData[] = [];
  porsche: CarData[] = [];

  usedIndex: number[] = [];

  constructor() {
    for (let i = 0; i < 2; i++) {
       Promise.all([this.getLamborghini(), this.getFerrari(), this.getPorsche()]).then(cars => {
      cars.forEach(car => {
        if (car.brand === 'Lamborghini') {
          this.lamborghini.push(car);
          this.lamborghiniCar = car;
        } else if (car.brand === 'Ferrari') {
          this.ferrari.push(car);
          this.ferrariCar = car;
        } else if (car.brand === 'Porsche') {
          this.porsche.push(car);
          this.porscheCar = car;
        }
      });
    });
    }
  }
  

  async getLamborghini() {
    const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
    const cars = await response.json();
    const lamborghiniCars = cars.filter((car: CarData, index: number) => {
      return car.brand === "Lamborghini" && !this.usedIndex.includes(index);
    });
  
    let randomIndex = this.getRandomIndex(lamborghiniCars.length);
    while (this.usedIndex.includes(randomIndex)) {
      randomIndex = this.getRandomIndex(lamborghiniCars.length);
    }
  
    this.usedIndex.push(randomIndex);
    return lamborghiniCars[randomIndex];
  }
  
  

  async getFerrari() {
    const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
    const cars = await response.json();
    const ferrariCars = cars.filter((car: CarData, index: number) => {
      return car.brand === "Ferrari" && !this.usedIndex.includes(index);
    });
  
    let randomIndex = this.getRandomIndex(ferrariCars.length);
    while (this.usedIndex.includes(randomIndex)) {
      randomIndex = this.getRandomIndex(ferrariCars.length);
    }
  
    this.usedIndex.push(randomIndex);
    return ferrariCars[randomIndex];
  }

  async getPorsche() {
    const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
    const cars = await response.json();
    const porscheCars = cars.filter((car: CarData, index: number) => {
      return car.brand === "Porsche" && !this.usedIndex.includes(index);
    });
  
    let randomIndex = this.getRandomIndex(porscheCars.length);
    while (this.usedIndex.includes(randomIndex)) {
      randomIndex = this.getRandomIndex(porscheCars.length);
    }
  
    this.usedIndex.push(randomIndex);
    return porscheCars[randomIndex];
  }

  getRandomIndex(max: number): number {
    return Math.floor(Math.random() * max);
  }
}
