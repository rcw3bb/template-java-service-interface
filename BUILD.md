# Build

## Pre-requisite

* Java 17

## Building

Run the following command to build the application:

```
gradlew build
```

## Testing

Run the following command to test the application:

```
gradlew run
```

Expect to see something similar to the following output:

```
Running ModuleBApp:
ServiceImplementation1 is the default implementation provided by moduleA.

Running ModuleDApp:
ServiceImplementation2 is doing something different.
ServiceImplementation3 is doing something different.
ServiceImplementation1 is the default implementation provided by moduleA.
ServiceImplementation4 is doing something different.
```



