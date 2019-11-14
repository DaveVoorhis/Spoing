Spoing Demo Application Core
============================

This is the main application code, which can be identical or nearly identical for both the Web and multiple desktop platforms.

Where necessary, desired differences between Web and desktop behaviour can be implemented via simple conditionals. Just check whether _PlatformDetect.isWeb()_
is true or not. Similarly, you can implement differences based on host operating system -- whether Web or desktop -- via appropriate methods in _PlatformDetect_.

In the provided demo, _PlatformDetect_ is only used in org.reldb.spoing.demo.launcher.Launcher.java
