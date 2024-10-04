FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

RUN useradd -ms /bin/bash appuser
USER appuser

WORKDIR /app

COPY --from=build /app/target/onlineBlogging-0.0.1-SNAPSHOT.jar /app/onlineBlogging.jar

#ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]

ENV JWT_EXPIRATION=8640000
ENV JWT_SECRET_KEY=b032dc09308d5ff95e4c74cf2750e52f03723cabe090847df04ab22aa35bd57e
ENV DB_PASSWORD=4746

EXPOSE 8088

ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-jar", "/app/onlineBlogging.jar"]

