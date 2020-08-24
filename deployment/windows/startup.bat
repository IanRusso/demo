@ECHO OFF
SETLOCAL

REM If current directory does NOT contain \deployment\windows
IF x%CD:\deployment\windows=%==x%CD% (
   ECHO ERROR SCRIPT MUST BE RUN FROM LOCAL DIRECTORY *e.g. from {repoHome}\deployment\windows
   goto EXIT
   )

SET HOME=%CD%
cd ../../
SET PROJECT_ROOT=%CD%

ECHO OPTIONS = %1 %2

IF "%1" == "skipCompile" (
    ECHO Skipping compile...
    GOTO BUILD
)
IF "%2" == "skipCompile" (
    ECHO Skipping compile...
    GOTO BUILD
)

ECHO Compiling Sources in %CD%
CALL mvn clean install > mvn-output
FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"BUILD FAILURE" mvn-output') DO (
    SET MVN_RESULT=%%i)
IF NOT "%MVN_RESULT%"=="" (
    ECHO ERROR FOUND IN MAVEN BUILD *see mvn-output for details*
    goto EXIT
    )


:BUILD

IF "%1" == "skipDockerBuild" (
    ECHO Skipping docker build...
    GOTO STARTUP
)
IF "%2" == "skipDockerBuild" (
    ECHO Skipping docker build...
    GOTO STARTUP
)
ECHO Building REST API Image
cd %PROJECT_ROOT%/rest
call docker build -t oecdrest . > docker-output 2>&1
FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"Failed" docker-output') DO (
    SET API_DOCKER_RESULT=%%i)
IF NOT "%API_DOCKER_RESULT%"=="" (
    ECHO ERROR FOUND IN DOCKER BUILD FOR REST API IMAGE *see rest/docker-output for details*
    goto EXIT
    )

ECHO Building Cron Job Scheduler Image
cd %PROJECT_ROOT%/job/scheduler
COPY ..\target\playingdocker-jar-with-dependencies.jar playingdocker-jar-with-dependencies.jar
call docker build -t oecdcronjob . > docker-output 2>&1
FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"Failed" docker-output') DO (
    SET JOB_DOCKER_RESULT=%%i)
IF NOT "%JOB_DOCKER_RESULT%"=="" (
    ECHO ERROR FOUND IN DOCKER BUILD FOR CRON JOB IMAGE *see job/docker-output for details*
    goto EXIT
    )

ECHO Building UI Image
cd %PROJECT_ROOT%/ui
call docker build -t oecdui . > docker-output 2>&1
FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"Failed" docker-output') DO (
    SET "UI_DOCKER_RESULT=%%i")
IF NOT "%UI_DOCKER_RESULT%"=="" (
    ECHO ERROR FOUND IN DOCKER BUILD FOR UI IMAGE *see ui/docker-output for details*
    goto EXIT
    )


cd %HOME%

:STARTUP
ECHO Starting Up Docker Images...

REM Get the local system IP address and remove spaces
set ip_address_string="IPv4 Address"
for /f "usebackq tokens=2 delims=:" %%f in (`ipconfig ^| findstr /c:%ip_address_string%`) do set "REDIS_SERVICE_HOST=%%f"
set "REDIS_SERVICE_HOST=%REDIS_SERVICE_HOST: =%"

echo Using Local IP = %REDIS_SERVICE_HOST%

call docker run --detach --rm -p 6379:6379 --name redis redis
call docker run --detach --rm --env REDIS_SERVICE_HOST --env REDIS_SERVICE_PORT=6379 -p 8080:8080 --name oecdrest oecdrest
call docker run --detach --rm --env REDIS_SERVICE_HOST --env REDIS_SERVICE_PORT=6379 -p 3000:80 --name oecdui oecdui
call docker run --detach --rm --env REDIS_SERVICE_HOST --env REDIS_SERVICE_PORT=6379 --name oecdcronjob oecdcronjob

:EXIT

ENDLOCAL
