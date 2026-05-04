# 1. Usamos Java 17 (igual que los otros)
FROM eclipse-temurin:17-jdk

# 2. Copiamos el .jar que se genera en target/
COPY target/hotel-0.0.1-SNAPSHOT.jar hotel-app.jar

# 3. Exponemos el puerto 8082
EXPOSE 8082

# 4. Ejecutamos la aplicación
ENTRYPOINT ["java","-jar","/hotel-app.jar"]
