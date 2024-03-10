# CRUDazon

<!-- BANNER DA INSERIRE QUI --> 
![banner](https://github.com/Faffo96/gruppo-2/assets/157897660/d29a9fdb-2ce2-4ff5-857e-54d4cfbe7531)

<!-- piccola descrizione del progetto --> 
<h3 align="center" >The goal of this exercise is to use CRUD operations correctly</h3>

<br>
<!-- SPAZIO DA METTERE + BADGES (dynamic e static) --> 
<!-- https://shields.io/badges // link per creare le badges --> 
<br>

I focused on two main goals:
1. _Basic layout creation predominantly with HTML and Bootstrap, but also CSS with pre-organized classes_
2. _The management of features and CRUDs on pages via Javascript using the id that is inserted in the url as a slug_

<br>

## Table of Contents 

- [How it is structured](#How-it-is-structured)
- [How it work](#how-it-work)

<br> 

## How it is structured

My project is divided into:

> ![Static Badge](https://img.shields.io/badge/HTML-black?style=for-the-badge&logo=HTML5)
- index
- addProduct
- product

 ![Static Badge](https://img.shields.io/badge/Javascript-black?style=for-the-badge&logo=javascript)
- [Index](#index)
- [addProduct](#addProduct)
- [Product](#product)

  <br>
  
 > ![Static Badge](https://img.shields.io/badge/CSS-black?style=for-the-badge&logo=CSS3)
- Stylesheet unico

<br>
  
## How it workS7L5

- _**index.js**_:
  
 * This code snippet demonstrates the initialization, creation, and manipulation of card elements on a webpage.
 
 * The code includes functions for retrieving data from a database, creating button elements, creating card elements, creating text and title elements, and handling search functionality.

 * The main function, init(), is called on page load and retrieves data from the database using the getDatabase() function. The retrieved data is then used to create card elements using the createCards() function.

 * The code also includes functions for creating different types of buttons (info, edit, delete), as well as functions for creating text and title elements, and a function for creating the card body element.

 * Additionally, the code includes functions for handling the deletion of records from the database, searching for records based on a search term, and displaying the search results on the webpage.

 * The code snippet is designed to be used in a webpage that displays a collection of products, with the ability to view more information about each product, edit product details, and delete products.
 */

<br>

- _**addProduct.js**_:

 * Initializes the page based on the URL parameters.
 * If the 'id' parameter is present, it retrieves the corresponding product from the database and shows the edit page.
 */
 * If the 'name' parameter is present, it displays an alert message indicating that the product was updated successfully and redirects to the index page.
 */
 * If neither 'id' nor 'name' parameters are present, it shows the add page.
 */
 * In addition, the code includes functions for managing the addition, deletion or modification of records from the database, and a function to clear all the form's inputs. 

<br>

- _**Product.js**_:

 * Initialize the page by taking the id from the url slug passed by the Edit(index.html) button.
 */
 * Retrieves data from the database based on the provided id.
 
 * Fills the page with data from the given object.
 */

<br> 

## Partecipants

| PARTECIPANTS | ROLE | ACCOUNT GIT | 
| ----------- |  ----------- | ----------- | 
| Fabio Scaramozzino | Epicode FullStack Student | ![Static Badge](https://img.shields.io/badge/Faffo96-%233eb752?style=for-the-badge&logo=github) | 

<br>

- [Back to top! :D](#CRUDazon)







