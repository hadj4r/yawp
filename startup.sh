#!/usr/bin/env bash

docker compose down
# temporary skipping tests for now
./mvnw clean package -DskipTests=true
docker compose up --force-recreate --build -d