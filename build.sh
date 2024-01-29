#!/usr/bin/env sh

set -e
set -u

./vm/gradlew -p ./vm dockerBuild
echo "Created docker image of video manager microservice."

./thm/gradlew -p ./thm dockerBuild
echo "Created docker image of trending hashtag manager microservice."

./sm/gradlew -p ./sm dockerBuild
echo "Created docker image of subscription manager microservice."