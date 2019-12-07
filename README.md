# ASD-Battleship

## Prerequisites
### Java

1. Install [JAVA 13 SDK](https://jdk.java.net/13/ "JAVA 13 SDK")
2. Install [JAVA FX 13](http://gluonhq.com/download/javafx-13.0.1-sdk-windows/ "JAVA FX 13") or download and copy to *C:\Program Files\Java\javafx-sdk-13.0.1*

### IntelliJ
3. Setup the Project Structure like this
[![Project Structure](https://i.ibb.co/F8044qb/Unbenannt.png "Project Structure")](https://i.ibb.co/F8044qb/Unbenannt.png "Project Structure")
4. Set the Run Configuration VM Options to this:
`--module-path "C:\Program Files\Java\javafx-sdk-13.0.1\lib" --add-modules javafx.base,javafx.controls,javafx.media`
[![Edit Run Configuration](https://i.ibb.co/Hd5YPtj/1.png "Edit Run Configuration")](https://i.ibb.co/Hd5YPtj/1.png "Edit Run Configuration")
[![Edit Run Configuration](https://i.ibb.co/238RN5R/1.png "Edit Run Configuration")](https://i.ibb.co/238RN5R/1.png "Edit Run Configuration")

------------


## Settings
###Logging
> [Log4J configuration help](https://www.tutorialspoint.com/log4j/log4j_configuration.htm "Log4J configuration help")

Log4j has three main components:
1. **loggers**: Responsible for capturing logging information.
2. **appenders**: Responsible for publishing logging information to various preferred destinations.
3. **layouts**: Responsible for formatting logging information in different styles.

Configuring log4j involves **assigning the Level**, **defining Appender**, and **specifying Layout objects** in a configuration file.

Possible log levels are:
- TRACE
- DEBUG
- INFO
- WARN
- ERROR
- FATAL
- ALL