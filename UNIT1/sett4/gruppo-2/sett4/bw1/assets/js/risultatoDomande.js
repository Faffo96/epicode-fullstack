const indiceRisposteArray = [];
let ctxGrafico;
let documentGrafico = document.getElementById('graficoCanvas');
const myDataStorage = localStorage.getItem('myData');
console.log(myDataStorage)
let myData = myDataStorage.split(',');
const canvas = document.getElementById("canvas"); 
const rateUsBtn = document.getElementById("rateUs");

const domandeIndexStorage = localStorage.getItem('domandeIndexArray');
let arrayIndexDomande = domandeIndexStorage.split(',');

console.log(arrayIndexDomande);

const risposteIndexStorage = localStorage.getItem('risposteIndexArray');
let arrayElencoRisposte = risposteIndexStorage.split(',');

console.log(arrayElencoRisposte);

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

    question:
      "In the programming language Java, which of these keywords would you put on a variable to make sure it doesn't get modified?",
    correct_answer: "Final",
    incorrect_answers: ["Static", "Private", "Public"],
  },
  {

    question: "The logo for Snapchat is a Bell.",
    correct_answer: "False",
    incorrect_answers: ["True"],
  },
  {

    question:
      "Pointers were not used in the original C programming language; they were added later on in C++.",
    correct_answer: "False",
    incorrect_answers: ["True"],
  },
  {
    question:
      "What is the most preferred image format used for logos in the Wikimedia database?",
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

    question:
      "What is the code name for the mobile operating system Android 7.0?",
    correct_answer: "Nougat",
    incorrect_answers: [
      "Ice Cream Sandwich",
      "Jelly Bean",
      "Marshmallow",
    ],
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

    question:
      "Which programming language shares its name with an island in Indonesia?",
    correct_answer: "Java",
    incorrect_answers: ["Python", "C", "Jakarta"],
  },
];

Chart.defaults.global.tooltips.enabled = false;

let chart = new Chart(canvas, {
  type: 'doughnut',
  data: {
    datasets: [{
      data: myData,
      backgroundColor: ['#d20094', '#00FFFF'],
      borderWidth: 0,
    }]
  },
  options: {
    maintainAspectRatio: false,
    hover: { mode: null },
    animation: { duration: 2000, },
    cutoutPercentage: 75,
  },
  tooltips: {
    enabled: true, // Abilita i tooltip
    callbacks: {
      label: function (tooltipItem, data) {
        // Ottieni il valore del segmento attuale
        var value = data.datasets[0].data[tooltipItem.index];
        if (tooltipItem.index === 0) {
          return "Wrong " + value * 100 + '%';
        } else {
          return "Correct " + value * 100 + '%';
        }
      }
    }
  }
}); 



function stampa() {

}

const verificaDomanda = (pulsanteCliccato) => {
  let documentRisposte = Array.from(document.getElementsByClassName("btnAsk"));
  for (let index = 0; index < documentRisposte.length; index++) {
    const rispostaCorrente = documentRisposte[index].innerText;
    if (documentRisposte[pulsanteCliccato - 1] !== undefined) {
      const rispostaUtente = documentRisposte[pulsanteCliccato - 1].innerText;
      if (rispostaUtente === rispostaCorrente) {
        risposte.push(true);
        break;
      } else {
        risposte.push(false);
        break;
      }
    }
  }
}



const lala = () => {


  for (let i = 0; i < arrayIndexDomande.length; i++) {
    let newDiv1 = document.createElement("div")
    let newP1 = document.createElement("p")
    newP1.setAttribute("class", "contenitoreRisposta_1");
    let newP2 = document.createElement("p")
    newP2.setAttribute("class", "contenitoreRisposta_2");
    let newP3 = document.createElement("p")
    newP3.setAttribute("class", "contenitoreRisposta_3");

    let newDiv2 = document.createElement("div")

    let newP4 = document.createElement("p")
    newP4.setAttribute("class", "contenitoreRisposta_4");
    let newP5 = document.createElement("p")
    newP5.setAttribute("class", "contenitoreRisposta_5");
    newP1.innerText = i + 1;


    let index = arrayIndexDomande[i];
    newP3.innerText = questions[index].question;

    let singolaRisposta = arrayElencoRisposte[i];
    newP4.innerText = singolaRisposta;


    newDiv1.appendChild(newP1);
    newDiv1.appendChild(newP2);
    newDiv1.appendChild(newP3);
    newDiv2.appendChild(newP4);

    if (singolaRisposta !== questions[index].correct_answer) {
      newP5.innerText = questions[index].correct_answer
      newP2.innerText = "❌"
      newDiv2.appendChild(newP5);
      newP4.style.color = "red"; 
    } else {
      newP2.innerText = "✅"
      newP4.style.color = "rgb(27, 211, 27)"; 
    }


    let divInterno = document.createElement("div")
    divInterno.setAttribute("class", "contenitoreRisposta");
    console.log(divInterno);
    divInterno.appendChild(newDiv1);
    divInterno.appendChild(newDiv2);

    let newDiv3 = document.getElementById("contenitoreRisposte");
    newDiv3.appendChild(divInterno);

  }


}

const init = () => {
  ctxGrafico = canvas.getContext('2d');
  lala(); 

}

rateUs.addEventListener("click", function(e){
  e.preventDefault;
    window.location.href = 'feedback.html';
})

addEventListener('load', init); 
