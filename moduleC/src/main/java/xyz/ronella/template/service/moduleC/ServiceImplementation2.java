package xyz.ronella.template.service.moduleC;

import xyz.ronella.template.service.moduleA.ServiceInterface;

public class ServiceImplementation2 implements ServiceInterface {
    @Override
    public void doSomething() {
        System.out.println("ServiceImplementation2 is doing something different.");
    }
}
