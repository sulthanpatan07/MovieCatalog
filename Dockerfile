# Use official OpenJDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml from backend
COPY backend/.mvn/ .mvn
COPY backend/mvnw .
COPY backend/pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the project from backend
COPY backend/src ./src

# Package the application
RUN ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/*.jar"]
