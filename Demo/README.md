Spoing Demo Application
=======================

This application can run equivalently on the desktop and on the Web from one source base.

Where necessary, desired differences between Web and desktop behaviour can be implemented via simple conditionals. Just check whether _PlatformDetect.isWeb()_
is true or not. Similarly, you can implement differences based on host operating system -- whether Web or desktop -- via appropriate methods in _PlatformDetect_.

In the provided demo, _PlatformDetect_ is only used in org.reldb.spoing.demo.launcher.Launcher.java
