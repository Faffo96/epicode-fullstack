// Costanti HTML
const documentCanvasClock = document.getElementById('canvasClock');
const testoDomanda = document.getElementById('documentDomanda');
const documentRisposta1 = document.getElementById('risposta1');
const documentRisposta2 = document.getElementById('risposta2');
const documentRisposta3 = document.getElementById('risposta3');
const documentRisposta4 = document.getElementById('risposta4');

// Variabili globali 
let documentRisposte;
let domandeIndexArray = [];
let risposteIndexArray = [];
let rispostaCorrente;
let domandaCorrente = 0;
let intervallo;
Chart.defaults.global.tooltips.enabled = false;
let risposte = [];
let domandeEstratte = [];
let risposteEstratte = [];
let indexGlobaleDomandeDatabase = 0;

// Array database
const questions = [
  {
    question: "What does CPU stand for?",
    correct_answer: "Central Processing Unit",
    incorrect_answers: [
      "Central Process Unit",
      "Computer Personal Unit",
      "Central Processor Unit",
    ],
  },
  {
    question: "In the programming language Java, which of these keywords would you put on a variable to make sure it doesn't get modified?",
    correct_answer: "Final",
    incorrect_answers: ["Static", "Private", "Public"],
  },
  {
    question: "The logo for Snapchat is a Bell.",
    correct_answer: "False",
    incorrect_answers: ["True"],
  },
  {
    question: "Pointers were not used in the original C programming language; they were added later on in C++.",
    correct_answer: "False",
    incorrect_answers: ["True"],
  },
  {
    question: "What is the most preferred image format used for logos in the Wikimedia database?",
    correct_answer: ".svg",
    incorrect_answers: [".png", ".jpeg", ".gif"],
  },
  {
    question: "In web design, what does CSS stand for?",
    correct_answer: "Cascading Style Sheet",
    incorrect_answers: [
      "Counter Strike: Source",
      "Corrective Style Sheet",
      "Computer Style Sheet",
    ],
  },
  {
    question: "What is the code name for the mobile operating system Android 7.0?",
    correct_answer: "Nougat",
    incorrect_answers: ["Ice Cream Sandwich", "Jelly Bean", "Marshmallow"],
  },
  {
    question: "On Twitter, what is the character limit for a Tweet?",
    correct_answer: "140",
    incorrect_answers: ["120", "160", "100"],
  },
  {
    question: "Linux was first created as an alternative to Windows XP.",
    correct_answer: "False",
    incorrect_answers: ["True"],
  },
  {
    question: "Which programming language shares its name with an island in Indonesia?",
    correct_answer: "Java",
    incorrect_answers: ["Python", "C", "Jakarta"],
  },
];


// Ottiene il contesto del timer
let ctxClock = documentCanvasClock.getContext('2d');
// Array per Chart.js; i valori possono andare da 0 a 1: 0 e' completamente sbagliato, 1 completamente giusto.
let myData = [0, 1];

let chart = new Chart(documentCanvasClock, {
  type: 'doughnut',
  data: {
    datasets: [{
      data: myData,
      backgroundColor: ['#876191', '#00FFFF'],
      borderWidth: 0,
    }]
  },
  options: {
    maintainAspectRatio: false,
    hover: { mode: null },
    animation: { duration: 0 },
    cutoutPercentage: 75,
  }
});

// Funzione che rende non cliccabili i pulsanti dell'HTML
function disabilitaRisposte() {
  documentRisposta1.setAttribute("disabled", "");
  documentRisposta2.setAttribute("disabled", "");
  documentRisposta3.setAttribute("disabled", "");
  documentRisposta4.setAttribute("disabled", "");
}

// Funzione per verificare la risposta dell'utente
const verificaDomanda = (pulsanteCliccato) => {
  let documentRisposte = Array.from(document.getElementsByClassName("btnAsk"));
  for (let index = 0; index < documentRisposte.length; index++) {
    /* console.log("indice al momento della verifica " + indexGlobaleDomandeDatabase); */
    const rispostaCorretta = questions[indexGlobaleDomandeDatabase].correct_answer;
    if (documentRisposte[pulsanteCliccato - 1] !== undefined) {
      rispostaCorrente = documentRisposte[pulsanteCliccato - 1].innerText;
      const rispostaUtente = documentRisposte[pulsanteCliccato - 1].innerText;
      if (rispostaUtente === rispostaCorretta) {
        risposte.push(true);
        break;
      } else {
        risposte.push(false);
        break;
      }
    }
  }
}


// Funzione per passare alla domanda successiva
const domandaSuccessiva = () => {
  localStorage.setItem('risposte', risposte); // Salva le risposte per la pagina del risultato
  clearInterval(intervallo);
  domandaCorrente++;
  if (domandaCorrente > questions.length - 1 ) {
    window.location.href = "risultato.html";
  }
  visualizzaDati();
  startTimer(30000);
};

// Funzione per inizializzare la visualizzazione delle domande e avviare il timer
function init() {
  visualizzaDati();
  startTimer(30000);
}

// Funzione per rendere cliccabili i pulsanti dell'HTML
function ablitaRisposte() {
  documentRisposta1.removeAttribute("disabled");
  documentRisposta2.removeAttribute("disabled");
  documentRisposta3.removeAttribute("disabled");
  documentRisposta4.removeAttribute("disabled");
}

// Funzione per generare un indice casuale per le domande
const getRandomIndex = (array, n) => {
  let randomIndex;
  if (array.length === questions.length) {
    return;
  }
  do {
    randomIndex = Math.floor(Math.random() * n);
  } while (array.includes(randomIndex));
  array.push(randomIndex);
  return randomIndex;
}

// Funzione per visualizzare le domande e le risposte
const visualizzaDati = () => {
  let documentRisposteArrayTesti = [];
  risposteEstratte = [];
  indexGlobaleDomandeDatabase = getRandomIndex(domandeEstratte, questions.length);
  testoDomanda.innerText = questions[indexGlobaleDomandeDatabase].question;
  console.log("indice al momento della visualizzazione " + indexGlobaleDomandeDatabase);

  documentRisposteArrayTesti.push(questions[indexGlobaleDomandeDatabase].correct_answer);
  documentRisposteArrayTesti.push(questions[indexGlobaleDomandeDatabase].incorrect_answers[0]);
  documentRisposteArrayTesti.push(questions[indexGlobaleDomandeDatabase].incorrect_answers[1]);
  documentRisposteArrayTesti.push(questions[indexGlobaleDomandeDatabase].incorrect_answers[2]);

  documentRisposta1.innerText = documentRisposteArrayTesti[getRandomIndex(risposteEstratte, 4)];
  documentRisposta2.innerText = documentRisposteArrayTesti[getRandomIndex(risposteEstratte, 4)];

  if (documentRisposteArrayTesti.length > 2) {
    documentRisposta3.innerText = documentRisposteArrayTesti[getRandomIndex(risposteEstratte, 4)];
    documentRisposta4.innerText = documentRisposteArrayTesti[getRandomIndex(risposteEstratte, 4)];
  }

  let documentRisposte = document.getElementsByClassName('btnAsk');
  let documentRisposteArray = Array.from(documentRisposte);

  documentRisposteArray.forEach(element => {
    element.style.display = 'inline';
    if (element.innerText === 'undefined') {
      element.style.display = 'none';
    }
  });

  documentRisposteArrayTesti = [];
  document.getElementById("mostraNDomande").innerText = "QUESTION " + (parseInt(domandaCorrente) + 1);
}

// Funzione per avviare il timer
const startTimer = (durataMillis) => {
  let index1 = 0;
  let index2 = 1;
  let milliSecondi = 0;
  const documentSecondi = document.getElementById("documentSecondi");
  intervallo = setInterval(function () {
    index1 += 1 / durataMillis * 100;
    index2 -= 1 / durataMillis * 100;

    let secondiRimanenti = Math.floor((durataMillis - milliSecondi) / 1000);
    documentSecondi.innerText = secondiRimanenti;

    milliSecondi += 100;

    if (milliSecondi >= durataMillis) {
      clearInterval(intervallo);
      risposte.push(false);
      domandaSuccessiva();
      return;
    }

    myData = [index1, index2];
    chart.data.datasets[0].data = myData;
    chart.update();
  }, 100);
};

const trasferisciDati = () => {

  documentRisposte = Array.from(document.getElementsByClassName("btnAsk"));

  console.log(domandeIndexArray);
  domandeIndexArray.push(indexGlobaleDomandeDatabase);


  risposteIndexArray.push(rispostaCorrente);


  localStorage.setItem('domandeIndexArray', domandeIndexArray);
  localStorage.setItem('risposteIndexArray', risposteIndexArray);

  console.log(risposteIndexArray); 

}

// Intercetta il click dei corrispettivi pulsanti 
documentRisposta1.addEventListener("click", function () {
  disabilitaRisposte();
  verificaDomanda(1);
  trasferisciDati();
  domandaSuccessiva();
  ablitaRisposte();
  
});
documentRisposta2.addEventListener("click", function () {
  disabilitaRisposte();
  verificaDomanda(2);
  trasferisciDati();  
  domandaSuccessiva();
  ablitaRisposte();
});
documentRisposta3.addEventListener("click", function () {
  disabilitaRisposte();
  verificaDomanda(3);
  trasferisciDati();
  domandaSuccessiva();
  ablitaRisposte();
});
documentRisposta4.addEventListener("click", function () {
  disabilitaRisposte();
  verificaDomanda(4);
  trasferisciDati();
  domandaSuccessiva();
  ablitaRisposte();
});

// Richiama init con l'evento load 
addEventListener("load", init);
