# Java Spring Bootcamp 🚀

## Overview
This repository contains the code I built during Nélio Alves' one-week Java Spring Bootcamp. This project offers hands-on practice with modern Spring Boot, RESTful APIs, and basic persistence. Designed for learning, the codebase is intentionally simple, featuring a clean Maven build, a modern project layout, and essential Spring starters (Web + Data JPA). This allows you to grasp Spring fundamentals without immediate complexity.


## Tech Stack
* **Runtime:** Java 17
* **Framework:** Spring Boot
* **Build Tool:** Maven
* **Database:**
    * H2 (for development and testing)
    * PostgreSQL (for production and API demonstration)

**Note:** Postman was used to verify API functionality.


## Example Endpoints

| Method | Endpoint                    | Purpose                                           |
| ------ | --------------------------- | ------------------------------------------------- |
| GET    | `/games/{id}`               | Retrieve **details for a specific game by ID**.   |
| GET    | `/lists`                    | **List all available game lists**.                |
| GET    | `/lists/{list}/games`       | **Retrieve all games within a specific list**.    |
| POST   | `/lists/{list}/replacement` | **Reorder games within a list**. Expects a JSON body with `sourceIndex` and `destinationIndex`. Example body: `{"sourceIndex": 3, "destinationIndex": 1}` |

## Getting Started
### Prerequisites
* **JDK 17** (check with `java --version`)
* **Git**
* **Docker** (optional, for an easy local PostgreSQL instance)
### 1. Clone the repo
```bash
git clone https://github.com/BudaBecker/java-spring-bootcamp.git
cd java-spring-bootcamp
mvnw spring-boot:run
```