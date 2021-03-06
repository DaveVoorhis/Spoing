Spoing into Action!
===================

Spoing is a set of libraries and sample source code to simplify
building and deploying lightweight, fast-loading, cross-platform
(Windows, Linux and MacOS) desktop and Web applications from one pure
Java source code base. No XML, CSS, HTML, Javascript, or significantly-different Web/desktop Java source is required. 

In short: One Java source base gets you an application that runs on Windows, MacOS, Linux, and the Web.

If you want to quickly write and deploy an application to run on any
desktop and/or the Web, Spoing makes it easy to do it.

Spoing includes everything you need to get started, including:
* An embedded Tomcat server for launching Web applications (https://tomcat.apache.org/)
* The SWT desktop UI libraries from Eclipse.org (https://www.eclipse.org/swt/)
* The RAP/RWT Web UI libraries from Eclipse.org (https://www.eclipse.org/rap/)
* Libraries to abstract away differences between SWT and RAP/RWT (https://www.eclipse.org/rap/developers-guide/devguide.php?topic=rwt.html)
* Sample application code

The provided libraries are easily separable, so you can either release
for all supported platforms -- i.e., Windows, Linux, MacOS, Web -- or
a subset if you wish.

Future updates will:
* Add improved documentation
* Consider integration with (for example) Tabris
(https://eclipsesource.com/products/tabris/) to enable mobile app
targets;
* Where possible, move portions of sample code to the libraries, to
simplify creating new applications.

## Required JDK

This release is intended for Java Development Kit (JDK) 11 and above,
but can be recompiled using Eclipse or other IDE -- or the Maven
pom.xml scripts -- to work with JDK 9 or above.

You can obtain a JDK 11 and above from
https://openjdk.java.net. Download the bundle appropriate to your
environment, unarchive it to a directory of your choice, and set your
PATH environment variable to point to its _bin_ directory.

The Oracle JDK 11 or above from
https://www.oracle.com/technetwork/java/javase/downloads/index.html
should work, but hasn't been tested.

The consumer-oriented run-time JRE 8 installer (e.g.,
https://download.java.net) should be considered obsolete. It was
intended for a model of deployment that is no longer used, where a
Java Virtual Machine (aka JVM, which is part of the Java Runtime
Edition or JRE) was downloaded and installed separately from Java
applications. Modern Java development bundles an application-specific
JRE (generated using JDK tools) with each application.

## Running the Demo

1 - Install JDK 11 or higher, as described above.

2 - Install Apache Maven, if you have not already done so. See https://maven.apache.org/install.html 

3 - Download this project to your system, if you have not already done so.

4 - Via the command-line, change to the project directory. Use Maven to clean and build the project:

	mvn clean
	mvn install

5 - To try a demo, change to the Demos directory. Use the command-line
to run the Web or Desktop script appropriate to your operating
system. Six scripts are provided, three desktop (Windows, MacOS and
Linux) and three Web (Windows, MacOS, and Linux).

The Web demos will start up a Web server which will remain running
until explicitly terminated via Ctrl-c. When the server starts, it
will show the URL to access the application. Normally,
http://localhost:8080 will work.
