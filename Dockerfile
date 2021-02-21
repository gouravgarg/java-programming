#FROM docker pull openjdk:15.0.1
FROM maven:3.6.3-openjdk-15
# copy the source tree and the pom.xml to our new container
COPY ./ ./
# package our application code
RUN mvn clean package                   #shell form
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]   #execution form

# the second stage of our build will use open jdk 8 on alpine 3.9
FROM openjdk:15.0.1

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD target/*.jar /app.jar

# set the startup command to execute the jar
ENTRYPOINT ["java","-jar","/app.jar"]