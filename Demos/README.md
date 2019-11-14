Demonstration Application
=========================

This application demonstrates the basic capability of Spoing, and also serves as a starting point for building your own applications.

Before you can try the demos, you have to build them with maven:

	mvn clean
	mvn install

Once you have built the demos, you will notice that six scripts are
provided, three desktop (Windows, MacOS and Linux) and three Web
(Windows, MacOS, and Linux).

To run a demo, use the command-line to cd to the Demos subdirectory,
then run the Web or Desktop script for your operating system.

The Web demos will start up a Web server which will remain running
until explicitly terminated via Ctrl-c. When the server starts, it
will show the URL to access the application.
