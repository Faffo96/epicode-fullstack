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

  constructor(private route: ActivatedRoute, private router: Router) {} // Constructor to inject ActivatedRoute and Router

  ngOnInit() {
    // Subscribe to route parameter changes
    this.route.paramMap.subscribe(params => {
      // Extract 'model' parameter from route
      const model = params.get('model');
      // If 'model' parameter exists, fetch car data for that model
      if (model) {
        this.getCar(model);
      }
    });
  }

  // Method to fetch car data based on model
  async getCar(model: string) {
    try {
      const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
      const cars: CarData[] = await response.json();
      const car = cars.find((car: CarData) => car.model === model);
      if (car) {
        this.carData = car;
      } else {
        console.log('Car not found');
      }
    } catch (error) {
      console.error('Error fetching car data', error);
    }
  }

  // Method to handle form submission
  async onSubmit() {
    // If search term is empty, return early
    if (this.searchTerm.trim() === '') {
      return;
    }
    try {
      const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
      const cars: CarData[] = await response.json(); 
      
      // Filter cars by model based on search term
      const matchingCarsByModel = cars.filter((car: CarData) =>
        car.model.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
      // If matching cars by model exist, navigate to details page of first matching car
      if (matchingCarsByModel.length > 0) {
        const firstMatchingCar = matchingCarsByModel[0];
        this.router.navigate(['/details', firstMatchingCar.model]);
        return;
      }
      
      // Filter cars by brand based on search term
      const matchingCarsByBrand = cars.filter((car: CarData) =>
        car.brand.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
      if (matchingCarsByBrand.length > 0) {
        const firstMatchingCar = matchingCarsByBrand[0];
        this.router.navigate(['/' + firstMatchingCar.brand.toLowerCase()]);
        return;
      }
  
      console.log('No matching cars found by model or brand');
    } catch (error) {
      console.error('Error fetching car data', error);
    }
  }
}
