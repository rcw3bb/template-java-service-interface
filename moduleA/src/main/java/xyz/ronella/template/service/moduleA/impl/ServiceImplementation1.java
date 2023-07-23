package xyz.ronella.template.service.moduleA.impl;

import xyz.ronella.template.service.moduleA.ServiceInterface;

public class ServiceImplementation1 implements ServiceInterface {
    @Override
    public void doSomething() {
        System.out.println("ServiceImplementation1 is the default implementation provided by moduleA.");
    }
}