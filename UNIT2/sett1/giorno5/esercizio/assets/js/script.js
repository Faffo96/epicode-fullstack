/* HEADER'S STICKY ANIMATION */

const navChange = document.getElementById('head_1');
const buttonChange = document.getElementById('buttonChange')

window.addEventListener('scroll', function () {
    const verticalScroll = window.scrollY;

    const scrollTracking = 465;


    if (verticalScroll > scrollTracking) {
        navChange.style.backgroundColor = 'white';
        buttonChange.style.backgroundColor = '#1a8917'
    } else {
        navChange.style.backgroundColor = '';
        buttonChange.style.backgroundColor = ''
    }
    window.addEventListener('beforeunload', () => {
        window.scrollTo(0, 0);
    });
});

/* M'S ANIMATION */

const ViewAndHide = (element) => {
    const currentOpacity = parseFloat(element.getAttribute('opacity'));
    if (currentOpacity === 1) {
        element.setAttribute('opacity', '0');
    } else if (currentOpacity === 0) {
        element.setAttribute('opacity', '1');
    }
}

const letters = document.querySelectorAll('#M > g');
let currentOpacity;

function init() {
    hidingCycle();
}

function showingCycle() {
    let count = 0;
    const intervalloUno = setInterval(() => {
        const numeroRandom = Math.floor(Math.random() * letters.length);
        currentOpacity = parseFloat(letters[numeroRandom].getAttribute('opacity'));
        if (currentOpacity === 0) {
            ViewAndHide(letters[numeroRandom]);
            console.log(11);
        }
        count++;
        if (count === 140) {
            clearInterval(intervalloUno);
            hidingCycle();
        }
    }, 30);
}

function hidingCycle() {
    let count = 0;
    const intervalloZero = setInterval(() => {
        const randomNumber = Math.floor(Math.random() * letters.length);
        currentOpacity = parseFloat(letters[randomNumber].getAttribute('opacity'));
        if (currentOpacity === 1) {
            ViewAndHide(letters[randomNumber]);
        }
        count++;
        if (count === 140) {
            clearInterval(intervalloZero);
            showingCycle();
        }
    }, 30);
}

addEventListener('load', init);