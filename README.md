# WorkoutTrackerApp

# How to start: 

https://grz55-workout-tracker.herokuapp.com App deployed on Heroku.
Launching may take Heroku up to 15 seconds, please wait patiently.
Pull into IDE, app runs on Tomcat server, H2 in-memory database used,
but can run on MySQL (commands ready in application.properties).
When compiling import.sql file should start to add test user accounts.

# Test credentials: 
Login: Peter123 or Armin123
Password: abcabcabc123

# Description: 

This app lets you organize your workout notes. 
User can manage notes. Access to other users' workouts in restricted.
Every workout include exercises performed. Every exercise performed has its own data (e.g. weight, repetitions).
All units can be easily organized.

# Technologies: 

Java, Spring Boot, Spring Security, Spring Data, Hibernate, MySQL, Thymeleaf

# App features:

- creating account
- loggin in
- logging out
- creating, editing, removing workouts
- creating, editing, removing exercises in workouts
- creating, editing, removing exercise sets (weight x reps) in exercises
- validating form errors
- basic error handling

# For future:

- code can be always cleaner
- workout templates to speed up adding
- selecting to edit by JS checkbox, not by giving ID
