# Petitions Hub (Spring Boot, Java 17)

A minimal Spring Boot web application to create, view, search, and sign petitions. Data is stored in-memory using Java collections (no external database required), making it perfect for demos, prototypes, and lab exercises.

---

## ✨ Features
- Create a petition (title + description)
- View all petitions
- Search petitions (by title or description) via a search page and a dedicated results page
- View a petition and sign it with **name** and **email**
- Basic email format validation

---

## 🧱 Tech Stack
- **Java 17**
- **Spring Boot 3.3** (Web, Thymeleaf)
- **Thymeleaf** for server-side views

---

## 🚀 Getting Started

### Prerequisites
- Java 17 (e.g., Temurin, OpenJDK)
- Maven 3.8+

### Run locally
```bash
mvn spring-boot:run
```
Then open: **http://localhost:8080**

---

## 🌐 Routes
- `GET /petitions` — list all petitions
- `GET /petitions/new` — petition creation form
- `POST /petitions` — create petition
- `GET /petitions/{id}` — view petition & sign it
- `POST /petitions/{id}/sign` — add a signature
- `GET /search` — search page
- `GET /search/results?q=...` — search results

---

## 🗂️ Project Structure
```
src/main/java/com/example/petitions/
├─ PetitionsApplication.java            # Spring Boot entry point
├─ controller/PetitionController.java   # HTTP endpoints & page routing
├─ model/Petition.java                  # Petition domain model
├─ model/Signature.java                 # Signature domain model
└─ service/PetitionService.java         # In-memory store & logic

src/main/resources/
├─ templates/                           # Thymeleaf views
│  ├─ petitions.html
│  ├─ new-petition.html
│  ├─ petition-detail.html
│  ├─ search.html
│  └─ search-results.html
├─ static/styles.css                    # Minimal styling
└─ application.properties               # App config
```

---

## 🔒 Validation
- Title is required when creating a petition
- Name and Email are required when signing
- Email must match a simple pattern (client-side + server-side check)

---

## 🧪 Testing (optional)
This starter includes `spring-boot-starter-test`. You can add unit tests in `src/test/java` for `PetitionService` and controller endpoints later.

---

## 📦 Packaging
Build a jar:
```bash
mvn clean package
```
Run the jar:
```bash
java -jar target/petitions-app-0.0.1-SNAPSHOT.jar
```

---

## 🛠️ Switching to a Database (optional)
If you need persistence across restarts:
1. Add dependencies for `spring-boot-starter-data-jpa` and a DB (H2/PostgreSQL).
2. Convert `Petition` and `Signature` to `@Entity` classes.
3. Create Spring Data repositories.
4. Replace `PetitionService` map operations with repository calls.

---

## 🧭 License
This project is provided as-is for educational purposes.

---

## 💡 Suggested GitHub Repo Name
**petitions-hub**

Alternative names:
- **spring-petitions**
- **petitions-boot**
- **sign-it**

---

## 🙋 Support / Ideas
Issues and feature requests are welcome. Consider adding:
- Duplicate signature prevention (per petition, per email)
- Pagination for lists and search results
- Export signatures to CSV
- Authentication for admin-only actions
