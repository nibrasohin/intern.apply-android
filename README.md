# intern.apply-android

Please see the [main repository](https://github.com/DimaMukhin/intern.apply) of the project for more information.

## Bugs

1. the test server run's on every build - for example: when you build project, run app and tests
2. the test server run's twice - once in configuration phase and then again in execution phase.

## running the application on production mode

1. clone the project
2. use android studio to compile and run the application

If the application does not work, please try to rebuild the whole project (clean + recompile)  
Try to restart the application once if at first nothing shows up (this is an issue with Android Studio). 

## running the application on development mode

1. clone the project
2. run the development server locally (see how to run the server [here](https://github.com/DimaMukhin/intern.apply))
3. change the base url of the api client to point to your local host server (see next topic for more details)
4. use android studio to compile and run the application

## changing api base url to point at local host development server

1. open `InternAPI.java`
2. change `private final String BASE_URL = "https://intern-apply.herokuapp.com/";` to point at `http://<localhost-ip>:3000`
3. example: `private final String BASE_URL = "http://192.168.1.2:3000";`  

Why manualy changing the BASE URL of the api? because Retrofit does not support "localhost" yet. We decided to keep using Retrofit anyways since this is not a big deal.

## running tests

right click on "AllIntegrationTests" and click on "run 'AllIntegrationTests'"

## running system tests

#Set the path of web server (intern.apply) to your local server repo in intern.apply.android/ServerScript.bat
right click on "SystemTests" and click on "run 'SystemTests'"
