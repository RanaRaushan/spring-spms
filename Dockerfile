# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:22

# Set the working directory inside the container
WORKDIR /app

# Add the application's jar to the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "application.jar"]