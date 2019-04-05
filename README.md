# Integrate Spring Cloud Stream and Spring Integration with AxonIQ Framework.


## Step 1

[Install Docker compose](https://docs.docker.com/compose/install/)

## Step 2

>`$ mkdir test-axon-spring-cloud-stream` \
>`$ cd test-axon-spring-cloud-stream` \
>`$ git clone https://github.com/mehdichitforoosh/axon-stream-source.git` \
>`$ git clone https://github.com/mehdichitforoosh/axon-stream-sink.git` \
>`$ git clone https://github.com/mehdichitforoosh/axon-stream-processor.git`

## Step 3

create a **docker-compose.yml** file in **test-axon-spring-cloud-stream** directory.

### docker-compose.yml :

```
version: '3.5'
services:

  rabbitmq:
    image: rabbitmq:3.7-management
    hostname: rabbitmq
    expose:
      - "5672"
    ports:
      - "15672:15672"
    networks:
      services-network-01:
        aliases:
          - rabbitmq

  axon-stream-source:
    build:
      context: ./axon-stream-source
    environment:
      - SPRING_APPLICATION_NAME=axon-stream-source
      - SPRING_PROFILES_ACTIVE=dev
    command: ["mvn", "spring-boot:run"]
    expose:
      - "9000"
    volumes:
      - maven-home:/root/.m2
    depends_on:
      - rabbitmq
    networks:
      services-network-01:
        aliases:
          - axon-stream-source

  axon-stream-sink:
    build:
      context: ./axon-stream-sink
    environment:
      - SPRING_APPLICATION_NAME=axon-stream-sink
      - SPRING_PROFILES_ACTIVE=dev
    command: ["mvn", "spring-boot:run"]
    expose:
      - "9000"
    volumes:
      - maven-home:/root/.m2
    depends_on:
      - rabbitmq
    networks:
      services-network-01:
        aliases:
          - axon-stream-sink

  axon-stream-processor:
    build:
      context: ./axon-stream-processor
    environment:
      - SPRING_APPLICATION_NAME=axon-stream-process
      - SPRING_PROFILES_ACTIVE=dev
    command: ["mvn", "spring-boot:run"]
    expose:
      - "9000"
    volumes:
      - maven-home:/root/.m2
    depends_on:
      - rabbitmq
    networks:
      services-network-01:
        aliases:
          - axon-stream-process

volumes:
  maven-home:

networks:
  services-network-01:
    name: services-network-01
    driver: bridge
```
## Step 4

### Run demo :

`docker-compose up -d --build`

## Step 5

Check console logs for Axon event processors in axon-stream-source,sink and processor microservices.

---
If you had a problem with downloading maven dependencies in docker container:

>`$ docker-compose down -v`

then:

>`$ docker-compose up -d`
