# Use an official OpenJDK runtime as a parent image
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven POM file
COPY pom.xml .

# # Download dependencies (this will help with Docker layer caching)
RUN mvn dependency:go-offline

# # Copy the source code
# COPY src ./src

# # Package the Spring Boot application
# RUN mvn clean package -DskipTests


# COPY --from=build /app/target/ecom-0.0.1-SNAPSHOT.jar .

# # Expose the port that the Spring Boot application will run on
# EXPOSE 8080

# # Set the default command to run your Spring Boot app
# ENTRYPOINT ["java", "-jar", "/app/ecom-0.0.1-SNAPSHOT.jar"]
