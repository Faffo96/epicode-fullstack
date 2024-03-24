# Car Motorshow

<!-- BANNER DA INSERIRE QUI --> 
<div align="center">
  <img src="./src/assets/img/README_banner.png" alt="banner">
</div>

<!-- piccola descrizione del progetto --> 
<h3 align="center">The goal of this project is to use CRUDs in angular</h3>
<h4 align="center"> Live Preview: car-showroom-a3b01.web.app</h4>

<br>
<!-- SPAZIO DA METTERE + BADGES (dynamic e static) --> 
<!-- https://shields.io/badges // link per creare le badges --> 
<br>

I focused on those main objectives:
1. _Identify the main components of the pages_
2. _Define navigation routes between pages_
3. _HTML creation and SCSS styling_
4. _Get the datas i need via fetchs_
5. _Pass the datas to HTML to dynamically display it_

<br>

## Table of Contents 

- [How it is structured](#How-it-is-structured)
- [How it work](#how-it-work)

<br> 

## How it is structured

My project is divided into:

> ![Static Badge](https://img.shields.io/badge/HTML-black?style=for-the-badge&logo=HTML5)
- Home page
- Brand page
- Details page

<br>

 ![Static Badge](https://img.shields.io/badge/Javascript-black?style=for-the-badge&logo=javascript)
 
- [home.components.ts](#home)
- [lamborghini.components.ts](#lamborghini)
- [ferrari.components.ts](#ferrari)
- [porsche.components.ts](#porsche)
- [details.components.ts](#details)
  <br>
  
 > ![Static Badge](https://img.shields.io/badge/CSS-black?style=for-the-badge&logo=CSS3)
- One stylesheet.

<br>
  
## How it work

 ### home **:**
  
The HomeComponent fetches and displays vehicle data from different brands (Lamborghini, Ferrari, and Porsche) via an API. It assigns the data to variables for UI rendering.

Initialization
In the constructor, async calls retrieve vehicle data for each brand using getLamborghini, getFerrari, and getPorsche.

Data Retrieval
Functions like getLamborghini, getFerrari, and getPorsche fetch brand-specific vehicle data asynchronously, ensuring uniqueness.

Random Index Selection
The getRandomIndex function selects a random index within the available range.

This component showcases a random selection of vehicles on the app's initial page.

<br>

### headerComponent.ts **:**

This HeaderComponent handles the navigation and search functionality. It fetches car data based on the model from the API and navigates to the details page accordingly. Additionally, it allows users to search for cars by model or brand and navigate to the appropriate page. It also includes handling for partial search matches.

<br>

### homeComponent.ts **:**

This HomeComponent fetches vehicle data for Lamborghini, Ferrari, and Porsche brands and distributes the results into respective arrays. It ensures that each brand has at most two cars displayed.

<br>

### lamborghiniComponent.ts **:**

The lamborghiniComponent fetches and displays Lamborghini car data from a mock API. The loadLamborghiniData method asynchronously retrieves data and filters it based on the brand.

<br>

### ferrariComponent.ts **:**

The ferrariComponent fetches and displays Lamborghini car data from a mock API. The loadLamborghiniData method asynchronously retrieves data and filters it based on the brand.

<br> 

### porscheComponent.ts **:**

The porscheComponent fetches and displays Lamborghini car data from a mock API. The loadLamborghiniData method asynchronously retrieves data and filters it based on the brand.

<br> 

### detailsComponent.ts **:**

This DetailsComponent fetches and displays details of a specific car based on the model passed in the URL. It uses Angular's ActivatedRoute to retrieve the model parameter from the URL and then fetches the corresponding car data from a mock API.

<br> 

## Partecipants

| PARTECIPANTS       | ROLE                      | ACCOUNT GIT                                                                                     |
| ------------------ | ------------------------- | ----------------------------------------------------------------------------------------------- |
| Fabio Scaramozzino | Epicode FullStack Student | ![Static Badge](https://img.shields.io/badge/Faffo96-%233eb752?style=for-the-badge&logo=github) |

<br>

- [Back to top! :D](#CRUDazon)







