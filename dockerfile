FROM openjdk:17
EXPOSE 8080
ADD target/TokenSystem.jar TokenSystem.jar
ENTRYPOINT [ "java", "-jar", "/TokenSystem.jar" ]