# Teaching-HEIGVD-AMT-2019-Project-One
In this file, we will describe our idea and how we implement it.

## Business domain
For this project, we choose to make an application that allow user to save which movie he seen.

To make that, we have found a open source database with a lot of movie and we have export a part of them and only the two data we care about: the title and the year of the movie.

To create more data, we also used a website to create 3000 users. Each of them is represented by a first name, last name, an email (unique in database), and a password.

[EER Diagram](img/EER_diagram.png)

## Application
First of all, when you arrive on the website, we will ask you an email and a password to log in. If you dont already have an account, you can create one.

After the login page, you will come to your preview page where you will see a list of movies you have seen. This page allow you to remove a movie of your list.

On an other page, you will see a list of all movies in the database. In this page, you will be able to search and add a movie to your personal list of seen movie.

The application will also offer you a page to edit your profile. More precisely, you can change your name and your password. It is also possible to delete your account.