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

### Prerequisites
1. MAC/Linux/**Windows with (WSL)**
2. Docker

### Step 1:

Run the `build.sh` script in order to create docker images of the different microservices i.e `VM`, `THM` and `SM`. The scripts are executed from the command line.

```text
$ ./build.sh
```

If you are running this command for the first time it will take a while so sit back and wait till it completes execution. Subsequent builds will be faster.

### Step 2:

Run the `docker-compose-up.sh` script to bring up the whole system including 3rd party containers such as kafka and zipkin.

```text
$ ./docker-compose-up.sh
```

If you run this script for the first time it will take a while since it will fetch some containers from the public docker registry so sit back and wait for it to finish execution. Subsequent builds will be faster. After successful execution wait for another 3 minutes or less for the system to warm up and bring up all the containers.

### Step 3:

Run the `tests.sh` script in order to test the API endpoints.

```text
$ ./tests.sh
```

If everything is okay the test should be successful.

### Step 3:

After successful execution you can shutdown the system using the `docker-compose-down.sh` script which stops and removes the containers.

```text
$ ./docker-compose-down.sh
```

That is it! Happy Hacking!