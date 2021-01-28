# Web API Test Automation Framework

- This framework is desired to implement the POM and DDD frameworks, so it can separate the data 
  from the implementation
  
  ![Alt text](readmescreenshots/pages.png?raw=true "POM")
  
- User can start adding the object repository using the attached Excel sheet under the resources
  directory and follow up the same criteria
  by adding the name of the element then findBy then the selector value
  ![Alt text](readmescreenshots/resources.jpg?raw=true "repo")

- User can add the test data in the `.properties` file under the resources' directory
  ![Alt text](readmescreenshots/resources.jpg?raw=true "test data")

- Data can be read from the classes `LoadDataFile` to load the user's test data and
   `ReadExternalData` to read the object repo data
under the `helper` package
  ![Alt text](readmescreenshots/helper.jpg?raw=true "helper")
  
- The idea behind using the current module is to separate the data from the test scenarios and to
  be easy to switch between drivers, so the user is able to use the `RemoteWebDriver` and declare is once, then assign to it any driver 
  like chrome, firefox, etc... to
  ![Alt text](readmescreenshots/remotewebdriver.png?raw=true "remotewebdriver")
  ![Alt text](readmescreenshots/drivers.jpg?raw=true "drivers")

- Then you don't have to use the selenium classes and methods to find the elements as you can use the class `getElement` 
this class contains the implementation of getting the `findBy` as text from the user using the method `readObjectRepo(String elementName)`
  under the `ReadExternalData` class in the `helper` package
  ![Alt text](readmescreenshots/readObjectRepo.png?raw=true "readObjectRepo")
  ![Alt text](readmescreenshots/getElement.png?raw=true "getElement")
  -- description attached to the methods 


  use one driver with multiple browsers, even in the future we can use mobile as well by just adding 
  its class under the `targets` package
  ![Alt text](readmescreenshots/targets.png?raw=true "targets")

- User can select the platform using the `testng` parameters
  ![Alt text](readmescreenshots/testng.png?raw=true "testng")

- The user can get the step logs using the `Listener` class under the `test` package
  ![Alt text](readmescreenshots/listener.png?raw=true "listener")

- The user can get a test report after the execution done using the default browser in his machine
  ![Alt text](readmescreenshots/reports.jpg?raw=true "reports")
  ![Alt text](readmescreenshots/report.jpg?raw=true "report")

> How to run 
  * Using the GUI/IDE
    - To start running the test cases the user should select/type the platform name in the `parameters` 
  tag in the `testng.xml` file, and the test classes you want to run.
    - Then `R.C click` on the same file --> `run`

* Using the terminal command line
	* Open terminal/cmd -> navigate to project directory -> `mvn clean test -P webRun`

  ![Alt text](readmescreenshots/profile.png?raw=true "profile")

  