FROM java:8-jdk-alpine
COPY target/playingdocker-jar-with-dependencies.jar /usr/app/playingdocker.jar
WORKDIR /usr/app/
ENV REDIS_HOST=172.29.186.113 REDIS_PORT=6379
ENTRYPOINT java -classpath playingdocker.jar com.irusso.playingdocker.Runner $REDIS_HOST $REDIS_PORT