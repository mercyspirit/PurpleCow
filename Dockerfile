FROM openjdk:17-oracle
LABEL maintainer="ziying.qiu@gmail.com"
WORKDIR /app

COPY ./target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 3000