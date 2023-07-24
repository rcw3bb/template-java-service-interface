package xyz.ronella.template.service.moduleD;

import xyz.ronella.template.service.moduleA.ServiceInterface;

public class ServiceImplementation4 implements ServiceInterface {
    @Override
    public void doSomething() {
        System.out.println("ServiceImplementation4 is the implementation provided moduleD.");
    }
}