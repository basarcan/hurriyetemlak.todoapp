FROM adoptopenjdk:11-jdk-openj9-bionic AS build-project
ADD . ./user-api
WORKDIR /user-api
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

FROM adoptopenjdk:11-jdk-openj9-bionic
EXPOSE 8080
WORKDIR /app
COPY --from=build-project ./user-api/target/user-api-*.jar ./user-api.jar
ENV ARTIFACT_NAME=user-api.jar
