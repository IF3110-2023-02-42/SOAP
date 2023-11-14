# Use Corretto 1.8 as the base image
FROM amazoncorretto:8

WORKDIR /app

# Copy the packaged JAR file into the image
COPY target/SOAP-0.0-jar-with-dependencies.jar /app/SOAP-0.0-jar-with-dependencies.jar
COPY .env /app/.env

# Expose the port your app runs on
EXPOSE 6060

# Set the command to run your app
CMD ["java", "-jar", "SOAP-0.0-jar-with-dependencies.jar"]
