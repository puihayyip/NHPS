# National Harmonized Pharmacy System (NHPS)

## Pre-requiste Software
1. RabbitMQ
2. MySQL (preferably with workbench)
3. Maven 3.6.0
4. Java 17
5. Docker Desktop

## Instructions to configure on local
- Start RabbitMQ, navigate to http://localhost:15672 and make sure you could sign in with username: 'guest' and password: 'guest'
- Request github token from me and paste the value into 'ConfigServer/src/main/resources/application.yml' (I'm thinking of a way to encrypt it so github doesn't invalidate my token)
- Request apiEncryptionKey.jsk from me and paste it in the root folder and into 'ConfigServer' folder
- Request user password from me. Inside MySQL, login as root user then create a new user with username: 'team42' and secret password. Grant the user all priviledges using `GRANT ALL PRIVILEGES ON *.* TO 'team42'@'localhost';`
- Create 1 new connection in MySQL. Sign into the connection using the created user and create 2 new databases 'nhps_users' and 'nhps_patients'

## Starting the servers
1. Run configServer first by running `mvn spring-boot:run` or by configuring a new application in intellij
2. Run discoveryServer
3. Run usersService/patientsService (non-consequential)
4. Run apiGateway

## Running the app
- Navigate to 'http://localhost:8010' and sign in with username: 'puihayyip' and password which I would provide. This is a dashboard running on Eureka to see all the instances of different services registered on the server
- Navigate to 'ApiGateway/src/main/resources/application.yml' to see the list of apis available
- All requests are route through apiGateway service which is exposed on port 8082 and mapped to each service by a suffix like 'users-ws' for the usersService. Therefore an example request to get a single patient by nric would be 'http://localhost:8082/patients-ws/api/patients/NRIC'

## Tasks remaining
1. Create 3 more services: prescription, dispense, billing.
2. Write controllers and service implementations to finish the backend
3. Create the frontend with Angular
4. Dockerize services and host on amazon ECR. FE to be hosted on aws amplify
5. Spin up fargate instances for the images + create amazon RDS
7. Write github actions to automate build, test, deployment onto fargate
8. Drink beer when all done

## Good to haves
- S3 and lambda service to host images
- Configure more comprehensive tests for the test automation
- Host apiGateway and discoveryService on EC2 loadbalancer instead and configure autoscaling
