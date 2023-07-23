package xyz.ronella.template.service.moduleB;

import xyz.ronella.template.service.moduleA.impl.ServiceImplementation1;
import xyz.ronella.template.service.moduleA.ServiceInterface;

public class ModuleBApp {
    public static void main(String[] args) {
        ServiceInterface service = new ServiceImplementation1();
        service.doSomething();
    }
}