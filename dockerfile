# Using Oracle GraalVM for JDK 17
FROM ghcr.io/graalvm/native-image-community:23 AS builder

# Install gzip
RUN microdnf install gzip

# Set the working directory to /home/app
WORKDIR /build

# Copy the source code into the image for building
COPY . /build

# Build
RUN ./mvnw --no-transfer-progress -e package

# Fetch base image for OpenJDK 20
FROM openjdk:20

# Define working directory in the container
WORKDIR /app

# Copy the native executable into the containers
COPY --from=builder /build/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Define the entry point of the container
ENTRYPOINT ["java", "-jar", "app.jar"]