let documentButton1 = document.querySelector('.btn1');
let documentButton2 = document.querySelector('.btn2');
let documentButton3 = document.querySelector('.btn3');
const viewButtons = document.querySelectorAll('.btnView');
let documentModalImg = document.querySelector('.modalImg')

let endPoint = 'https://api.pexels.com/v1/search?query=';

addEventListener("load", init);

function init() {
    loadLastSearch();
}

function loadLastSearch() {
    const lastSearch = localStorage.getItem('lastSearch');
    if (lastSearch) {
        loadImg(lastSearch);
    }
}

viewButtons.forEach(button => {
    button.addEventListener('click', function () {
        // Trova l'immagine associata alla card padre del bottone cliccato
        const card = this.closest('.card');
        const img = card.querySelector('.card-img-top');

        // Ottieni l'URL dell'immagine e stampalo nella console
        const src = img.getAttribute('src');

        setModalImg(src);
    })
})

function setModalImg(src) {
    documentModalImg.setAttribute('src', `${src}`);
    documentModalImg.setAttribute('width', '450px');
}

async function loadImg(inputUtente) {
    try {
        const response = await fetch(endPoint + inputUtente, {
            method: 'GET',
            headers: {
                Authorization: "Bearer TXAl4tsq0AH939vI13rKvpyMo7xSgfkSwtTUiEnLKjjvEI0zqABAVoL5"
            }
        });
        if (!response.ok) {
            throw new Error('Errore nella richiesta');
        }
        const fetchResult = await response.json();
        let documentImgs = document.getElementsByClassName('bd-placeholder-img card-img-top');
        for (let i = 0; i < documentImgs.length; i++) {
            const element = documentImgs[i];
            element.setAttribute('src', `${fetchResult.photos[i].src.original}`);
        }

        // Salvataggio delle ultime immagini mostrate nel local storage
        localStorage.setItem('lastImages', JSON.stringify(fetchResult.photos.map(photo => photo.src.original)));
    } catch (error) {
        console.error('Errore durante la richiesta:', error);
    }
}

documentButton1.addEventListener("click", () => {
    loadImg('mountains');
    localStorage.setItem('lastSearch', 'mountains');
});

documentButton2.addEventListener("click", () => {
    loadImg('sunsets');
    localStorage.setItem('lastSearch', 'sunsets');
});

documentButton3.addEventListener("click", () => {
    let valueUtente = document.getElementById('valueUtente').value;
    loadImg(valueUtente);
    localStorage.setItem('lastSearch', valueUtente);
});


