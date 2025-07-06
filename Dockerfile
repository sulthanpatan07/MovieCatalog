# Use official OpenJDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY backend/.mvn/ .mvn
COPY backend/mvnw .
COPY backend/pom.xml .

# Pre-fetch dependencies (optional but useful)
RUN ./mvnw dependency:go-offline

# Copy all source files
COPY backend/src ./src

# Build the application (creates the JAR file in /app/target/)
RUN ./mvnw clean package -DskipTests

# Run the JAR
CMD ["java", "-jar", "target/MovieCatalog-0.0.1-SNAPSHOT.jar"]
