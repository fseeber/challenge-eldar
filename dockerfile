FROM maven:3.8.4-openjdk-11 as builder

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Construcción del proyecto
RUN mvn clean package -DskipTests

FROM openjdk:11

# Establecer el directorio de trabajo para la app
WORKDIR /app

# Copiar el JAR desde la etapa de construcción
COPY --from=builder /app/Ejercicio1/demo/target/*.jar app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]