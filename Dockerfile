FROM maven:3.3-jdk-8
MAINTAINER mehdichitforoosh@gmail.com
COPY . /usr/src
WORKDIR /usr/src
CMD ["mvn", "spring-boot:run"]