FROM openjdk:8-jdk
#MAINTAINER wade<wade@22878536@qq.com>
# Add the service itself
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]