ARG version

FROM maven:3.8.1-jdk-11 AS build
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app
RUN mvn verify --fail-never
COPY src /usr/src/app/src
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests
# Final build
FROM gcr.io/distroless/java:11
WORKDIR /usr/src/app
COPY --from=build "/usr/src/app/target/idea_rating_api-1.0.0.jar" app.jar
EXPOSE 8080
CMD ["app.jar"]
