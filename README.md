# Spring Boot Assessment JYS

##System Requirements
In order to  build and run the application under Windows 10, the following 
prerequisites have to be met:
* Java JDK 11 or above;
* JDK must be added to PATH;
* Maven (https://maven.apache.org/download.cgi);
* Maven must be added to PATH;
* Docker (https://www.docker.com/products/docker-desktop);
* Ubuntu 18.04 LTS from Windows store;
* An IDE for JAva like Eclipse, Netbeans, or IntelliJ;


## MySQL database
MySQL is hosted in Docker and can be built using the command below from PowerShell. 
Open a PowerShell console in the project root folder, and execute the shell command below: 
```shell script
docker-compose up -d
```
Once MySQL has been deployed to the Docker container, the DB 'technicalassessment' can be populated, 
using the sql scripts located in the project 'resource' folder.
There are two scripts in the project resources folder, one to create the DB schema and one to populate 
the tables with some test data;


#How to run the Application
The application can be run from the IDE. This will make the API available to the View Layer, 
via restful calls.
The application supports the following:
* Add a new User (REST /user/add/{username}/{firstname}/{lastname}/{role});
* Get user data (REST /user/get/{username});
* Remove an existing User (REST /user/delete/{username});
* Update an existing User (REST /user/update/{username}/{firstname}/{lastname}/{role});
* Add a new product (REST /product/add/{name}/{price}/{description});
* Get Product data (REST /product/get/{name})
* Remove an existing Product (REST /product/delete/{name});

``Get User and Producr use a loose search for partial strings.``



