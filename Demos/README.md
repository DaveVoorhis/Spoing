Demonstration Application
=========================

This application demonstrates the basic capability of Spoing, and also serves as a starting point for building your own applications.

To configure it, use Maven:

	mvn clean install

Six scripts are provided, three desktop (Windows, MacOS and Linux) and three Web (Windows, MacOS, and Linux).

To run a demo, use the command-line to cd to the _Deploy/demo subdirectory, then run the Web or Desktop script for your operating system.

The Web demos will start up a Web server which will remain running until explicitly terminated via Ctrl-c. When the server starts, it will
show the URL to access the application.