# Revature Social Network

## Project Description

In this web application every Revature employee can connect to other employees, whether it be an associate, a trainer, or contracted employees. Each person has their own account, that comes with a unique username, Revature employee information. Within this network employees can access locations and information to help them with many different possibilities. Also it is being created to ease the transition as a Revature employee, whether it be as a trainer/associate, contracted employee/contractor, or staff member.

## Technologies Used

* Java
* Angular
* Hibernate
* HTML/CSS
* JavaScript
* Spring
* SQL
* Log4J
* JUnit
* S3

## Features

#### Project Features

* Users can register an acount, login, and logout.
* Users can reset their password, receiving it via email link for validation.
* Users can modify their information, including uploading a profile picture using AWS S3.
* Users can search for other users of the social netowork.
* Users can view their own profile and other people's profile, which contains their posts.
* Users can view a global feed, where all posts are shown.
* Users can "like" posts.
* Users can chat using a live chatroom.

#### To-do list

* Successfully deploy program using EC2.
* Add ability to comment on peoples posts.
* Add ability to add videos to posts and view them.

## Getting Started

1. Clone project using the following link: https://github.com/eaw10-20/SocialMedia.git
2. Open "project-two" folder using IntelliJ and set to run as a Tomcat Server. We used the settings shown below ![1](https://user-images.githubusercontent.com/60686880/116504890-4d332b00-a86e-11eb-94d1-77735c017ced.jpg)
3. Open "front-end" folder using Visual Studio Code. Import node_modules by running "npm install" in the terminal.

## Usage

In order to start the application, first run the Tomcat Server in IntelliJ. If all goes well then after a while the console will tell you that there was a server startup in xxx milliseconds. Next run the Angular side by entering "ng serve -o" into its respective console. Once you do that the below page will pop up.

![2](https://user-images.githubusercontent.com/60686880/116505867-d6e3f800-a870-11eb-9054-377498b8c97c.jpg)

If you have an account you can enter your email and password into their respective fields to login. At the bottom of the form, there is a link that will allow you to register for a new account. Clicking that like will take you to the following page:

![3](https://user-images.githubusercontent.com/60686880/116506222-b4061380-a871-11eb-945b-72b5b9ff6442.jpg)

Here you can register for a new account by entering your information. Once done, you can hit submit to register, or back to go back to the login page.

Once you login, you are greeted by the main page, which shows posts from all users.

![4](https://user-images.githubusercontent.com/60686880/116507034-5b377a80-a873-11eb-9af0-88d7bd34fec3.jpg)

Using the form at the top, you can make a new post, and even add a picture if you like.

![8](https://user-images.githubusercontent.com/60686880/116507131-85893800-a873-11eb-9a2a-0893097b4982.jpg)

You can also like and unlike posts on this page, and search for users using the searchbar in the top left.

Going to your profile page you can see a card with your information on the left side as well as links to other profiles to the right.

![9](https://user-images.githubusercontent.com/60686880/116507332-eadd2900-a873-11eb-8ac3-7f0745945e82.jpg)

If you like you can update your information using the edit profile link, which will take you to the form shown below:

![6](https://user-images.githubusercontent.com/60686880/116507407-0c3e1500-a874-11eb-9f2c-d365642d664d.jpg)

Here you can do a variety of things, including some things that weren't available at registration such as creating a description for your profile or uploading a profile image.

The chat feature is also fully functional and can be accessed from the navbar at the top of the screen in the main and profile pages.

![7](https://user-images.githubusercontent.com/60686880/116507637-7d7dc800-a874-11eb-89c6-1464582088b4.jpg)

The chat is a room with universal access where all users can communicate in real time.

Finally, if you need to recover your password, the app is capable of sending a recovery password to the user's email address.


## Contributors
* Hakeem Clark      - https://github.com/hclark07
* Michael DeGennaro - https://github.com/Mike-DeGennaro
* Corey Schink      - https://github.com/CSchink
* Eric Williams     - https://github.com/eaw10-20
