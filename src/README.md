Spoing Source
=============

This is the source for the Spoing library.

IMPORTANT: If you wish to work on the Spoing library itself, you will need to set a platform dependency to reflect your development environment:

1 - Edit source/spoingPlatformAll/pom.xml

2 - Find the section that looks like this:
    
	<dependencies>
		<dependency>
			<groupId>org.reldb.spoing</groupId>
			<artifactId>spoingNativeWin_64</artifactId>
			<version>1.1.0</version>
		</dependency>
	</dependencies>
    
3 - If your OS platform matches what's in the &lt;artifactId&gt;...&lt;/artifactId&gt; tag, you're done! If it doesn't, change it to:
    
	spoingNativeLinux_64
	
   or
    
	spoingNativeMacos_64
	
   as appropriate.
   
4 - Spoing needs to be built before you can use it. To build and install the jars in your Maven repository, do this:

	mvn clean
	mvn install

