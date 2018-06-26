# Overview

A RESTful application that polls the latest rates of EUR->USD from a mock server or forex.io.

# TechStack
  * Java >= 1.7
  * SpringBoot 2.0.2
  * Maven
  * H2 database

# Usage
  * Build the application with 'mvn clean install'
  
# Configuration
  * It is possible to configure the url to get the exchange rate by selecting the spring profiles active, running _mvn -Dspring.profiles.active=[mock,prd]_.
  * Also possible to configure the fetch polling rate with _-Dscheduler.fetchRatePeriod=[TIME_IN_MS]_.

# Testing
 * 'mvn test' will run all unit tests.

# [API](docs/API.md)
 
