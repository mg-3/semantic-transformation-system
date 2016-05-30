####################################################
# Ubuntu 15.10 with Java 8 installed for running
# Semantic Transformation Services (STS)
# Build image with:
#    docker build -t lilly/sts:v1 .
# Run image with:
#    docker run --name STS -d -p 8090:8090 lilly/sts:v1
#
# Notes:
# In order to configure RefDB connection settings edit the
# config/application.properties file and rebuild/restart the container
####################################################
FROM java:8u72
MAINTAINER Mike Garcia "mike@d2discovery.com"
RUN apt-get update && apt-get install -y net-tools vim less
WORKDIR /home/semantic/sts/
ADD target/*.tar.gz /home/semantic/sts/
ADD config/* /home/semantic/sts/config/
ADD sparql/* /home/semantic/sts/sparql/
EXPOSE 8100 8090 8080
CMD ["java", "-Dserver.port=8090", "-cp", "config", "-jar", "refdb-sts-1.0-SNAPSHOT.jar"]
