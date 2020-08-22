@echo off
ECHO Running Maven clean+install...
cd ../
call mvn clean install > mvn-output 2>&1
FINDSTR /L /S /I /N /C:"Failed to execute goal" mvn-output
COPY target\playingdocker-jar-with-dependencies.jar scheduler\playingdocker-jar-with-dependencies.jar
cd scheduler
ECHO Running Docker build in %CD%...
call docker build -t ianrusso777/oecdcronjob . > docker-output 2>&1
ECHO Running Docker container: oecdcronjob...
call docker run --rm -ti --name oecdcronjob ianrusso777/oecdcronjob