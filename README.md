# Microservices-demo

Micro services demo application based on Paul Chapman blog (https://spring.io/blog/2015/07/14/microservices-with-spring) on spring.io website.

The demo is created using Spring boot, Spring data(wraps JPA and hibernate), Spring fox,Junit and Mockito.

It exposes "Users" micro service, through a swagger UI, provides insert new and delete users and query them by email or name.

For demo purposes, an in memory H2 database had been used. No vault secrets had been used to secure credentials.

Clone it and either load into your favorite IDE or use maven to build it from command line.  


# testing and exploring the user micro service end point

  Hit the following swagger URL:[http://localhost:2222/codechallenge/swagger-ui.html#/user-controller] to explore and test
  the user mirco service operations.
  
  In real life, a JS based single page app. or Java/node back end consumer should be developed and pushed to an independent repo. 
  
# in scope , done
   * basic functionality.
   * 85%+ key classes code(services, controllers) covered by unit tests. No need to write tests for repository since 
    it only inherits Spring data.
   * Basic code quality review by Sonar eclipse plugin.
   * swagger ui via spring fox.
   * in memory database for demo purposes
   * basic database scripts that can be used when migrating to a persistent database.
   * basic build using maven.

# in scope , Work in progress
   * Finish remaining unit tests
   * In a real application central exception handling should be managed by HandlerExceptionResolver.
   * input validation(via @BeanValidation).
   * Automated tests.
   * Configure jacoco maven plugin for code coverage reports.
   * Add sonar maven plugin
   * Apache Maven Checkstyle Plugi using google_checks.xml.
   * prepare a runner script.
   * Dockerize the project and upload it on docker registry.
   * Create a Docker file to spin new instances upon need(beware of JVM parameters and c groups awareness).

# out of scope
   * impodomdent patch for parial updates of entities(using etags)
   * Pagination and other performance enhancement techniques.
   * White and black listing at query level, other security concerns.
   * Usage of a real database.
   * Securing DB credentials in vaults.
   * Data sharding and eventually consistency problems when using one database instance per micro service instance.
   * Circuit breaker back pressure(Hystrex, extra)
   * Make the micro service Euroka enabled(service discovery).
   * Use load balancer like Ribbon.
   * Authentication and Authorization(JWT/SWT, AUth0, extra).
   * Creation of a resilient fault tolerant single page application/ back end consumer
   * Anything related to Sagas or event sourcing. 
