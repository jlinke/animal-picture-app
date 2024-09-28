Here’s a **README** file for your application, including instructions to build, start, and use it. This guide will cover how to set up the environment with **Docker Compose** and get the application running, as well as how to access the Spring Boot app via **Nginx** and configure **MySQL**.

---

# Animal Picture App

This project is a simple microservice-based application that fetches and stores random animal pictures (cats, dogs, bears) using Spring Boot, Nginx, and MySQL. The app is containerized using Docker and Docker Compose.

## Table of Contents
1. [Technologies](#technologies)
2. [Prerequisites](#prerequisites)
3. [How to Build](#how-to-build)
4. [How to Run](#how-to-run)
5. [How to Use](#how-to-use)
7. [Endpoints](#endpoints)
8. [Stopping the Application](#stopping-the-application)

---

## Technologies

- **Java 17** (with Spring Boot)
- **Nginx** (as web server)
- **MySQL** (as the database)
- **Docker** (for containerization)
- **Docker Compose** (to manage multi-container application)

---

## Prerequisites

To run this application, make sure you have the following installed on your system:

1. **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
2. **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)

---

## How to Build

1. **Clone the repository**:
    ```bash
    git clone <repository_url>
    cd <repository_directory>
    ```

2. **Build the Spring Boot application**:
   Ensure that the Spring Boot application JAR file is built before running Docker. If you're using Maven, run the following command:

    ```bash
    mvn clean install
    ```
   This will generate a JAR file in the `target/` directory (for example: `animal-app.jar`).

---

## How to Run

1. **Start the application using Docker Compose**:

   Once you have built the Spring Boot application JAR file, start all the services (Spring Boot, Nginx, and MySQL) using Docker Compose:

    ```bash
   cd deployment
    docker-compose up --build
    ```

    - This command builds the Docker images for the Spring Boot app and sets up the necessary containers for Nginx and MySQL.
    - The services are defined in the `docker-compose.yml` file.

2. **Verify the containers are running**:

   After running Docker Compose, you can check the running containers:

    ```bash
    docker ps
    ```

   This should show the `animal-pictures-app`, `nginx-container`, and `mysql-container` running.

---

## How to Use

### 1. Access the Application

Once the containers are running, you can access the application in your browser:

- **Access via Nginx** (default):  
  Open a browser and navigate to:

    ```
    http://localhost/
    ```

  Nginx acts as the Webserver to access the frontend part of the app.

### 2. Endpoints

You can interact with the app using REST APIs to fetch and store images of animals:

#### 2.1 Fetch and Store an Animal Picture
You can fetch and store an animal picture using the following endpoint (Not available via the frontend):
- **Method**: `POST`
- **URL**: `http://localhost/api/animals/fetch?type={animal-type}&amount={number}`

Replace `{animal-type}` with either `cat`, `dog`, or `bear`.

#### Example:

```bash
curl -X POST "http://localhost/api/animals/fetch?type=cat&amount=1"
```

#### 2.2 Get the Last Stored Animal Picture
Retrieve the last stored picture of a specific animal (also available via the frontend):

- **Method**: `GET`
- **URL**: `http://localhost/api/animals/last/{animal-type}`

Replace `{animal-type}` with `cat`, `dog`, or `bear` to retrieve the last stored image.

#### Example:

```bash
curl -X GET "http://localhost/api/animals/last/cat"
```

The image will be returned in binary format, and it can be used in a frontend application directly.

---

## Stopping the Application

When you're finished, you can stop the application by running the following command:

```bash
docker-compose down
```

This will stop and remove all the containers associated with the application, including Nginx, MySQL, and the Spring Boot app.

---

## Additional Information

### Persistence

- **MySQL Data Persistence**: The MySQL database is mounted to a Docker volume (`mysql-data`), ensuring data is persistent between container restarts.
