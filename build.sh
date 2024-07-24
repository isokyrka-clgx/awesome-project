#!/bin/bash

# Stop on first error
set -e

COMMIT_HASH=$(git rev-parse --short HEAD)

BRANCH_NAME=$(git rev-parse --abbrev-ref HEAD | sed 's/[^a-zA-Z0-9]/-/g')

RANDOM_STR=$(cat /dev/urandom | base64 | tr -dc 'a-zA-Z0-9' | fold -w 8 | head -n 1)

IMAGE_TAG="${COMMIT_HASH}_${BRANCH_NAME}_${RANDOM_STR}"

IMAGE_NAME="isokyrka/awesome-project"

mvn clean install

# Build the Docker image
docker build -t $IMAGE_NAME:$IMAGE_TAG .

# Push the Docker image to Docker Hub
docker push $IMAGE_NAME:$IMAGE_TAG

echo "Docker image $IMAGE_NAME:$IMAGE_TAG built and pushed to Docker Hub"

# docker run -p 8083:8083 isokyrka/awesome-project:latest