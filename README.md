# Cucumber-Framework Pico container, Extent Report, Cucumber jvm parallel plugin
Pico container, Extent Report, Cucumber jvm parallel plugin

Steps to be Followed
1. Property file - Change the report path as per your folder structure
2. Change the driver for chrome and desired browser in WebConnector.java
3. Run the Runner file by right clicking on it, as Junit
4. You can change the tag annotation in Runner file, "~@login" means you don't want to include the scenario, just removing scenario will become runnable
5. For running in parallel mode go to cmd of project structure and just type mvn test
