# ServByte Inc Easy Serve
ServByte Inc provide businesses with a simple and easy-to-use solution to allow them serve their customers and keep growing their businesses.

# About The Solution
This apllication is developed using Spring Boot to build a RESTFul backend that communicate with front end build with Vuejs, by recieving API request and processing it. It currecnt uses an in-memory database H2 for data storage.

# System Requirements
The solution is develop and tested on a Windows platform 64 bit, but it should run on any operating system.
For following tools where used for development and testing:

- Windows Os 64bit
- Spring Tool Suit (STS) 4
- VS Code Editor
- NPM (version 6.14.12)
- @vue/cli 4.4.1
- JDK 8
- Maven 2.8
- Browser (Chrome Opera)
- POSTMAN

# Project Setup
The backend of the application build on Spring Boot, requires Port 8080 to be available for it to run. While the front end build with Vuejs is configured to run on Port 4000, however you can edit from script section of the package.json (servbyte/client/package.json) 
##### Spring Boot backend setup:
Open the terminal of your system and run the following command
```
git clone https://github.com/nobleson/servbyte.git
cd servbyte/server
mvn clean spring-boot:run
```
If everything goes well, you will see your backend application running on port 8080
There is a preloaded data to help you test the front end.
##### Vuejs front end setup:
We assumed that you have already deployed the backend application on your localhost and is running ok.
Open a new terminal and execute the following command
```
 cd servbyte/client
 npm install
 npm run serve
```
If averything goes well, you should see your application running on localhost port 4000. Open it from the browswer using this link:
```
http://localhost:4000
```
You can test the applcation which will allow you to check for services that are available on ServByte.
Food Service is available so you can click and view or search Restaurants, from location of choice, check their menu, order a meal and make payment.
![servbyte](https://user-images.githubusercontent.com/14866971/128034908-5beedf8e-0600-4859-ae51-7d93a5b96fd1.PNG)


# Order Payment 
The payment gateway is paytack. the application is able to process your order payment, however you will not be allowed to make payment using this application due to cross origin issues, since it is running on a localhost 




