const searchURL = 'https://striveschool-api.herokuapp.com/api/product/';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';
let fetchResult = [];
const params = new URLSearchParams(location.search);
const id = params.get('id');
console.log(id)
let currentID;// Salva l'ID dell'oggetto corrente



const documentBtnReset = document.getElementById('btnReset');
const documentBtnDelete = document.getElementById('btnDelete');
const documentBtnSave = document.getElementById('btnSave');

addEventListener('load', init);

async function init() {
    try {
        fetchResult = await getDatabase("");
        console.log(fetchResult);
        if (fetchResult && fetchResult.length > 0) {
            const foundProduct = fetchResult.find(item => item._id === id);
            if (foundProduct) {
                currentID = foundProduct._id;
                fillInputs(currentID);
            } else {
                console.error('Product not found with ID:', id);
            }
        } else {
            console.error('No objects returned from the request');
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
        currentID = fetchResult.length > 0 ? fetchResult[0]._id : null; // Assegna l'ID del primo elemento dell'array (o null se l'array è vuoto)
        console.log(currentID);
        return fetchResult;
    } catch (error) {
        throw error;
    }
}


async function fillInputs(id) {
    try {
        await getDatabase(""); // Assicurati di ottenere i dati più aggiornati prima di cercare l'ID corrispondente
        const product = fetchResult.find(item => item._id === id); // Cerca l'oggetto con l'ID corrispondente
        if (!product) {
            throw new Error('Product not found');
        }
        
        document.getElementById('productName').value = product.name; // Riempie il campo del nome del prodotto
        document.getElementById('brand').value = product.brand; // Riempie il campo del brand
        document.getElementById('price').value = product.price; // Riempie il campo del prezzo
        document.getElementById('imgUrl').value = product.imageUrl; // Riempie il campo dell'URL dell'immagine
        document.getElementById('productDescription').value = product.description; // Riempie il campo della descrizione del prodotto
    } catch (error) {
        console.error('Error filling inputs:', error);
        // Gestisci eventuali errori qui
    }
}


documentBtnReset.addEventListener('click', clearInputFields);

async function clearInputFields() {
    try {
        document.getElementById('productName').value = ''; // Svuota il campo del nome del prodotto
        document.getElementById('brand').value = ''; // Svuota il campo del brand
        document.getElementById('price').value = ''; // Svuota il campo del prezzo
        document.getElementById('imgUrl').value = ''; // Svuota il campo dell'URL dell'immagine
        document.getElementById('productDescription').value = ''; // Svuota il campo della descrizione del prodotto
    } catch (error) {
        console.error('Error clearing input fields:', error);
        // Gestisci eventuali errori qui
    }
}

documentBtnDelete.addEventListener('click', deleteRecord);

async function deleteRecord() {
    try {
        const id = params.get('id'); // Ottiene l'ID dal parametro URL
        const response = await fetch(searchURL + id, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + API_KEY
            }
        });
        if (!response.ok) {
            throw new Error('Failed to delete record');
        }
        console.log('Record deleted successfully');
        
        // Reindirizza l'utente alla pagina index.html
        window.location.href = 'index.html';
    } catch (error) {
        console.error('Error deleting record:', error);
        // Gestisci eventuali errori qui
    }
}

documentBtnSave.addEventListener('click', saveChanges);

async function saveChanges() {
    try {
        const updatedProduct = {
            name: document.getElementById('productName').value,
            brand: document.getElementById('brand').value,
            price: parseFloat(document.getElementById('price').value),
            imageUrl: document.getElementById('imgUrl').value,
            description: document.getElementById('productDescription').value
        };
        
        const response = await fetch(searchURL + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + API_KEY
            },
            body: JSON.stringify(updatedProduct)
        });

        if (!response.ok) {
            throw new Error('Failed to update product');
        }
        /* fillInputs(currentID); */
        alert('Product updated successfully');
        window.location.href = 'index.html';
        
    } catch (error) {
        console.error('Error saving changes:', error);
    }
}





