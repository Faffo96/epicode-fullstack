
let taskList = document.getElementById('taskList');
let userTask = document.getElementById('userTask');
let addBtn = document.getElementById('addBtn');
let removeBtns = document.querySelectorAll('.task input');

addBtn.addEventListener("click", function (event) {
    createTask();
    console.log(removeBtns);
})
0
createTask = () => { 
    let newTask = document.createElement('div');
    newTask.setAttribute('class', 'task');

    let newP = document.createElement('p');
    newP.innerText = userTask.value;

    let newButton = document.createElement('input');
    newButton.setAttribute('type', 'button');
    newButton.setAttribute('value', 'X');
    
    newTask.appendChild(newP);
    newTask.appendChild(newButton);
    taskList.appendChild(newTask);
    
    userTask.value = '';

    newButton.addEventListener("click", function (event) {
        event.preventDefault();
        let taskToRemove = newTask;
        taskList.removeChild(taskToRemove);
        
    });
    removeBtns = document.querySelectorAll('.task input');
}




removeBtns.forEach(removeBtn => {
    removeBtn.addEventListener("click", function (event) {
        event.preventDefault();
        let taskToRemove = removeBtn.parentNode;
        taskList.removeChild(taskToRemove);
        removeBtns = document.querySelectorAll('.task input');
        console.log(removeBtns);
    });
}
);

