const navChange = document.getElementById('head_1');
const buttonChange = document.getElementById('buttonChange')

window.addEventListener('scroll', function () {
    const scrollVerticale = window.scrollY;

    const scrollIntercettazione = 465;


    if (scrollVerticale > scrollIntercettazione) {
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



const alternareVisibilita = (elemento) => {
    const opacityAttuale = parseFloat(elemento.getAttribute('opacity'));
    if (opacityAttuale === 1) {
        elemento.setAttribute('opacity', '0');
    } else if (opacityAttuale === 0) {
        elemento.setAttribute('opacity', '1');
    }
}

const lettere = document.querySelectorAll('#M > g');
const opacitaZero = [];
const opacitaUno = [];
let opacityAttuale;

function init() {
    eseguiCicloOpacitaUno();
}

function eseguiCicloOpacitaUno() {
    let count = 0;
    const intervalloUno = setInterval(() => {
        const numeroRandom = Math.floor(Math.random() * lettere.length);
        opacityAttuale = parseFloat(lettere[numeroRandom].getAttribute('opacity'));
        if (opacityAttuale === 0) {
            alternareVisibilita(lettere[numeroRandom]);
            console.log(11);
        }
        count++;
        if (count === 140) {
            clearInterval(intervalloUno);
            eseguiCicloOpacitaZero();
        }
    }, 30);
}

function eseguiCicloOpacitaZero() {
    let count = 0;
    const intervalloZero = setInterval(() => {
        const numeroRandom = Math.floor(Math.random() * lettere.length);
        opacityAttuale = parseFloat(lettere[numeroRandom].getAttribute('opacity'));
        if (opacityAttuale === 1) {
            alternareVisibilita(lettere[numeroRandom]);
            console.log(22);
        }
        count++;
        if (count === 140) {
            clearInterval(intervalloZero);
            eseguiCicloOpacitaUno();
        }
    }, 30);
}

addEventListener('load', init);