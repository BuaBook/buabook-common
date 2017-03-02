# BuaBook Common Library for Java

This library contains a number of helper functions that we have found useful throughout our Java applications.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.buabook/buabook-common/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.buabook/buabook-common)
[![Build Status](https://travis-ci.org/BuaBook/buabook-common.svg?branch=master)](https://travis-ci.org/BuaBook/buabook-common)
[![Coverage Status](https://coveralls.io/repos/github/BuaBook/buabook-common/badge.svg?branch=master)](https://coveralls.io/github/BuaBook/buabook-common?branch=master)

## Functionality

This library contains the following packages:

* `com.buabook.common`
    * `Formatters`: Currency and date converters
    * `Objects`: String to object conversion and object fields to map conversions
    * `Patterns`: E-mail pattern matcher
    * `Printers`: List and array conversions to string (for logging)
    * `Resources`: Resource loading from class path
    * `StringSplitter`: Improved String splitting
    * `Systems`: Provides application root folder and environment / system property access
    * `Uuids`: Null UUID access and null check

* `com.buabook.common.concurrent`
    * `NamedThreadFactory`: A thread factory that names each thread with a custom prefix and counter (useful for thread pools)

* `com.buabook.common.connection`
    * `Process`: Simple container to store connection details to a target process (hostname and port) 

* `com.buabook.common.net`
    * `DataSocket`: Socket wrapper provides input and output streams as `DataInputStream` and `DataOutputStream`
    * `SocketAcceptorThread`: Bind to port and notify of new inbound connections to the port (via `INewClientSocketListener` interface)

* `com.buabook.common.random`
    * `RandomCodeGenerator`: Generates random alphanumeric strings

* `com.buabook.common.shutdown`
    * `ShutdownThread`: Thread that is executed as the JVM shuts down, to allow cleanup prior to exit (using `ShutdownFunction`s)