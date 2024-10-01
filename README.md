# ExpenseTracker

### A Spring Boot-based application that provides a service of managing user expenses. 
This application incorporates Spring Boot for backend, Spring Data and PostgreSQL for a onetomany relational database, Spring Security for multiple users authorization and content security.

**Here are some screenshots showing how the application works:**

![image](https://github.com/user-attachments/assets/8c8c255f-fa19-47c5-9faf-48841def8d53)


![image](https://github.com/user-attachments/assets/a42709a2-69f1-407a-86c6-fc02d16b29f5)


![image](https://github.com/user-attachments/assets/922f7cd9-971f-4b37-8a8a-7d1d53dcb3a5)


![image](https://github.com/user-attachments/assets/14e1a733-4007-429b-b7b8-8d7e7dae5ec6)


Here's a running example: [Link](http://34.121.220.215:8080/login).


## Database and Docker
This application uses PostgreSQL containerized in a Docker for easy deployment and scalability. The Docker container stores database data in a Docker volume for data persistence. See compose.yaml in root directory for how the Docker is set. The database contains two tables, one for user and one for expenses. Users to expenses is a one to many relationship, meaning that each user holds multiple expenses and they are interconnected. The username and password, encrypted, are also stored in this database for user authentication. The database interacts with the backend of the application through Spring Data JPA.

## User Authentication and Security
Spring Security is used for user authentication, content protection, and cross site request forgery protection. Spring Security locks all other pages other than the login and create account page for users not logged in. Furthermore, Spring Security is also used to determine which user's profile to be rendered on the frontend webpage. Users have no access to any other users' profiles because Spring Security and Thymeleaf templates work together so only the logged in user's profile is populated. Lastly, for every POST, DELETE, PUT, PATCH requests, a csrf token is managed by Spring Security in order to protect user information and protect the server.

## Thymeleaf templates and Bootstrap CSS
Thymeleaf is used to allow users to interact with the backend and also used to render each page with accurate information. Bootstrap CSS is incorporated to make the user-facing interface significantly more appealing.
