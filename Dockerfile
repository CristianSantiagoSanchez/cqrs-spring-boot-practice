FROM openjdk:18-jdk-slim

# Add wait-for-it
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh


COPY "book-swap-boot/target/book-swap-boot-0.0.1-SNAPSHOT.jar" "app.jar"

EXPOSE 8080
EXPOSE 9092

CMD ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "app.jar"]