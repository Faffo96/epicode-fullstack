/* INIT */

const searchURL = 'https://65fff0f4df565f1a61458616.mockapi.io/crudAzon/products/';
/* const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';
 */let fetchResult = [];
const params = new URLSearchParams(location.search);
let currentID = '';
const documentBtnSave = document.getElementById('btnSave');
const documentBtnDelete = document.getElementById('btnDelete');
const documentH1 = document.getElementById('h1');
const documentLoading = document.getElementById('loading');
const documentMain = document.querySelector('.main');

async function getDatabase(id) { // Retrieves data from the database using the provided id.
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
        documentMain.style.display = "block";
        return fetchResult;
    } catch (error) {
        throw error;
    }
}

/* INIT_ADD_PRODUCT - OPTION 1 */

function showAddProductPage() { // Displays the edit page.
    documentLoading.style.display = "none";
    documentMain.style.display = "block";
    documentH1.innerText = "Add Product"
    documentBtnSave.addEventListener('click', (e) => pushDatabase(e));
}

/* INIT_EDIT_PRODUCT - OPTION 2 */

async function fillForm(id) { // Retrieves product data from the database using the provided id and fills the corresponding form fields.
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

async function showEditProductPage(product) { // Displays the edit page for a product.
    try {
        if (product) {
            await fillForm(currentID);
            documentH1.innerText = "Edit Product";
            documentBtnDelete.style.display = "block";
            documentBtnSave.addEventListener('click', (e) => saveChanges(currentID, e));
            documentBtnDelete.addEventListener('click', () => deleteRecord(currentID));
        } else {
            console.error('Product not found with ID:', currentID);
        }
    } catch (error) {
        console.error('Error showing edit:', error);
    }
}

addEventListener('load', init);

async function init() { // Initializes the page based on the URL parameters.
    try {
        if (params.has('id')) { // If the 'id' parameter is present, it retrieves the corresponding product from the database and shows the edit page.
            currentID = params.get('id');
            showEditProductPage(await getDatabase(currentID));
        } else { // If neither 'id' nor 'name' parameters are present, it shows the add page.
            showAddProductPage();
        }
    } catch (error) {
        console.error('Error initializing:', error);
    }
}

/* POST AND PUT RECORD */

function createObjectFromForm() { // Converts form data into an object.
    const formData = new FormData(document.querySelector('form')); // Gets the form data
    console.log(formData);
    const data = {
    };

    formData.forEach((value, key) => { // Convert form data to an object through a special, specific use of the forEach method in forms.
        data[key] = value;
    });
    return data;
}


async function pushDatabase(e) {
    e.preventDefault(); // Impedisce il comportamento predefinito del modulo HTML
    const data = createObjectFromForm();
    try {
        const response = await fetch(searchURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        if (!response.ok) {
            throw new Error('Errore nella richiesta di salvataggio');
        }
        console.log('response ok and data sent:' + response);
        alert('Product updated successfully');
        window.location.href = 'index.html';
        return response.json();
    } catch (error) {
        console.error('Si è verificato un errore durante il salvataggio dei dati:', error);
        throw error; // Rilancia l'errore per gestirlo nell'ambito chiamante
    }
}


async function saveChanges(id, e) {
    e.preventDefault(); // Impedisce il comportamento predefinito del modulo HTML
    try {
        const updatedProduct = createObjectFromForm();
        const response = await fetch(searchURL + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedProduct),
        });
        if (!response.ok) {
            throw new Error('Failed to update product');
        }
    } catch (error) {
        console.error('Error saving changes:', error);
        throw error;
    }
    
    window.location.href = 'index.html';
}



/* DELETE AND RESET BUTTONS */

async function deleteRecord(id) { // Deletes a record from the database using the provided id.
    try {
        const response = await fetch(searchURL + id, {
            method: 'DELETE',
            /*  headers: {
                 'Authorization': 'Bearer ' + API_KEY
             } */
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

const documentBtnReset = document.getElementById('btnReset');
documentBtnReset.addEventListener('click', clearInputFields);

async function clearInputFields() { // Clears the input fields on the page.
    try {
        document.getElementById('productName').value = '';
        document.getElementById('brand').value = '';
        document.getElementById('price').value = '';
        document.getElementById('imgUrl').value = '';
        document.getElementById('productDescription').value = '';
    } catch (error) {
        console.error('Error clearing input fields:', error);
    }
}

console.log(currentID)



