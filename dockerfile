# Definir las versiones de Maven y Java en variables para fácil actualización
ARG MAVEN_VERSION=3.8.4
ARG JAVA_VERSION=17

# Usar una imagen base con Maven para construir el proyecto
FROM maven:${MAVEN_VERSION}-openjdk-${JAVA_VERSION} AS build
WORKDIR /app

# Copiar solo el pom.xml inicialmente y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar los fuentes y empaquetar el jar sin ejecutar tests
COPY src ./src
RUN mvn clean package -DskipTests

# Imagen base para la ejecución
FROM openjdk:17
WORKDIR /app

# Crear un usuario no root y cambiar la propiedad del directorio
RUN useradd -m myuser && chown myuser /app
USER myuser

# Copiar el jar desde el paso de construcción
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

