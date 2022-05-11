# Throttling-project
## Mohammed Baker

This is a spring boot project, to implement a throttling service based on:
- Method
- IP Address

Each IP address should be able to call a method for limited number of time. 
## How to run?

- Run the spring boot project.
- Call the methods(Urls mentioned below)
- To change the number of times, you can change it from application.properties
- You can use @Throttling annotation to enable and disable the throttling for endpoints

## URLS
- Throttled endpoints (http://localhost:8080/tests/t1, http://localhost:8080/tests/t3)
- Non Throttled endpoint (http://localhost:8080/tests/t2, http://localhost:8080/tests/t4)

## Method Overview
This project is implemented using:
- Custom annotation
- Reflection
- H2 in memory database
- Intercepter

The main idea is to create a custom annotation to specify if the throttlinh check should be applied to the endpoint or not. An intercepter is used to catch every requests and check if the method is throttled or not. 

## Data Model
To save the number of attempts for specific IP address the data stored in the database with the following format:
- Id
- MtheodId which is the hash code for the method since that we need a unique Identifier for the method.
- IP Address
- Numbee Of Attemptes

## Why Intercepter Not Filter? 
The filter will catch the requests before they parsed using the dispatcherServlet. Hence, the requested method (And controller) for sepecific path is not mapped yet. On the other hand, the intercepter will catch the requests after the dispatcherServlet parsed them. 



