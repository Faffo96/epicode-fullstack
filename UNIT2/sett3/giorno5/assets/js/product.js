/* INIT */

const params = new URLSearchParams(location.search);
const currentID = params.get('id');
const searchURL = 'https://65fff0f4df565f1a61458616.mockapi.io/crudAzon/products/';
/* const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk'; */

let fetchResult = [];
const documentBrand = document.getElementById('brand');
const documentName = document.getElementById('name');
const documentPrice = document.getElementById('price');
const documentDescription = document.getElementById('description');
const documentImageUrl = document.getElementById('imageUrl');
const documentLoading = document.getElementById('loading');
const documentMain = document.querySelector('.main');

async function getDatabase(id) { // Retrieves data from the database based on the provided id.
    try {
        const response = await fetch(searchURL + id)/* , {
            headers: {
                'Authorization': 'Bearer ' + API_KEY
            }
        }); */
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        fetchResult = await response.json();
        documentLoading.style.display = "none";
        documentLoading.style.padding = "0px";
        documentMain.style.display = "block";
        return fetchResult;
    } catch (error) {
        throw error;
    }
}

function fillPage(obj) { // Fills the page with data from the given object.
    documentImageUrl.style.display = "block";
    documentImageUrl.setAttribute('src', obj.imageUrl);
    documentBrand.innerText = obj.brand;
    documentName.innerText = obj.name;
    documentPrice.innerText = obj.price + '€';
    documentDescription.innerText = obj.description;
}

addEventListener('load', init);

async function init() { // Initializes the page by fetching data from the database and filling the page with the retrieved data.
    try {
        if (currentID !== null) {
            const foundProduct = await getDatabase(currentID);
            if (foundProduct) {
                fillPage(foundProduct);
            } else {
                console.error('Product not found with ID:', currentID);
            }
        } else {
            console.error(currentID, 'not found')
        }
    } catch (error) {
        console.error('Error initializing:', error);
    }
}



