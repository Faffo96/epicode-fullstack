var _a;
var documentCallPhoneNumberInput = document.getElementById('callPhoneNumber');
var documentCallBtn = document.getElementById('callBtn');
var documentEndCallBtn = document.getElementById('endCallBtn');
var intervalID;
var currentUser;
var User = /** @class */ (function () {
    function User(_name, _surname, _credit, _callMinutes) {
        this.name = _name;
        this.surname = _surname;
        this.credit = _credit;
        this.callMinutes = _callMinutes;
    }
    User.prototype.addCredit = function (quantity) {
        this.credit += quantity;
    };
    User.prototype.call = function (callMinutes) {
        this.credit = this.credit - (callMinutes * 0.20);
    };
    User.prototype.getCredit = function () {
        return this.credit;
    };
    User.prototype.getCallMinutes = function () {
        return this.callMinutes;
    };
    User.prototype.resetCallsMinutes = function () {
        this.callMinutes = 0;
    };
    return User;
}());
function updateUsersSelect(user) {
    var selectElement = document.getElementById('usersSelect');
    var optionElement = document.createElement('option');
    optionElement.value = user.name;
    optionElement.textContent = user.name + ' ' + user.surname;
    selectElement.appendChild(optionElement);
}
function handleCallButtonClick() {
    var calledNumber = parseInt(documentCallPhoneNumberInput.value);
    if ( /* currentUser.credit > 0.20 && */currentUser.callMinutes < 60) {
        intervalID = setInterval(function () {
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
(_a = document.getElementById('usersSelect')) === null || _a === void 0 ? void 0 : _a.addEventListener('change', function (event) {
    var selectElement = event.target;
    var selectedUserName = selectElement.value;
    if (selectedUserName === 'Fabio') {
        currentUser = User1;
    }
    else if (selectedUserName === 'Mario') {
        currentUser = User2;
    }
    else if (selectedUserName === 'Paolo') {
        currentUser = User3;
    }
});
var User1 = new User('Fabio', 'Scaramozzino', 10, 0);
var User2 = new User('Mario', 'Scaramozzino', 10, 0);
var User3 = new User('Paolo', 'Scaramozzino', 10, 0);
updateUsersSelect(User1);
updateUsersSelect(User2);
updateUsersSelect(User3);
