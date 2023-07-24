package xyz.ronella.template.service.moduleB;

import xyz.ronella.template.service.moduleA.impl.ServiceImplementation1;

public class ModuleBApp {
    public static void main(final String ... args) {
        final var service = new ServiceImplementation1();
        service.doSomething();
    }
}