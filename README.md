# Crop Monitoring management system

## Introduction
This project is a crop monitoring system built using the **Spring Framework**. It is designed to manage the operations of a monitoring system, handling key functionalities like staff management, crop tracking, and vehicle and equipment management. The system is built on a layered architecture and uses logging for debugging and monitoring. With **JPA** and **Hibernate**, it ensures efficient database interactions and seamless **Object Relational Mapping** (ORM).

The system coheres to best practices, with clear separation between presentation, business logic, and data layers.

## Key Features
- **Vehicle Management**: CRUD operations to manage vehicle.
- **Monitoring log**: Functionality to create and manage monitoring logs.
- **staff Management**: Add and manage customer profiles.
- **Logging**: Integrated logging for event tracking and debugging.
- **Layered Architecture**: Separates presentation, business logic, and data layers.
- **REST API**: Provides API endpoints with JSON responses.
- **Exception Handling**: Custom exception handling and input validation using Spring Validator.

## Technologies Used
- **Backend**: Spring Framework, Spring Data JPA, Hibernate
- **Database**: MySQL
- **Communication**: AJAX
- **Logging**: Spring Loggers for application flow monitoring
- **ORM**: JPA with Hibernate for database interaction
- **Spring Data JPA**: For data repository layer
- **Lombok**: Reduces boilerplate code
- **JWT**: for security configurations

## System Architecture
The project is structured into the following layers:

- **Controller Layer**: Manages HTTP requests and responses.
- **Service Layer**: Contains the business logic and service implementations.
- **DAO Layer**: Handles data persistence and database interaction.
- **Entity Layer**: Maps to database tables via Hibernate.
- **DTO Layer**: Facilitates data transfer between layers.
- **Custom Exception Layer**: Handles specific exceptions and errors.

## API Endpoints

### Staff Endpoints
- `GET /allStaff`: Retrieve all staff or get a staff by ID.
- `POST /staff`: Create a new staff.
- `PUT /staff/{staffId}`: Update an existing staff.
- `DELETE /staff/{staffId}`: Delete a staff by ID.

### vehicle Endpoints
- `GET /vehicle`: Retrieve all vehicles or get a vehicle by code.
- `POST /vehicle`: add a new vehicle.
- `PUT /vehicle/{vehicleCode}`: Update an existing vehicle.
- `DELETE /vehicle/{vehicleCode}`: Delete vehicle by code.

### Equipment Endpoints
- `GET /equipment`: Retrieve all equipments or get a equipment by code.
- `POST /equipment`: add a new equipment.
- `PUT /equipment/{equipmentCode}`: Update an existing equipment.
- `DELETE /equipment/{equipmentCode}`: Delete a equipment by code.

### User Endpoints
- `GET /user`: Retrieve users.
- `POST /user`: add a new user.
- `PUT /user/{email}`: Update an existing user.
- `DELETE /user/{email}`: Delete an user by email.
- 
### Crop Endpoints
- `GET /crops`: Retrieve crops.
- `POST /crops`: add new crops.
- `PUT /crops/{cropCode}`: Update an existing details of crops.
- `DELETE /crops/{cropCode}`: Delete an crop by id.

### Field Endpoints
- `GET /fields`: Retrieve fields.
- `POST /fields`: add new field detail.
- `PUT /fields/{fieldCode}`: Update the existing details of field.
- `DELETE /fields/{fieldCode}`: Delete an field by id.

### MonitoringLog Endpoints
- `GET /logs`: Retrieve logs.
- `POST /logs`: add new log detail.
- `PUT /logs/{logCode}`: Update the existing details of logs.
- `DELETE /logs/{logCode}`: Delete an log by logCode.

## API Documentation
For detailed API documentation, including example requests and responses, visit: **[API Documentation](https://documenter.getpostman.com/view/35948713/2sAYBYepwh)**.

## Frontend for this crop monitoring system
**[FrontEnd](https://github.com/duranginethmini/cropMonitor-Frondend)

## 🛡️ License
This project is licensed under the MIT License. See the [LICENSE] file for details.
