# Use the official Eclipse Temurin JRE image for Java 25
# Alpine-based images are recommended for their small footprint
FROM eclipse-temurin:25-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy your application's JAR file to the container
# Replace 'target/*.jar' with the actual path to your JAR
COPY target/journalApp-0.0.1-SNAPSHOT.jar app.jar

# Standard port for many Java web applications
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
