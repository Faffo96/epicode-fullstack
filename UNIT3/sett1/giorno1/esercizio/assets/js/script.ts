const documentCallPhoneNumberInput: HTMLInputElement = document.getElementById('callPhoneNumber') as HTMLInputElement;
const documentCallBtn: HTMLElement = document.getElementById('callBtn')!;
const documentEndCallBtn: HTMLElement = document.getElementById('endCallBtn')!;
let intervalID: number;
let currentUser: User;

interface Phone {
    credit: number;
    callMinutes: number;

    addCredit(quantity: number): void;
    call(phoneNumber: number): void;
    getCredit(): number;
    getCallMinutes(): number;
    resetCallsMinutes(): void;
}

class User implements Phone {
    name: string;
    surname: string;
    credit: number;
    callMinutes: number;

    constructor(_name: string, _surname: string, _credit: number, _callMinutes: number) {
        this.name = _name;
        this.surname = _surname;
        this.credit = _credit;
        this.callMinutes = _callMinutes;
    }

    addCredit(quantity: number): void {
        this.credit += quantity;
    }

    call(callMinutes: number): void {
        this.credit = this.credit - (callMinutes * 0.20);
    }

    getCredit(): number {
        return this.credit;
    }

    getCallMinutes(): number {
        return this.callMinutes;
    }

    resetCallsMinutes(): void {
        this.callMinutes = 0;
    }

}


function updateUsersSelect(user: User) {
    const selectElement: HTMLSelectElement = document.getElementById('usersSelect') as HTMLSelectElement;
    const optionElement: HTMLOptionElement = document.createElement('option');
    optionElement.value = user.name;
    optionElement.textContent = user.name + ' ' + user.surname;
    selectElement.appendChild(optionElement);
}

function handleCallButtonClick() {
    const calledNumber: number = parseInt(documentCallPhoneNumberInput.value);
    if (/* currentUser.credit > 0.20 && */ currentUser.callMinutes < 60) {
        intervalID = setInterval(() => {
            currentUser.callMinutes += 1;
            console.log("Chiamata iniziata");
        }, 60000);
    }
}


function handleEndCallButtonClick() {
    clearInterval(intervalID);
    console.log('Chiamata finita. Minuti totali di chiamata: ' + currentUser.getCallMinutes());
}


documentCallBtn.addEventListener('click', handleCallButtonClick);
documentEndCallBtn.addEventListener('click', handleEndCallButtonClick);

document.getElementById('usersSelect')?.addEventListener('change', (event) => {
    const selectElement: HTMLSelectElement = event.target as HTMLSelectElement;
    const selectedUserName: string = selectElement.value;

    if (selectedUserName === 'Fabio') {
        currentUser = User1;
    } else if (selectedUserName === 'Mario') {
        currentUser = User2;
    } else if (selectedUserName === 'Paolo') {
        currentUser = User3;
    }
});




const User1 = new User('Fabio', 'Scaramozzino', 10, 0);
const User2 = new User('Mario', 'Scaramozzino', 10, 0);
const User3 = new User('Paolo', 'Scaramozzino', 10, 0);
updateUsersSelect(User1);
updateUsersSelect(User2);
updateUsersSelect(User3);