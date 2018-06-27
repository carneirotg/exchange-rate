# Overview

A RESTful application that polls the latest rates of EUR->USD from a mock server or forex.io.

# TechStack
  * Java >= 1.7
  * SpringBoot 2.0.2
  * Maven
  * H2 database

# Usage
  * Build the application with _mvn clean install_.
  
# Configuration
  * It is possible to configure the url to get the exchange rate by selecting the spring profiles active, running _mvn -Dspring.profiles.active=[mock,prd]_.
  * Also possible to configure the fetch polling rate with _-Dscheduler.fetchRatePeriod=[TIME_IN_MS]_.
  * It is also possible to query in the database after the project is running by the url: _http://localhost:8080/h2_
  
# Runnig
 * After run with _mvn spring-boot:run_ the endpoints will be available on _http://localhost:8080_

# Testing
 * 'mvn test' will run all unit tests.

# [API](docs/API.md)
 
