@ECHO OFF
SETLOCAL

ECHO Shutting down...
call docker stop oecdweb

REM If current directory does NOT contain \deployment\windows
IF x%CD:\deployment\windows=%==x%CD% (
   ECHO ERROR SCRIPT MUST BE RUN FROM LOCAL DIRECTORY *e.g. from {repoHome}\deployment\windows
   goto EXIT
   )

SET HOME=%CD%
cd ../../
SET PROJECT_ROOT=%CD%


ECHO OPTIONS = %1 %2

IF "%!"=="skipDockerBuilds" (
    ECHO Found option "skipDockerBuilds", skipping docker build process...
    GOTO STARTUP
)

ECHO Building Web Image
  cd %PROJECT_ROOT%/web
  call docker build -t oecdweb . > docker-output 2>&1
  FOR /f "tokens=*" %%i IN ('FINDSTR /L /S /I /N /C:"Failed" docker-output') DO (
      SET WEB_DOCKER_RESULT=%%i
      )
  IF NOT "%WEB_DOCKER_RESULT%"=="" (
      ECHO ERROR FOUND IN DOCKER BUILD FOR WEB IMAGE *see web/docker-output for details*
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

call docker run --detach --rm --env REDIS_SERVICE_HOST --env REDIS_SERVICE_PORT=6379 -p 8080:8080 --name oecdweb oecdweb

:EXIT
ENDLOCAL