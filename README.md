# JPMS Service Implementation Template

A template of service implementation with JPMS.

[TOC]

## Pre-requisite

* Java 17

## Cloning

1. **Create a fork** of this repository.

2. **Clone the forked repository** to your machine.

3. **Test your cloned repository** using the following command:

   ```
   gradlew run
   ```

   > See the output in [BUILD.md](BUILD.md) testing section.

## Modules

This section describes what each module in this template is all about.

## template.moduleA API

This is the module the exposes the following interface:

```java
public interface ServiceInterface {
    void doSomething();
}
```

The preceding interface is the one the **must be provided by the other modules** for any **other modules** the uses it *(i.e. except for moduleB application that uses a traditional way of using an interface and implementation)*.

This module also provides a default implementation with the following class:

```
xyz.ronella.template.service.moduleA.impl.ServiceImplementation1
```

> The implementation class in this module is optional. It is just here to show that the exposer of the interface can also be a provider.

### module-info.java

```java
import xyz.ronella.template.service.moduleA.ServiceInterface;
import xyz.ronella.template.service.moduleA.impl.ServiceImplementation1;

module template.moduleA {
    exports xyz.ronella.template.service.moduleA;
    exports xyz.ronella.template.service.moduleA.impl;
    provides ServiceInterface with ServiceImplementation1;
}
```

This modules exports the following packages:

* xyz.ronella.template.service.moduleA *(i.e. holds the ServiceInterface.)*

* xyz.ronella.template.service.moduleA.impl *(i.e. holds the ServiceImplementation1 default implementation.)*

  > The implementation package is not required to be exposed. It is here because of moduleB application only.

Also this offers a default implementation *(i.e. optional)* with the following class using the provides directive:

* ServiceImplementation1 *(i.e. this is only necessary if the **uses directive** was used by the module requiring the ServiceInterface implementation.)*

## template.moduleB Application

This module is an **application** that uses the **ServiceInterface** from **tempalte.moduleA** but it does not use the **uses directive** in **module-info**. Additionally, it uses the default **ServiceImplementation1** from the same module as follows:

```java
package xyz.ronella.template.service.moduleB;

import xyz.ronella.template.service.moduleA.impl.ServiceImplementation1;
import xyz.ronella.template.service.moduleA.ServiceInterface;

public class ModuleBApp {
    public static void main(String[] args) {
        ServiceInterface service = new ServiceImplementation1();
        service.doSomething();
    }
}
```

> Notice the **import section**, it is referencing moduleA's ServiceInterface and its default implementation *(i.e. ServiceImplementation1)*. 

> The direct reference to ServiceImplementation1 class is the reason why template.moduleA must export the following package within its module-info:
>
> ```java
> xyz.ronella.template.service.moduleA.impl
> ```
>
> The modern way is to use the ServiceLoader.load method *(i.e. the strategy used in template.moduleE)*.

This is the traditional way of using an interface and implementation from a different module *(i.e. template.moduleA)*. 

### module-info.java

```java
module template.moduleB {
    requires template.moduleA;
    exports xyz.ronella.template.service.moduleB;
}
```

The modules requires template.moduleA because it is using an interface and a class from it. It also exposes its own package to be shared with other modules *(i.e. **Root Application** of this template)*. 

### Using Gradle to Run the Application

Use the following command to **run moduleB application**:

```
gradlew :moduleB:run
```

> See the [Build](BUILD.md) to build all the applications.

Expect to see the following output:

```
ServiceImplementation1 is the default implementation provided by moduleA.
```

### Packaging the Application

Use the following command the package the application:

```
gradlew :moduleB:packWin
```

This will zip the application to the following directory:

```
<PROJECT_ROOT>\moduleB\build\pack
```

### Using the Packaged Application

1. **Unzip the package** from the directory mentioned in the preceding section.

2. Using command prompt, **navigate to the bin directory** of the extracted package.

3. **Execute** the following command:

   ```
   moduleB
   ```

   Expect to see the following output:

   ```
   ServiceImplementation1 is the default implementation provided by moduleA.
   ```

## template.moduleC API Implementation

This module provides multiple implementations of template.moduleA's ServiceInterface.

### module-info.java

```java
import xyz.ronella.template.service.moduleA.ServiceInterface;
import xyz.ronella.template.service.moduleC.ServiceImplementation2;
import xyz.ronella.template.service.moduleC.ServiceImplementation3;

module template.moduleC {
    requires template.moduleA;
    provides ServiceInterface with ServiceImplementation2, ServiceImplementation3;
}
```

The modules requires template.moduleA because it is using an interface from it and provides the following implementations:

* ServiceImplementation2
* ServiceImplementation3

## template.moduleD API Implementation

This module provides an implementations of template.moduleA's ServiceInterface.

### module-info.java

```java
import xyz.ronella.template.service.moduleA.ServiceInterface;
import xyz.ronella.template.service.moduleD.ServiceImplementation4;

module template.moduleD {
    requires template.moduleA;
    provides ServiceInterface with ServiceImplementation4;
}
```

The modules requires template.moduleA because it is using an interface from it and provides the following implementation:

* ServiceImplementation4

## template.moduleE Application

This module is an **application** that uses the **ServiceInterface** from **template.moduleA** that use the **uses directive** in **module-info**. It uses all the implementations provided by the other modules *(i.e. template.moduleA, template.moduleC and template.moduleD)*:

```java
package xyz.ronella.template.service.moduleE;

import xyz.ronella.template.service.moduleA.ServiceInterface;

import java.util.ServiceLoader;

public class ModuleEApp {
    public static void main(final String ... args) {
        final var serviceLoader = ServiceLoader.load(ServiceInterface.class);
        for (final ServiceInterface service : serviceLoader) {
            service.doSomething();
        }
    }
}
```

The **ServiceLoader.load method** used in the preceding code is the one that **interrogates the classloader** for all the **module provided implementations of ServiceInterface** *(i.e. template.moduleA, template.moduleC and template.moduleD implementations of ServiceInterface)*.

### module-info.java

```java
import xyz.ronella.template.service.moduleA.ServiceInterface;

module template.moduleE {
    uses ServiceInterface;
    requires template.moduleA;
    requires template.moduleC;
    requires template.moduleD;
    exports xyz.ronella.template.service.moduleE;
}
```

The module indicated that it **requires implementations of ServiceInterface** using the **uses directive**.

The following modules are required because the application needs their provided implementations of ServiceInterface:

* template.moduleA *(i.e. aside from providing the interface definition but also providing a default implementation.)*

  > The default implementation is optional. If you don't want this to be discovered by the ServiceLoader.load method remove the provides directive related to it in template.moduleA's module-info.

* template.moduleC *(i.e. provides multiple implementations.)*

* template.moduleD *(i.e. provides additional implementation.)*

This module also exposes its own package to be shared with other modules *(i.e. **Root Application** of this template)*. 

### Using Gradle to Run the Application

Use the following command to **run moduleE application**:

```
gradlew :moduleE:run
```

> See the [Build](BUILD.md) to build all the applications.

Expect to something similar to the following output:

```
ServiceImplementation2 is the implementation provided moduleC.
ServiceImplementation3 is the implementation provided moduleC.
ServiceImplementation1 is the default implementation provided by moduleA.
ServiceImplementation4 is the implementation provided moduleD.
```

### Packaging the Application

Use the following command the package the application:

```
gradlew :moduleE:packWin
```

This will zip the application to the following directory:

```
<PROJECT_ROOT>\moduleE\build\pack
```

### Using the Packaged Application

1. **Unzip the package** from the directory mentioned in the preceding section.

2. Using command prompt, **navigate to the bin directory** of the extracted package.

3. **Execute** the following command:

   ```
   moduleE
   ```

   Expect to something similar to the following output:

   ```
   ServiceImplementation2 is the implementation provided moduleC.
   ServiceImplementation3 is the implementation provided moduleC.
   ServiceImplementation1 is the default implementation provided by moduleA.
   ServiceImplementation4 is the implementation provided moduleD.
   ```

## template.service Root Application

This module is the **gradle root project** of this template application. It demonstrate the usage of other application modules *(i.e. template.moduleB and template.moduleE)*. This is the reason why template.moduleB and template.moduleE applications have exported their packages within their own respective module-info.

> Without this root application, both template.moduleB and template.moduleE don't need to export their packages.

```java
package xyz.ronella.template.service.app;

import xyz.ronella.template.service.moduleB.ModuleBApp;
import xyz.ronella.template.service.moduleE.ModuleEApp;

public class App {
    public static void main(final String ... args) {
        System.out.println("Running ModuleBApp:");
        ModuleBApp.main(args);

        System.out.println("\nRunning ModuleDApp:");
        ModuleEApp.main(args);
    }
}
```

### module-info.java

```java
module template.service {
    requires template.moduleB;
    requires template.moduleE;
}
```

Since we need to call the application entrypoints from template.moduleB and template.moduleE applications. It is necessary to require the following modules:

* template.moduleB *(i.e. traditional way of using the ServiceInterface.)*
* template.moduleE *(i.e. using the ServiceLoader.load method.)*

### Using Gradle to Run the Application

Use the following command to **run template.service application**:

> Where does the template.service coming from? It is name of the module in module-info.

```
gradlew :run
```

> See the [Build](BUILD.md) to build all the applications.

Expect to see something similar to the following output:

```
Running ModuleBApp:
ServiceImplementation1 is the default implementation provided by moduleA.

Running ModuleDApp:
ServiceImplementation2 is the implementation provided moduleC.
ServiceImplementation3 is the implementation provided moduleC.
ServiceImplementation1 is the default implementation provided by moduleA.
ServiceImplementation4 is the implementation provided moduleD.
```

### Packaging the Application

Use the following command the package the application:

```
gradlew :packWin
```

This will zip the application to the following directory:

```
<PROJECT_ROOT>\build\pack
```

### Using the Packaged Application

1. **Unzip the package** from the directory mentioned in the preceding section.

2. Using command prompt, **navigate to the bin directory** of the extracted package.

3. **Execute** the following command:

   ```
   template-service-interface
   ```

   > Where does the template-service-interface coming from? It is the rootProject.name defined in settings.xml of this module.

   Expect to see something similar to the following output:

   ```
   Running ModuleBApp:
   ServiceImplementation1 is the default implementation provided by moduleA.
   
   Running ModuleDApp:
   ServiceImplementation2 is the implementation provided moduleC.
   ServiceImplementation3 is the implementation provided moduleC.
   ServiceImplementation1 is the default implementation provided by moduleA.
   ServiceImplementation4 is the implementation provided moduleD.
   ```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## [Build](BUILD.md)

## [Changelog](CHANGELOG.md)

## Author

* Ronaldo Webb
