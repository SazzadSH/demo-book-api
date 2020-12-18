FROM openjdk:8
ADD target/demo-book-API.jar demo-book-API.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo-book-API.jar"]
