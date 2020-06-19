FROM openjdk:14-jdk-alpine
ADD target/exchange-rates-0.0.1-SNAPSHOT.jar /
EXPOSE 8080
CMD ["java", "-jar", "exchange-rates-0.0.1-SNAPSHOT.jar"]
