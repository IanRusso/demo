kubectl delete service/redis
kubectl delete service/oecdrest-service
kubectl delete service/ui-service

kubectl delete deployment.apps/redis
kubectl delete deployment.apps/oecdrest
kubectl delete deployment.apps/ui

kubectl delete cronjob.batch/oecdjob
