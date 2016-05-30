#! /bin/bash

docker rm -f $(docker ps -q -a)
docker run --name STS -d -p 8090:8090 lilly/sts:v1
