FROM maven:3.6-openjdk-11-slim as builder
ADD . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install

FROM openjdk:11-jre-slim
COPY --from=builder /usr/src/app/target/operator-merge-adder-jar-with-dependencies.jar /opt/operator-merge-adder-jar-with-dependencies.jar
CMD ["java","-jar","/opt/operator-merge-adder-jar-with-dependencies.jar"]
