SLABCODE assessment
------------------------------------------------------------------------------------------------------
assessment is a basic REST API back-end java application to perform a project managment

---------------------------------------------

DEPENDENCIES
------------------------------------------------------------------------------------------------------

* The application runs with java 8 or higher,
* uses maven for dependency handling
* uses spring boot for inversion of control and http concerns handling

------------------------------------------------------------------------------------------------------
PACKAGING
------------------------------------------------------------------------------------------------------

* point path inside project directory, in my case as an example I am using windows 10
  'C:\Users\LENOVO\Documents\review\projects\slabcode-assessment>'

* be sure the path is pointing inside the application directory in my case the welcome prompt shows
  'C:\Users\LENOVO\Documents\review\projects\slabcode-assessment>'

* to build the project, enter the following command and note a BUILD SUCCESS

----

    mvn clean install

> !!! To skip the tests use parameter : -DskipTests

------------------------------------------------------------------------------------------------------
RUN
------------------------------------------------------------------------------------------------------

* to run the application

----

    mvn spring-boot:run

------------------------------------------------------------------------------------------------------
WHAT NEXT ?
------------------------------------------------------------------------------------------------------

* Unit testing (the best practice is TDD)
* Code style
* Some documentation

