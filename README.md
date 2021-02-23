# TaskScheduler
 Task scheduler for quartz

Version 1.0

Initial Version of Task Scheduler in spring boot. Steps followed:
a) Create a spring-boot application using sprint initializr

b) While creating the initial repo, add quartz dependency

c) Application has two end points
   i) /task/v1/schedule -- schedule a task to run at every x seconds 

   ii) /task/v1/trigger  -- trigger an already scheduled task

d) Currently the service creates a file in c:\tmp\ folder. 

e) The file name is randomly generated as txt file

f) Swagger dependency has been added and api documentation pages are present

g) The end points are ssl enabled using self-signed certificate which is in resources folder
   Certificate generated using this command:
.\keytool -genkey -alias bootsecurity -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore c:\tmp\bootsecurity.p12 -validity 3650
