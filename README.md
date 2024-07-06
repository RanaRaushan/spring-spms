Parking Slot Booking Application

Description

This Spring Boot application provides a real-time parking slot booking system, allowing users to book available slots and updating slot data dynamically using WebSockets.

Getting Started

Dependencies

- Java 17 (or higher)
- Spring Boot 3.0.0 (or higher)

Installation

1. Clone the repository: git clone 
2. Build the application: mvn clean package
3. Run the application

Installation via Docker

1. Install Docker
2. Build the application: mvn clean package
3. Run below docker command
   - ocker-compose build 
   - docker-compose up


Configuration

- Configure database connection properties in application.properties
  - Create <application-local.properties> file and update data as per your requirement (Refer to application-dev.properties)
- For Docker configuration, Create .env file and update application.properties value
  - For Eg: 
    - `some.key=value` in application.properties will be configured as `SOME_KEY=value` in .env file

Features

- Book available parking slots in real-time
- Receive updates on slot availability through WebSockets
- View current slot status (available/occupied)
- JWT login user


Usage


- Start the application
- Access the application at [Start Parking](http://localhost:8080/api/v1/park)
