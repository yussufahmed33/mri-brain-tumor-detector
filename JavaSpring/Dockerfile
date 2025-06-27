FROM openjdk:17
COPY target/dockerjava.jar dockerjava.jar
EXPOSE 6060
ENTRYPOINT ["java", "-jar", "dockerjava.jar"]