#!/bin/sh
jdk/bin/java -cp "bin:tomcat/*:WebContent/WEB-INF/lib/*" org.reldb.spoing.MainProd --port 8080
