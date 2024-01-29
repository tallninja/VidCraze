#!/usr/bin/env sh

set -e
set -u


printf "####################################### VIDEO MANAGER MICROSERVICE TESTS #########################################################\n"
./clients/gradlew -p ./clients test --tests VmClientTests
printf "\n\n"

printf "####################################### TRENDING HASHTAG MANAGER MICROSERVICE TESTS ##############################################\n"
./clients/gradlew -p ./clients test --tests ThmClientTests
printf "\n\n"

printf "####################################### SUBSCRIPTION MICROSERVICE TESTS ##########################################################\n"
./clients/gradlew -p ./clients test --tests SmClientTests
printf "\n\n"