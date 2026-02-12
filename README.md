# HireForge — Backend API

**Live API Endpoint:** `https://hireforge-backend.onrender.com`  
---

## 🚀 Project Overview
HireForge Backend is a robust RESTful API built with **Java** and **Spring Boot**. It serves as the core engine for the HireForge job-tracking ecosystem, handling secure user authentication (JWT), data persistence, and business logic for managing job applications.

The service is designed with a stateless architecture, making it highly scalable and ready for cloud deployment.

## 🛠 Tech Stack
* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Security:** Spring Security & JSON Web Token (JWT)
* **Database:** PostgreSQL (Hosted on Neon.tech)
* **ORM:** Spring Data JPA / Hibernate
* **Build Tool:** Maven

## ✨ Key Features
* **Stateless Authentication:** Secure login and registration using JWT.
* **CRUD Operations:** Full management of job applications (Create, Read, Update, Delete).
* **Resource Protection:** Only authenticated users can access their specific application data.
* **Automated Schema Management:** Database tables are automatically kept in sync using Hibernate DDL-Auto.
* **CORS Configuration:** Pre-configured to securely communicate with the Vercel-hosted frontend.

## 🔑 Environment Variables
To run this project, you must set the following variables in your environment (Render Dashboard or `.env` file):

| Variable | Description |
| :--- | :--- |
| `DATABASE_URL` | PostgreSQL connection string from Neon.tech |
| `DB_USERNAME` | Your database username |
| `DB_PASSWORD` | Your database password |
| `JWT_SECRET` | A long, secure string for signing tokens |

---

## 📡 API Endpoints

### Authentication
* `POST /api/auth/register` - Create a new user account.
* `POST /api/auth/login` - Authenticate and receive a JWT.

### Applications (Requires JWT Header)
* `GET /api/applications` - Get all applications for current user.
* `POST /api/applications` - Add a new job application.
* `PUT /api/applications/{id}` - Update an existing application.
* `DELETE /api/applications/{id}` - Remove an application.

---

## 💻 Local Development

1. **Clone the repo:**
   ```bash
   git clone https://github.com/your-username/hireforge-backend.git
   ```

2. **Configure Database:**
   Ensure PostgreSQL is running and update `application.yml`.

3. **Build & Run:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

---

## 🌐 Deployment (Render)

**Build Command:**
```bash
mvn clean install -DskipTests
```

**Start Command:**
```bash
java -jar target/*.jar
```

**Environment:** Add all keys from the Environment Variables section to the Render "Environment" tab.