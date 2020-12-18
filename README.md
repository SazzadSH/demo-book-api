## Requirement

 - JDK 1.8
 - Maven 3.6.3
 - Docker

**To build with Maven:**

 ~~~
 mvn package
 ~~~

**To run the application:**
 ~~~
 java -jar target/demo-book-api.jar
 ~~~

**To build Docker Image:**
 ~~~
 sudo docker build -f Dockerfile -t demo-book-api .
 ~~~

**To build Multi Stage Docker:**
 ~~~
 sudo docker build -f DockerfileMultiStage -t demo-book-api .
 ~~~

**Run Docker Image:**
Note: PORT 8080 must be free
 ~~~
 sudo docker run -p 8080:8080 demo-book-api
 ~~~

**To run the Swagger UI:**
~~~
http://localhost:8080/swagger-ui.html
~~~
