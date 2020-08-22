FROM openjdk:8
WORKDIR .
COPY playingdocker-jar-with-dependencies.jar ./playingdocker.jar
RUN apt-get update && apt-get install -y cron
COPY cronjob /etc/cron.d/
RUN (crontab -l ; echo  "$(cat /etc/cron.d/cronjob)") | crontab -
RUN touch /var/log/cron.log
CMD cron & tail -f /var/log/cron.log