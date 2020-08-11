FROM java:8-jdk-alpine
COPY target/playingdocker-jar-with-dependencies.jar /usr/app/playingdocker.jar
WORKDIR /usr/app/
ENTRYPOINT java -classpath playingdocker.jar com.irusso.playingdocker.Runner