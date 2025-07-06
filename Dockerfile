# Use official OpenJDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy everything
COPY backend/ ./

# Give permission to mvnw (if not already)
RUN chmod +x mvnw

# Build the project inside container
RUN ./mvnw clean package -DskipTests

# Run the app
CMD ["java", "-jar", "target/MovieCatalog-0.0.1-SNAPSHOT.jar"]
