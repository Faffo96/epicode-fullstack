const searchURL = 'https://striveschool-api.herokuapp.com/api/product/';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';
let ActualItem;
let fetchResult = [];
const params = new URLSearchParams(location.search);
const currentID = params.get('id');
const documentBtnSave = document.getElementById('btnSave');
const documentBtnDelete = document.getElementById('btnDelete');
const documentH1 = document.getElementById('h1');

addEventListener('load', init);

async function init() {
    if (currentID !== null) {
        documentH1.innerText = "Edit Product"
        documentBtnDelete.style.display = "block";
        fillInputs(currentID);
        documentBtnSave.addEventListener('click', saveChanges);
        documentBtnDelete.addEventListener('click', deleteRecord);
    } else {
        documentH1.innerText = "Add Product"
        documentBtnSave.addEventListener('click', pushDatabase);
    }
    try {
        fetchResult = await getDatabase("");
        console.log(fetchResult);
        if (fetchResult && fetchResult.length > 0) {
            const foundProduct = fetchResult.find(item => item._id === currentID);
            if (foundProduct) {
                fillInputs(currentID);
            } else {
                console.error('Product not found with ID:', currentID);
            }
        } else {
            console.error('No objects returned from the request');
        }
    } catch (error) {
        console.error('Error initializing:', error);
    }
}


function pushDatabase() {
    const formData = new FormData(document.querySelector('form')); // Ottieni i dati del modulo
    console.log(formData);
    const data = {}; // Inizializza un oggetto per i dati da inviare

    // Converti i dati del modulo in un oggetto
    formData.forEach((value, key) => {
        console.log(key);
        data[key] = value;
    });

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
        // Gestisci eventuali errori qui
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

        // Reindirizza l'utente alla pagina index.html
        window.location.href = 'index.html';
    } catch (error) {
        console.error('Error deleting record:', error);
        // Gestisci eventuali errori qui
    }
}



async function saveChanges() {
    try {
        const updatedProduct = {
            name: document.getElementById('productName').value,
            brand: document.getElementById('brand').value,
            price: parseFloat(document.getElementById('price').value),
            imageUrl: document.getElementById('imgUrl').value,
            description: document.getElementById('productDescription').value
        };

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
        /* alert('Product updated successfully'); */
        


    } catch (error) {
        console.error('Error saving changes:', error);
    }
}


