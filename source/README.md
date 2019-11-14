Demonstration Application
=========================

This application demonstrates the basic capability of Spoing, and also serves as a starting point for building your own applications.

To run it, you'll need a Java 13 or higher installation on your path. You can download a Java installation from https://jdk.java.net/13/

The provided projects are for Eclipse 2019-09 or higher, but other IDE's will work. The ant build system is used to build Spoing, but all libraries
are pre-built so you don't _have_ to use ant.

Six scripts are provided, three desktop (Windows, MacOS and Linux) and three Web (Windows, MacOS, and Linux).

To run a demo, use the command-line to cd to the _Deploy/demo subdirectory, then run the Web or Desktop script for your operating system. The scripts
are intentionally as simple as possible to illustrate which .jars are needed.

The Web demos will start up a Web server which will remain running until explicitly terminated via Ctrl-c. When the server starts, it will
show the URL to access the application.