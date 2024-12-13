# Fetch base image for OpenJDK 20
FROM openjdk:20

# Define working directory in the container
WORKDIR /app

# Copy the jar file built using Gradle or Maven to the container
COPY ./target/*.jar /app/app.jar

# Expose the port your app runs on
EXPOSE 8080

# Define the entry point of the container
ENTRYPOINT ["java", "-jar", "app.jar"]