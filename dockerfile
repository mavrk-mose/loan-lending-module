FROM eclipse-temurin:17-jre 

VOLUME /tmp

COPY target/main-0.0.1-SNAPSHOT.jar main.jar

ENTRYPOINT ["java","-jar","/main.jar"]