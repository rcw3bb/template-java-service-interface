package xyz.ronella.template.service.moduleE;

import xyz.ronella.template.service.moduleA.ServiceInterface;

import java.util.ServiceLoader;

public class ModuleEApp {
    public static void main(String[] args) {
        ServiceLoader<ServiceInterface> serviceLoader = ServiceLoader.load(ServiceInterface.class);
        for (ServiceInterface service : serviceLoader) {
            service.doSomething();
        }
    }
}
