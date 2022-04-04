FROM gradle:7.2.0-jdk8 as gradle
COPY ./build.gradle ./build.gradle
COPY ./src ./src
RUN gradle bootJar
COPY build/libs/TourGuideProxy-*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
EXPOSE 8080 9000