const documentImgs = document.getElementsByClassName('carousel1');
const documentImgs2 = document.getElementsByClassName('carousel2');
const documentImgs3 = document.getElementsByClassName('carousel3');
let randomNumbers = [];

/* Funzione per generare un numero random */
const generateRandomNumber = (min, max) => {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  };

  /* Funzione per popolare il carosello con immagini in un ordine random ma senza ripetizioni */
  const populateCarousel = (carouselArray, generalSrc) => {
    for (let index = 0; index < carouselArray.length; index++) {
        let randomNumber;
        if (randomNumbers.length === 6) {
            randomNumbers = [];
        }
        do {
            randomNumber = generateRandomNumber(0, 10);
        } while (randomNumbers.includes(randomNumber)); // Continua a generare un nuovo numero finché non è univoco
        randomNumbers.push(randomNumber);
        carouselArray[index].setAttribute('src', `${generalSrc}${randomNumber}.jpg`);
    }
}

populateCarousel(documentImgs, 'assets/media/trendingNow/media')
populateCarousel(documentImgs2, 'assets/media/watchItAgain/media')
populateCarousel(documentImgs3, 'assets/media/newReleases/media')


// Funzione per aprire il modal al caricamento della pagina
function openModalOnLoad() {
    var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
    myModal.show();
}

// Aggiunta della classe di dissolvenza al modal dopo un leggero ritardo, in modo che venga applicata la classe
function addFadeClassToModal() {
    const modal = document.getElementById('exampleModal');
    if (modal.classList.contains('show')) {
        modal.classList.add('fade');
    }
}

document.addEventListener('DOMContentLoaded', openModalOnLoad);
setTimeout(addFadeClassToModal, 100);


/* AUDIO INTRO */

let checkbox = document.getElementById("checkbox");
let audio = new Audio();
audio.src = "./netflix-opening-intro-4k.mp3";

// Gestione dell'evento "canplaythrough" per assicurarsi che l'audio sia caricato prima di riprodurlo
audio.addEventListener("canplaythrough", function() {
    console.log("Audio caricato correttamente");
    checkbox.addEventListener("change", function() {
        if (checkbox.checked) {
            audio.play();
        } else {
            audio.pause(); 
        }
    });
});

