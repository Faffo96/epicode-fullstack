const params = new URLSearchParams(location.search);
const currentID = params.get('id');
const searchURL = 'https://striveschool-api.herokuapp.com/api/product/';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';

let fetchResult = [];
const documentBrand = document.getElementById('brand');
const documentName = document.getElementById('name');
const documentPrice = document.getElementById('price');
const documentDescription = document.getElementById('description');
const documentImageUrl = document.getElementById('imageUrl');
const documentLoading = document.getElementById('loading');
const documentMain = document.querySelector('.main');


addEventListener('load', init);

async function init() {
    try {
        if (currentID !== null) {
            fetchResult = await getDatabase("");
        console.log(fetchResult);

        if (fetchResult && fetchResult.length > 0) {
            const foundProduct = fetchResult.find(item => item._id === currentID);
            if (foundProduct) {
                documentImageUrl.style.display = "block";
                documentImageUrl.setAttribute('src', foundProduct.imageUrl);
                
                documentBrand.innerText = foundProduct.brand;
                documentName.innerText = foundProduct.name;
                documentPrice.innerText = foundProduct.price + '€';
                documentDescription.innerText = foundProduct.description;
            } else {
                console.error('Product not found with ID:', currentID);
            }
        } else {
            console.error('No objects returned from the request');
        }
        }
    } catch (error) {
        console.error('Error initializing:', error);
    }
}



async function getDatabase(slug) {
    try {
        const response = await fetch(searchURL + slug, {
            headers: {
                'Authorization': 'Bearer ' + API_KEY
            }
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        fetchResult = await response.json();
        documentLoading.style.display = "none";
        documentMain.style.display = "block";
        return fetchResult;
    } catch (error) {
        throw error;
    }
}