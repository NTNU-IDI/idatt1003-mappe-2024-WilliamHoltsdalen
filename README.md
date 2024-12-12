# Mappevurdering IDATT1003

STUDENT NAME = "William L. Holtsdalen"  
STUDENT ID = "10037"

## Project description
This project serves as the final project for the IDATT1003 course at NTNU (2024).

The purpose of this project is to provide a user-friendly interface for users to interact with a
food storage system, in order to decrease food waste. The project is a Java application that provides 
a menu-based text user interface, allowing users to add, remove, search for, and consume grocery items. The application 
also includes a cookbook, where the user can add, remove, and edit food recipes. Furthermore, the 
user can get suggestions for meals based on groceries in the food storage, as well as suggestions 
for meals based on groceries in the food storage that expire before a user-specified date.

## Project structure

The project's source code is located in the `src` directory. It is organized into two main packages:

The `src/main/java/edu/ntnu/idi/idatt` directory contains the packages where the application's code 
is located.
- the `models` package contains the core domain classes that represent the application's data.
- the `services` package contains service classes that provide functionality and business logic for the application.
- the `utils` package contains utility classes that provide functionality for the applications user interface, like user input and output.
- the `views` package contains the user interface class that interacts with the user.


- the `App.java` file contains the main method that launches the application.

The `src/test/java/edu/ntnu/idi/idatt` directory contains the JUnit test classes for the application.
- the `models` package contains the JUnit test classes for the core domain classes.

## Link to repository

[Link to the GitHub repository for this project](https://github.com/NTNU-IDI/idatt1003-mappe-2024-WilliamHoltsdalen)

## How to run the project

The very first step to running to the application is acquiring the source code. This can be done by downloading
a compressed .zip file from the GitHub repository, or cloning the repo directly to your computer, either via 
https or ssh. 

After you have acquired the source code, make sure you have the Java JDK installed on your computer. 
You can download the JDK from [here](https://www.oracle.com/java/technologies/downloads/).

You can open the project in an IDE like IntelliJ IDEA (highly recommended). Alternatively, you can 
run the project by using the command line / terminal in your operating system.

Once you are ready to launch the application go ahead and navigate to the src/main/java/edu/ntnu/idi/idatt 
package in IntelliJ IDEA and open the App.java file. With this file open, you can press the green arrow in the 
top right-hand corner of the screen, which is the default position for the run-button. If for some reason you 
cannot see this button, you can instead run the App.java file by right-clicking it in the file browser window 
and pressing the `Run ‘App.main()’` option.


Upon running the application, you will be greeted with a welcome message and prompted with the main menu of 
the application. Here you can choose which submenu you would like to enter by entering the corresponding 
number in the menu. If you enter an invalid number (not in the range 0-4) you will be notified of the error 
and prompted with the main menu again. This is how all menus work in the application. In each submenu you will 
find unique options to control the functionality of the application.

If you wish to add some demo data to the application, you can do so by entering the settings menu and selecting 
the corresponding option.

## How to run the tests
To be able to run the tests, you need to have the Java JDK installed, as well as Maven and JUnit. If you are using IntelliJ IDEA you 
can get the correct version of JUnit by going to the maven tab, and clicking the 'refresh' button. This will reload the Maven project
and load all necessary dependencies. 

Running the tests can be done by opening the project in an IDE like IntelliJ IDEA (recommended), right clicking on the `src/test/java` 
unit test package and selecting `Run 'Tests in 'java''`. If you wish to run any single test class by itself, you can do so by right 
clicking on the test class and selecting `Run 'TestClassName'`.
