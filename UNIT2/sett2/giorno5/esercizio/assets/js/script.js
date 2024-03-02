const documentImgs = document.getElementsByClassName('carousel1');
const documentImgs2 = document.getElementsByClassName('carousel2');
const documentImgs3 = document.getElementsByClassName('carousel3');
let randomNumbers = [];


const generateRandomNumber = (min, max) => {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  };

  const populateCarousel = (carouselArray, generalSrc) => {
    for (let index = 0; index < carouselArray.length; index++) {
        let randomNumber;
        if (randomNumbers.length === 6) {
            randomNumbers = [];
        }
        do {
            randomNumber = generateRandomNumber(0, 10);
        } while (randomNumbers.includes(randomNumber)); // Continua a generare un nuovo numero finché non è univoco
        randomNumbers.push(randomNumber); // Aggiungi il numero univoco all'array
        carouselArray[index].setAttribute('src', `${generalSrc}${randomNumber}.jpg`);
    }
}

populateCarousel(documentImgs, 'assets/media/trendingNow/media')
populateCarousel(documentImgs2, 'assets/media/watchItAgain/media')
populateCarousel(documentImgs3, 'assets/media/newReleases/media')


document.addEventListener('DOMContentLoaded', function() {
    var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
    myModal.show();
  });

  function addFadeClassToModal() {
    const modal = document.getElementById('exampleModal');
    if (modal.classList.contains('show')) {
        modal.classList.add('fade');
    }
}

setTimeout(addFadeClassToModal, 100);
