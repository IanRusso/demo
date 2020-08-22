@ECHO OFF

ECHO Shutting down...
call docker stop oecdui
call docker stop oecdrest
call docker stop redis
call docker stop oecdcronjob