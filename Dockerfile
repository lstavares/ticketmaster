# Use uma imagem base do JDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR do seu projeto para o contêiner
COPY target/ticketmaster-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que seu aplicativo vai usar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
