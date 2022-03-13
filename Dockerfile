FROM maven:3.8.4-openjdk-17-slim as build
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17-oracle
LABEL maintainer="ziying.qiu@gmail.com"
WORKDIR /app

COPY --from=build /usr/src/app/target/demo-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]