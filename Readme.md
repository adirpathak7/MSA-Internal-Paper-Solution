# 🛠️ MSA Docker Deployment for Garments Application

This repository contains instructions to set up a **Microservices Architecture (MSA)** for the Garments application (ICT-3 Internal practical question paper solution) using **Docker**.  
It includes a **MySQL database**, **Resource application**, and **Client application**.

---

## 📑 Table of Contents

- Prerequisites
- Step 1: Create Docker Network
- Step 2: MySQL Container Setup
- Step 3: Access and Configure MySQL
- Step 4: Export Database via phpMyAdmin
- Step 5: Resource Application Dockerfile Setup
- Step 6: Build and Deploy Resource Application
- Step 7: Client Application Dockerfile Setup
- Step 8: Build and Deploy Client Application
- Step 9: Test Applications
- ⚠️ Important Notes

---

## 🛠️ Prerequisites

Before starting, ensure you have:

- Docker installed on your machine  
- Payara Micro Docker image (`payara/micro:6.2024.5-jdk17`)  
- Garments application artifacts (`GarmentsApp.war` and `GarmentsClientApp.war`)  
- `domain.xml` and `mysql.jar` for the Resource application  

> 💡 Tip: Make sure Docker is running and ports `8085`, `8086`, and `3305` are free.

---

## Step 1: Create Docker Network
Create a **Docker network** so that all containers can communicate with each other:

```bash
docker network create garmentsNet
```

## Step 2: MySQL Container Setup

Run the **MySQL container** with **persistent storage** and **custom port mapping**:

```bash
docker run -d --name msamysql --network garmentsNet -e MYSQL_ROOT_PASSWORD=adirp7 -v msaData:/var/lib/mysql -p 3305:3306 mysql:8.0
```

**Explanation:**

- `-d` → Run container in detached mode (background)
- `--name msamysql` → Assign a name to the container
- `--network garmentsNet` → Connect container to the Docker network
- `-e MYSQL_ROOT_PASSWORD=adirp7` → Set MySQL root password
- `-v msaData:/var/lib/mysql` → Create persistent volume for MySQL data
- `-p 3305:3306` → Map host port 3305 to container port 3306
- `mysql:8.0` → Use the official MySQL 8.0 Docker image

## Step 3: Access and Configure MySQL

Access the **MySQL container shell:**

```
docker exec -it msamysql mysql -uroot -padirp7
```
Check existing databases:
```
show databases;
```
Grant access to root if needed:
```
alter user 'root'@'%' identified by 'adirp7'
```

## Step 4: Export Database via phpMyAdmin

**Export your local database and import into Docker MySQL:**

1. Open phpMyAdmin, select your database → Export → Custom → Select All → View Output as Text → Export.
2. Copy SQL script and paste it inside **MySQL container shell**:

## Step 5: Resource Application Dockerfile Setup

**Create `Dockerfile` for the Resource application:**
```
FROM payara/micro:6.2024.5-jdk17

ENV DEPLOY_DIR=/opt/payara/deployments

RUN mkdir ${PAYARA_HOME}/config

COPY --chown=payara:payara domain.xml ${PAYARA_HOME}/config/
COPY --chown=payara:payara mysql.jar ${PAYARA_HOME}/config/config

USER payara
WORKDIR ${PAYARA_HOME}

COPY artifact/GarmentsApp.war ${DEPLOY_DIR}/

EXPOSE 8080

CMD ["--addlibs","/opt/payara/config/mysql.jar",
     "--deploymentDir","/opt/payara/deployments",
     "--rootDir","/opt/payara/config",
     "--domainConfig","/opt/payara/config/domain.xml"]
```

**Explanation:**

- `FROM payara/micro:6.2024.5-jdk17` → Use Payara Micro base image
- `COPY artifact/GarmentsApp.war ${DEPLOY_DIR}/` → Deploy WAR file
- `CMD [...]` → Start Payara Micro with config files
- Persistent configs (`domain.xml` and `mysql.jar`) copied into container

## Step 6: Build and Deploy Resource Application
Build Docker image:
```
docker build -t garmentsresourceimg .
```
Run Resource container:
```
docker run -d --name garmentsresourcecon --network garmentsNet -p 8085:8086 garmentsresourceimg
```
View logs to verify deployment:
```
docker logs -f garmentsresourcecon
```
Test API endpoint:
```
http://garmentsresource:8080/GarmentsApp/app/api
```

## Step 7: Client Application Dockerfile Setup

Update `microprofile-config.properties` in Client app:
```
http://garmentsresourcecon:8080/GarmentsApp/app/api
```
Create Dockerfile for Client app:
```
FROM payara/micro:6.2024.5-jdk17

ENV DEPLOY_DIR=/opt/payara/deployments

COPY artifact/GarmentsClientApp.war ${DEPLOY_DIR}/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/opt/payara/payara-micro.jar","--deploy","/opt/payara/deployments/GarmentsClientApp.war"]
```

## Step 8: Build and Deploy Client Application
Build Docker image:
```
docker build -t garmentsclientimg .
```
Run Client container:
```
docker run -d --name garmentsclientcon --network garmentsNet -p 8086:8080 garmentsclientimg
```

## Step 9: Test Applications

- Resource API: http://localhost:8085/GarmentsApp/app/api
- Client JSF page: http://localhost:8086/GarmentsClientApp/
- Client Servlet output: http://localhost:8086/GarmentsClientApp/GarmentsServlet

**🎉 Congratulations!** Your Garments MSA application is fully deployed on Docker.

## ⚠️ Important Notes

- Remove old containers/volumes if DB creation fails.
- Update JWT tokens and public key in Resource app if needed.
- Docker image/container names must be lowercase.
- Ensure `domain.xml` and mysql.jar exist and paths match Dockerfile configuration.
- Ports `8085`, `8086`, `3305` should be free before deployment.
