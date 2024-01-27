# VidCraze

Vidcraze is a video sharing social platform

## Description

- The system comprises of 3 microservices:
  - *Video Microservice (VM)* - responsible for posting, viewing, liking and disliking videos
  - Trending Hashtag Microservice (THM) - finding out the current top 10 liked hashtags
  - Subscription Microservive (SM) - allow users to subscribe and
    unsubscribe from hashtag.

## Technologies Used

- Programming Languages
  1. Java 17
- Frameworks
  1. Micronaut
- Databases
  1. Postgresql
- Event Bus
  1. Kafka
  2. Kafka Streams
- Testing
  1. JUnit5
  2. Mockito
- Deployment
  1. Docker
  2. Docker Compose
- IDE
  1. IntelliJ

## Running The Application

Using a command line interface navigate to the different microservices i.e `vm`, `thm` and `sm`.
Inside those folders run the following command:

**MAC/LINUX**

```text
./gradlew dockerBuild
```

**WINDOWS**

```text
gradlew.bat dockerBuild
```

The commands above will create docker containers of the individual microservices.
After creating the containers run the whole system using the command below

```text
docker compose up
```

OR

```text
docker compose up -d
```

The latter command runs docker compose in detached mode.