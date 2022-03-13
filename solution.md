Command to build api docker container
docker build -t purplecowapi .

Command for running mongo db

docker run -d --name purplecowmongo -p 27017:27017 mongo

Command for running api

docker run -dp 3000:3000 purplecowapi

docker-compose build

docker-compose --env-file .env up
