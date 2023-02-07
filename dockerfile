FROM openjdk:17
EXPOSE 8080
ENV DATA_PATH /data
ADD target/ask-chatgpt-izicap.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]