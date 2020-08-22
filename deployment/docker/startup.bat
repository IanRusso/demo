@ECHO OFF
SETLOCAL

IF NOT "%CD%" == "C:\Users\iruss\git\demo\deployment\docker" (
   ECHO ERROR SCRIPT MUST BE RUN FROM LOCAL DIRECTORY *e.g. from {repoHome}\deployment\docker
   goto EXIT
   )

SET HOME=%CD%
cd ../../
SET PROJECT_ROOT=%CD%


ECHO OPTIONS = %1
IF NOT "%1"=="skipCompile" (
    ECHO Compiling Sources in %CD%
    CALL mvn clean install > mvn-output
    FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"BUILD FAILURE" mvn-output') DO (
        SET MVN_RESULT=%%i)
    IF NOT "%MVN_RESULT%"=="" (
        ECHO ERROR FOUND IN MAVEN BUILD *see mvn-output for details*
        goto EXIT
        )
) ELSE (
    ECHO Found option "skipCompile", skipping compile...
    )

IF "%2"=="skipDockerBuilds" (
    ECHO Found option "skipDockerBuilds", skipping docker build process...
) ELSE (
     ECHO Building REST API Docker Image
     cd %PROJECT_ROOT%/rest
     call docker build -t oecdrest . > docker-output 2>&1
     FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"failed" docker-output') DO (
         SET API_DOCKER_RESULT=%%i)
     IF NOT "%API_DOCKER_RESULT%"=="" (
         ECHO ERROR FOUND IN DOCKER BUILD FOR REST API IMAGE *see rest/docker-output for details*
         goto EXIT
         )

     ECHO Building CRON Job Scheduler Docker Image
     cd %PROJECT_ROOT%/job/scheduler
     call docker build -t oecdcronjob . > docker-output 2>&1
     FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"failed" docker-output') DO (
         SET JOB_DOCKER_RESULT=%%i)
     IF NOT "%JOB_DOCKER_RESULT%"=="" (
         ECHO ERROR FOUND IN DOCKER BUILD FOR CRON JOB IMAGE *see job/docker-output for details*
         goto EXIT
         )

     ECHO Building UI Docker Image
     cd %PROJECT_ROOT%/ui
     call docker build -t oecdui . > docker-output 2>&1
     FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"failed" docker-output') DO (
         SET UI_DOCKER_RESULT=%%i)
     IF NOT "%UI_DOCKER_RESULT%"=="" (
         ECHO ERROR FOUND IN DOCKER BUILD FOR UI IMAGE *see ui/docker-output for details*
         goto EXIT
         )
 )

cd %HOME%

ECHO Starting Up Docker Images...
call docker run --detach --rm -p 6379:6379 --name redis redis
call docker run --detach --rm -p 8080:8080 --name oecdrest oecdrest
call docker run --detach --rm -p 3000:80 --name oecdui oecdui
call docker run --detach --rm --name oecdcronjob ianrusso777/oecdcronjob

:EXIT

ENDLOCAL
