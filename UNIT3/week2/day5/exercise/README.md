# Epicode-ToDo

<!-- BANNER DA INSERIRE QUI --> 
<div align="center">
  <img src="./src/assets/img/banner.png" alt="banner">
</div>

<!-- piccola descrizione del progetto --> 
<h3 align="center">The goal of this project is to use GET and POST https requestes in angular</h3>
<h4 align="center"> Live Preview: <a href="https://epicode-todo.web.app/">https://epicode-todo.web.app/</a></h4>

<br>
<!-- SPAZIO DA METTERE + BADGES (dynamic e static) --> 
<!-- https://shields.io/badges // link per creare le badges --> 
<br>

I focused on those main objectives:
1. _Identify the main components of the pages_
2. _Define navigation routes between pages_
3. _HTML creation and SCSS styling_
4. _Get the datas i need via http request_
5. _Pass the datas to all the components using Behavior Subjects to dynamically display it_

<br>

## Table of Contents 

- [How it is structured](#How-it-is-structured)
- [How it work](#how-it-work)

<br> 

## How it is structured

My project is divided into:

> ![Static Badge](https://img.shields.io/badge/HTML-black?style=for-the-badge&logo=HTML5)
- Home page
- Pending Tasks Page
- Completed Tasks Page
- Users Page

<br>

 ![Static Badge](https://img.shields.io/badge/Javascript-black?style=for-the-badge&logo=javascript)
 
- [Epicode-ToDo](#epicode-todo)
  - [Table of Contents](#table-of-contents)
  - [How it is structured](#how-it-is-structured)
  - [How it work](#how-it-work)
    - [home.Component.ts **:**](#homecomponentts-)
    - [pendingTasks.Component.ts **:**](#pendingtaskscomponentts-)
    - [completedTasks.Component.ts **:**](#completedtaskscomponentts-)
    - [users.Component.ts **:**](#userscomponentts-)
  - [Partecipants](#partecipants)
  
 > ![Static Badge](https://img.shields.io/badge/CSS-black?style=for-the-badge&logo=CSS3)
- Multiple stylesheet

<br>
  
## How it work


<br>

### home.Component.ts **:**

The Home component takes care of doing the http GET request in the services users and tasks. The GET is done only once in the user's entire browsing experience to make the application as lightweight as possible. The data is then saved locally in an array and passed to the behavior subjects, which will track changes in real time while the service distributes the data to the components that require it.

<br>

### pendingTasks.Component.ts **:**

The component takes the data from the services, without running a new GET, and displays it. The same component is shown both on the home page and on the pending tasks detail page. When the status of a task changes, it is moved to the top of the list of corresponding tasks in real time. The component will take care of changing the status of the task on click and send both the change to the database via POST, and the data to the behavior subject in the service.

<br>

### completedTasks.Component.ts **:**

Same of pending tasks.

<br> 

### users.Component.ts **:**

The component takes the data from the services, without running a new GET, and displays it. The page shows a list of only users who have assigned tasks (pending and unpending). The task behavior remains the same as on the other pages, even though I was not able to reuse the components

<br> 


## Partecipants

| PARTECIPANTS       | ROLE                      | ACCOUNT GIT                                                                                     |
| ------------------ | ------------------------- | ----------------------------------------------------------------------------------------------- |
| Fabio Scaramozzino | Epicode FullStack Student | ![Static Badge](https://img.shields.io/badge/Faffo96-%233eb752?style=for-the-badge&logo=github) |

<br>

- [Back to top! :D](#Epicode-ToDo)

 





