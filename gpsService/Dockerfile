FROM gradle:7.2.0-jdk8 as gradle
COPY ./build.gradle ./build.gradle
COPY ./src ./src
COPY ./libs ./libs
RUN gradle bootJar
COPY build/libs/GpsService-*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
EXPOSE 8081 9001