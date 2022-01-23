FROM maven:3.8.4-jdk-11 as maven

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn package -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /reading-is-good

COPY --from=maven target/reading-is-good-1.0.jar ./

CMD ["java", "-jar", "./reading-is-good-1.0.jar"]