#Here user table

#Note: In document creating user table not mentiond

# Blog Application with User Roles and Authentication 


This project is a web application for managing blog posts with user roles and authentication. It includes functionalities for user registration, login, admin dashboard, and viewer interface.

## Database Schema

### Posts Table

```sql
CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    userId INT,
    edit BOOLEAN DEFAULT false,
    FOREIGN KEY (userId) REFERENCES user(id)
);
```

### Media Table

```sql
CREATE TABLE media (
    id INT AUTO_INCREMENT PRIMARY KEY,
    postId INT,
    file_data LONGBLOB,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (postId) REFERENCES posts(id) ON DELETE CASCADE
);
```

### User 

```sql
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Viewer') NOT NULL
);
```



## Project Phases

### Phase 1: User Registration and Login (Frontend & Backend)

#### User Registration

- Create a user registration form capturing name, email, password, and role (Admin/Viewer).
- Implement registration logic in a Servlet to validate data, store user details in MySQL, and assign roles.

#### User Login

- Design a login page with fields for username/email and password.
- Implement login logic in a Servlet to authenticate users against the database and establish sessions for role identification.

### Phase 2: Admin Dashboard (Frontend & Backend)

#### Admin Dashboard

- Develop a dashboard accessible to Admin users after successful login.
- Implement functionalities for Admin to:
  - Create new blog posts with title, content, and optional image upload.
  - Update existing blog posts including title, content, and image.
  - Delete blog posts.

### Phase 3: User Interface for Viewers (Frontend)

#### Viewer Interface

- Implement UI for viewers to:
  - Search blog posts by title or date(working on that).
  - View individual blog posts in detail, opening them in a new tab.

## Video Link

[Watch the project video demonstration](https://1drv.ms/v/s!Avd4fnSa-w6DgW1mPQPc4SLCoWBM)

## Installation

1. Clone the repository:

   ```bash
   $ gitclone https://github.com/Purushotham-Palla/blogApplication1.git
  
   ```

2. Set up the MySQL database:
   - Create a database and run the provided SQL scripts (`posts.sql`, `media.sql`) to create the necessary tables.

3. Import the project into your IDE as a Dynamic Web Project.

4. Deploy the project on a servlet container like Apache Tomcat.

5. Access the application through the provided URL (e.g., `http://localhost:8080/blogapp`).

## Tech Stack

- Backend: Java Servlets
- Frontend: JSP (JavaServer Pages)
- Database: MySQL

## Contact

For any questions or feedback, feel free to reach out:


- **Author**: Purushotham-Palla
- **GitHub**: [Purushotham-Palla](https://github.com/Purushotham-Palla/)
- **Email**: ppurushotham2001@gmail.com


