# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and mvnw files to the working directory
COPY pom.xml ./pom.xml
COPY mvnw ./mvnw
COPY .mvn/ ./.mvn/

# Make the mvnw file executable
RUN chmod +x ./mvnw

# Copy the source code into the container
COPY src ./src

# Install dependencies and package the app (use Maven)
RUN ./mvnw clean package -DskipTests

# Second stage: minimal runtime image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged .jar file from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the port the application will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
