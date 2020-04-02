FROM adoptopenjdk:11-jdk-openj9-bionic AS build-project
ADD . ./userApi
WORKDIR /userApi
RUN ./mvnw clean install -DskipTests

FROM adoptopenjdk:11-jdk-openj9-bionic
EXPOSE 8080
WORKDIR /app
COPY --from=build-project ./userApi/target/userApi-*.jar ./userApi.jar
ENV ARTIFACT_NAME=userApi.jar
