# Use uma imagem base do JDK
FROM openjdk:17-jdk-slim

# Copy your project's JAR file into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Copy start-app.sh script in to container
COPY start-app.sh /start-app.sh

# Expose the port your application will use
EXPOSE 8080

# Give execute permission to the script
RUN chmod +x /start-app.sh

# Command to run the application
ENTRYPOINT ["./start-app.sh"]
