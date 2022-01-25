# dynaccurate-solution 

## Description 

The description of the project is available [here]()

The project is a web service that it manage users (CRUD function) and it consumes events from a queue RabbitMQ. The second part of the project, which produces events to a queue RabbitMQ, is [here](https://github.com/afonsoneto121/dynaccurate-worker). 



## Stack

* Java 11
  * Spring Cloud
  * Spring Data
* MongoDB
* Git
* Docker



## Documentation

Documentation is available [here]()

## Usage

To use this service is required docker and docker-compose are installed locally and Postman(optional) for the test. 

#### Step 1

```sh
git clone https://github.com/afonsoneto121/dynaccurate
```

#### Step 2

```sh
cd run && \
docker-compose up --build -d && \
cd ..
```

\***NOTE:** *This command can require root privileges, and it can take some time.*

This command initializes all infrastructure.

#### Step 3

```sh
docker ps
```

The outbound of this command should be: 

```
IMAGE
afonsobsneto/dynaccurate-worker
mongo-express
afonsobsneto/dynaccurate 
mongo
```


