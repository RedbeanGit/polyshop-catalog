# Catalog

## Description

The Catalog microservice is responsible for maintaining a list of products, each with a name, description, and price. It was developed as part of a course project on creating microservices using RabbitMQ for communication between services.

## Technologies Used

- Programming Language: Java with Spring Boot
- Database: MongoDB
- Message Queue: RabbitMQ
- Containerization: Docker

## Installation and Configuration

1. Clone the GitHub repository:

```bash
git clone git@github.com:RedbeanGit/polyshop-catalog.git
```

2. Install Docker and Docker Compose on your machine if you haven't already. You can follow the installation instructions on Docker's official website: https://docs.docker.com/get-docker/ and https://docs.docker.com/compose/install/.

3. Navigate to the Catalog microservice directory:

```bash
cd polyshop-catalog
```

4. Launch Docker Compose to start the necessary containers:

```bash
docker-compose up -d
```

**Now you can choose to run the Catalog service inside a docker container or directly on your host.**

### Running with docker

5. Build the Docker image for the microservice using the provided Dockerfile:

```bash
docker build -t polyshop-catalog .
```

6. Run the container from the image you have just builded:

```bash
docker run --name polyshop_catalog polyshop-catalog
```

### Running on host

5. Start Spring Boot application:

```bash
./mvnw spring-boot:run
```

## API

List of routes/API endpoints available for this microservice:

- **GET** /products : Retrieves the list of all products in the catalog.
- **GET** /products/{productId} : Retrieves a product with the specified ID.
- **POST** /products : Creates a new product.

## Message Queue

The Catalog microservice listens for all messages coming from the Order microservice (order-exchange) using the routing key _order.checked_ in order to update its own list of products.
