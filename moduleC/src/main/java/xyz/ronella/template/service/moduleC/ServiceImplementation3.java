package xyz.ronella.template.service.moduleC;

import xyz.ronella.template.service.moduleA.ServiceInterface;

public class ServiceImplementation3 implements ServiceInterface {
    @Override
    public void doSomething() {
        System.out.println("ServiceImplementation3 is doing something different.");
    }
}
