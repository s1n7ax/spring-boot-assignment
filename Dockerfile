FROM ubuntu:oracular AS build

RUN apt-get update -y \
  && apt-get install -y openjdk-17-jdk-headless

WORKDIR /app

COPY ./build.gradle ./gradlew ./settings.gradle ./
COPY ./gradle/ ./gradle
RUN ./gradlew dependencies

COPY ./src ./src
RUN ./gradlew build


FROM ubuntu:oracular

ARG APP_VERSION=0.0.1-SNAPSHOT

RUN apt-get update -y \
  && apt-get install -y openjdk-17-jre-headless

WORKDIR /app

COPY --from=build /app/build/libs/demo-${APP_VERSION}.jar ./app.jar

ENTRYPOINT ["java","-jar","./app.jar"]
