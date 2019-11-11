# Teaching-HEIGVD-AMT-2019-Project-One
In this file, we will describe how we implemented the project and all components.

## Models
In our project, we only have two model :
+ User
+ Movie

The first one is represented by an id, a first name, a last name, an email and a password.

The second one is represented by an id, a title and a year of production.

## DAOs
To implements our DAO we have done an interface `IDAO` that implements the four CRUD methods with genericity.

For each models, we create a interface which implement the `IDAO`. We add to this interface all specifics methods for this DAO. Finally, we create a class for each DAO and implements all methods of the two previous interfaces.

The two DAO class are connected to a `DataSource` to have an access in the database.

The methods are typically used to get all movies, a specific user and so on.

## Servlets
In this project, we have writen 8 servlets :

+ `HomeServlet`: first page after the login, list of seen movies
+ `LoginServlet`: log in page
+ `LogoutServlet`: used to delete the session and redirect to login
+ `MoviesServlet`: page with a list of all movies of the DB
+ `ProfileServlet`: page to edit and delete account
+ `RegistrationServlet`: page to create a new user

The servlets are use to make the link between the DAO and the HTML page. We have implemented mainly the doGet and doPost methods in all servlet.

To restrict access on pages, we created a `SecurityFilter` that redirect on log in page the user if he isn't log in.