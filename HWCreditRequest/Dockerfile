FROM openjdk:23

EXPOSE 5500

ADD target/HWCreditRequest-0.0.1-SNAPSHOT.jar credit_app.jar

ENTRYPOINT ["java","-jar","credit_app.jar"]