# Use official OpenJDK image
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy Maven wrapper files and pom.xml first (to cache dependencies)
COPY backend/mvnw .
COPY backend/.mvn .mvn
COPY backend/pom.xml .

# Download dependencies
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Now copy the rest of the project
COPY backend/src ./src

# Package the application
RUN ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/MovieCatalog-0.0.1-SNAPSHOT.jar"]
