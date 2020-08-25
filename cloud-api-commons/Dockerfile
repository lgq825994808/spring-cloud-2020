FROM openjdk:8-jdk
#MAINTAINER wade<wade@22878536@qq.com>
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/cloud-api-commons-1.0.jar
ENTRYPOINT ["java", "-Duser.timezone=GMT+08", "-Djava.library.path=/usr/local/openjdk-8/lib/", "-Xms1024m", "-Xmx1024m", "-XX:MaxNewSize=1024m", "-XX:PermSize=256m", "-XX:MaxPermSize=512m","-jar", "/usr/share/cloud-api-commons-1.0.jar"]