const testo = document.getElementById('timer');

window.addEventListener('load', function () {
    const secondiMemorizzati = sessionStorage.getItem('secondi');
    
    if (secondiMemorizzati) {
        startTimer(1000, parseInt(secondiMemorizzati, 10));
    } else {
        startTimer(1000);
    }
});

function startTimer(intervalloMillis, secondiIniziali = 0) {
    let milliSecondi = secondiIniziali * 1000;

     intervallo = setInterval(function () {
        milliSecondi += intervalloMillis;

        const secondiTrascorsi = Math.floor(milliSecondi / 1000);
        testo.innerHTML = `Sono trascorsi ${secondiTrascorsi} secondi dal caricamento della pagina`;
        sessionStorage.setItem('secondi', secondiTrascorsi);
    }, intervalloMillis);
}

