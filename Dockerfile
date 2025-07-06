# Use official OpenJDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY backend/.mvn/ .mvn
COPY backend/mvnw .
COPY backend/pom.xml .

# Download dependencies
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy the rest of the project
COPY backend/src ./src

# Package the application
RUN ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/moviecatalog-0.0.1-SNAPSHOT.jar"]
