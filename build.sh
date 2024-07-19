#!/bin/bash

# Stop on first error
set -e

IMAGE_NAME="isokyrka/awesome-project"
TAG="latest"

mvn clean install

# Build the Docker image
docker build -t $IMAGE_NAME:$TAG .

# Push the Docker image to Docker Hub
docker push $IMAGE_NAME:$TAG

echo "Docker image $IMAGE_NAME:$TAG built and pushed to Docker Hub"

# docker run -p 8083:8083 isokyrka/awesome-project:latest