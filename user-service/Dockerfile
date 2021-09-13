FROM openjdk:11-jdk
VOLUME tmp
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar
CMD ["sh", "-c", "java -jar app.jar"]