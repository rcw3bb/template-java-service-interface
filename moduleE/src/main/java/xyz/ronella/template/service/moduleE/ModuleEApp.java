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
