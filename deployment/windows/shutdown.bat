@ECHO OFF

ECHO Shutting down...
call docker stop oecdweb
call docker stop oecdcronjob
call docker stop redis