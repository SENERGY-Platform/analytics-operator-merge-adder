ENV NAME merge-adder

FROM maven:3.6-openjdk-11-slim as builder
ADD . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install

FROM openjdk:11-jre-slim
COPY --from=builder /usr/src/app/target/operator-${NAME}-jar-with-dependencies.jar /opt/operator-${NAME}-jar-with-dependencies.jar
ADD https://github.com/jmxtrans/jmxtrans-agent/releases/download/jmxtrans-agent-1.2.6/jmxtrans-agent-1.2.6.jar opt/jmxtrans-agent.jar
CMD ["java","-jar","/opt/operator-${NAME}-jar-with-dependencies.jar"]
