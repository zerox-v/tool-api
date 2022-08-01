FROM openjdk:16

LABEL maintainer="taro@ionic.fun"

VOLUME ['/tmp']

EXPOSE 9092

ADD ./ToolApi-1.0.0.jar /home/docker/app.jar
# 运行jar包
ENTRYPOINT ["nohup","java","-jar","/home/docker/app.jar","&"]