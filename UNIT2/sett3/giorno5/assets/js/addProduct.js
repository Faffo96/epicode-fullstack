const searchURL = 'https://striveschool-api.herokuapp.com/api/product/';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';
let fetchResult = [];
const params = new URLSearchParams(location.search);
let currentID = '';
const documentBtnSave = document.getElementById('btnSave');
const documentBtnDelete = document.getElementById('btnDelete');
const documentH1 = document.getElementById('h1');
const documentLoading = document.getElementById('loading');
const documentMain = document.querySelector('.main');

addEventListener('load', init);

async function init() {
    try {
        if (params.has('id')) {
            currentID = params.get('id');
            showEditPage(await getDatabase(currentID));
        } else if (params.has('name')) {
            alert('Product updated successfully');
            window.location.href = 'index.html';
        } else {
            showAddPage();
        }
    } catch (error) {
        console.error('Error initializing:', error);
    }
}

async function showEditPage(product) {
    try {
        if (product) {
            await fillForm(currentID);
            documentH1.innerText = "Edit Product";
            documentBtnDelete.style.display = "block";
            documentBtnSave.addEventListener('click', saveChanges);
            documentBtnDelete.addEventListener('click', deleteRecord);
        } else {
            console.error('Product not found with ID:', currentID);
        }
    } catch (error) {
        console.error('Error showing edit:', error);
    }
}


function showAddPage() {
    documentLoading.style.display = "none";
    documentMain.style.display = "block";
    documentH1.innerText = "Add Product"
    documentBtnSave.addEventListener('click', pushDatabase);
}

function createObject() {
    const formData = new FormData(document.querySelector('form')); // Ottieni i dati del form
    console.log(formData);
    const data = {};

    // Converti i dati del modulo in un oggetto
    formData.forEach((value, key) => {
        console.log(key);
        data[key] = value;
    });
    return updatedProduct;
}


function pushDatabase() {
   const data = createObject();
    // Esegui una richiesta POST usando fetch
    fetch(searchURL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${API_KEY}` // Aggiungi l'API key nell'header Authorization
        },
        body: JSON.stringify(data), // Converti l'oggetto dati in JSON e invialo nel corpo della richiesta
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Errore nella richiesta di salvataggio');
            }
            return response.json(); // Se la richiesta è andata a buon fine, ritorna la risposta JSON
        })
        .then(responseData => {
            console.log('Dati salvati con successo:', responseData);
        })
        .catch(error => {
            console.error('Si è verificato un errore durante il salvataggio dei dati:', error);
        });
    console.log(data)
}

async function getDatabase(id) {
    try {
        const response = await fetch(searchURL + id, {
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


async function fillForm(id) {
    try {
        const product = await getDatabase(id);
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
    }
}


const documentBtnReset = document.getElementById('btnReset');
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
    }
}

async function deleteRecord() {
    try {
        const response = await fetch(searchURL + currentID, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + API_KEY
            }
        });
        if (!response.ok) {
            throw new Error('Failed to delete record');
        }
        console.log('Record deleted successfully');
        window.location.href = 'index.html';
    } catch (error) {
        console.error('Error deleting record:', error);
    }
}

async function saveChanges() {
    try {
        const updatedProduct = createObject();

        const response = await fetch(searchURL + currentID, {
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
    } catch (error) {
        console.error('Error saving changes:', error);
    }
}
