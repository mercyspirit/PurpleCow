## Environment Setup

### _Basic Setup for Quickstart_

Docker Desktop. Install [here](https://docs.docker.com/get-docker/)

### _Development Setup_

java version "17.0.2". Install [here](https://www.oracle.com/java/technologies/downloads/)

MavenApache Maven 3.8.4. Install [here](https://maven.apache.org/install.html)

MongoDB. Install [here](https://www.mongodb.com/try/download/community?tck=docs_server)

Robo3T. Install [here](https://robomongo.org/)

### _Extra Requirements for Windows Users Because Command Prompt Can't Use Bash_

Cygwin. Install [here](https://www.cygwin.com/)

or

Git bash. Install [here](https://git-scm.com/)

## Quickstart Application

### _Quickstart on port 3000 (api) and port 27017 (mongo db)_

```
./startup.sh
```

### _How to edit the ports served to your machine_

Open _.env_

Change ports below as desired

Before (api on 3000, db on 27017)

```
APP_PORT=3000
DB_PORT=27017
```

After (api on 8080m db on 27000)

```
APP_PORT=8080
DB_PORT=27000
```

## Other commands you can use for local development

### _Below are the two commands that compose startup.sh_

This one builds both the api container and mongo containers based off the docker-compose.yaml file

```
docker-compose build
```

This one eats the _.env_ file and runs the containers

```
docker-compose --env-file .env up
```

### _Local Development_

Create executable

```
mvn clean install
```

Run spring boot project

```
mvn spring-boot:run
```

Run mongodb instance on 27017 (I recommend using Robo3T to do your database management)

```
mongod
```

Open mongo shell to type mongo commands into

```
mongo
```

OR OPEN Robo3T

Robo3T Setup

```
Address: localhost: 27017
```

Insert new db

```
use purplecowdb
```

Insert new documents

```
db.items.insert({id: "1", name: "Purple Project"})
```

## Assumptions, Changes, Or Details To Take Into Account

1. Data was assumed to stay in simple document and id style. Change to a transactional system will require refactoring to SQL solutions

2. No secrets implementation yet

3. As solution gets bigger, the project may be required to be split into separate maven modules which will increase build time and development time to refactor

4. More data that is inserted, the bigger the need is for pagination. If entries come to be in the 100's, we may need to refactor into a pagination model

5. Name query would be faster if the fields were successfully text indexed

6. As database gets bigger, search apis should be turned into streams

7. User authentication needs to be implemented

8. Splunk + Datadog should be configured from the start for easy debugging

9. Id should be tracked and auto incremented every time a new project is added. MongoDB has triggers.

## API List

### _You can load PurpleCowAPI.postman_collection.json into postman for easy access to apis calls_

Get All Items

```
GET /items
```

Get All Items Where Names Have the query as substring

```
GET /items?query=cow
```

Post list of items to either update or add new items

```
POST /items
```

Delete all items in collection

```
DELETE /items
```

Get item by id

```
GET /items/1
```

Put item to save or add new item

```
PUT /items
```

Delete item by id

```
DELETE /items/1
```
