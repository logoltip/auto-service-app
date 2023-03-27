# 🚘 Auto-Service 🚘

### 📑 Description:

This is a Java project that provides RESTful APIs to manage an auto service. The APIs allow you to create, update, and retrieve data related to mechanics, cars, car owners, orders, services, and products.

### 💻 Technologies used:

Java 17, Spring Boot, PostgreSQL, Maven, Lombok, Swagger.

### 🛠️️ Project structure:

- Controllers layer - http controllers.
- Service layer - all business logic (and connecting repository with controllers).
- Model and DTO layer - models and DTOs (for http requests and responses).
- Repository layer - work with database.

### ⚙️ API Endpoints:

#### MechanicController:
 - POST /mechanics: Create a mechanic. 
 - PUT /mechanic/{id}: Update the details of a mechanic. 
 - GET /mechanics/{id}/works: Retrieve all orders associated with a mechanic. 
 - GET /mechanics/{id}/salary: Calculate and return the salary of a mechanic. 
#### CarController:
 - POST /cars: Create a car. 
 - PUT /cars/{id}: Update the details of a car. 
#### OwnerController:
 - POST /owners: Create a car owner. 
 - PUT /owners/{id}: Update the details of a car owner. 
 - GET /owners/{id}/orders: Retrieve all orders associated with a car owner. 
#### OrderController:
 - POST /orders: Create an order. 
 - POST /orders/{id}/products: Add a product to an order. 
 - POST /orders/{id}/works: Add a work to an order.
 - PUT /orders/{id}: Update the details of an order. 
 - PUT /orders/{id}/status: Update the status of an order. 
 - GET /orders/{id}/cost: Calculate the cost of an order. 
#### WorkController:
 - POST /works: Create a work. 
 - PUT /works/{id}: Update the details of a work. 
 - PUT /works/{id}/status: Update the status of a work.
#### ProductController:
 - POST /products: Create a product. 
 - PUT /products/{id}: Update the details of a product.

### 📱 How to use app:

To run this project, you will need to have Java 17, PostgreSQL, and Maven installed on your machine:

1. Clone this project on GitHub;
2. Open the project in your preferred IDE (such as IntelliJ IDEA or Eclipse);
3. Set your database credentials in the ```application.properties``` file located in the `src/main/resources` directory;
4. Build the project using the following command: ```mvn clean install```;
5. Run the project using the following command: ```mvn spring-boot:run```;
6. Navigate to `http://localhost:8080/swagger-ui.html` in your web browser to access the Swagger UI and test the APIs;
7. Let the magic begin 🪄

### 📝 Notes:
 - The discount on products for a car owner is calculated as 1% per order. 
 - The discount on works for a car owner is calculated as 2% per order. 
 - The salary of a mechanic is calculated as 40% of the cost of the work he worked on. 
 - When calculating and paying the salary of a mechanic, the status of the work is updated to "PAID OUT". 
 - When changing the status of an order to "COMPLETE SUCCESSFULLY" or "COMPLETE UNSUCCESSFULLY", the completion date is set to the current date. 
 - The diagnostic service is free if the car owner agrees to the repair, and costs 500 UAH if the car owner refuses.
