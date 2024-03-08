const searchURL = 'https://striveschool-api.herokuapp.com/api/product/';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';
let ActualItem;
const documentBtnSave = document.getElementById('btnSave');

documentBtnSave.addEventListener('click', function postDatabase() {
    const formData = new FormData(document.querySelector('form')); // Ottieni i dati del modulo
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
        // Aggiungi qui eventuali azioni supplementari da eseguire dopo il salvataggio dei dati
    })
    .catch(error => {
        console.error('Si è verificato un errore durante il salvataggio dei dati:', error);
        // Gestisci eventuali errori qui
    });
    console.log(data)
});


