# Build

## Pre-requisite

* Java 17

## Building

Run the following command to build the template application:

```
gradlew build
```

## Testing the Applications

Run the following command to all the applications:

```
gradlew run
```

> This template contains three applications. 

Expect to see something similar to the following output:

```
> Task :run
Running ModuleBApp:
ServiceImplementation1 is the default implementation provided by moduleA.

Running ModuleDApp:
ServiceImplementation2 is the implementation provided moduleC.
ServiceImplementation3 is the implementation provided moduleC.
ServiceImplementation1 is the default implementation provided by moduleA.
ServiceImplementation4 is the implementation provided moduleD.

> Task :moduleB:mergeClasses SKIPPED

> Task :moduleB:run
ServiceImplementation1 is the default implementation provided by moduleA.

> Task :moduleE:mergeClasses SKIPPED

> Task :moduleE:run
ServiceImplementation2 is the implementation provided moduleC.
ServiceImplementation3 is the implementation provided moduleC.
ServiceImplementation1 is the default implementation provided by moduleA.
ServiceImplementation4 is the implementation provided moduleD.
```

