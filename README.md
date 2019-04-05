# axon-stream-source

### docker-compose.yml :

```version: '3.5'
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
    command: ["dockerize","-wait","tcp://rabbitmq:5672","-timeout=2m", "mvn", "spring-boot:run"]
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
    command: ["dockerize","-wait","tcp://rabbitmq:5672","-timeout=2m", "mvn", "spring-boot:run"]
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
    command: ["dockerize","-wait","tcp://rabbitmq:5672","-timeout=2m", "mvn", "spring-boot:run"]
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
    
# Run demo :

`docker-compose up -d --build`
