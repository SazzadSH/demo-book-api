FROM openjdk:8
ADD target/demo-book-api.jar demo-book-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo-book-API.jar"]
