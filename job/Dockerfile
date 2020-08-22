FROM openjdk:8
WORKDIR /usr/app/
COPY target/playingdocker-jar-with-dependencies.jar ./playingdocker.jar
ENTRYPOINT java -classpath playingdocker.jar com.irusso.playingdocker.Runner