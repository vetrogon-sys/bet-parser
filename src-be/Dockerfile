FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/src-be.jar /usr/local/lib/src-be.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/src-be.jar"]