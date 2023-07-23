package xyz.ronella.template.service.app;

import xyz.ronella.template.service.moduleB.ModuleBApp;
import xyz.ronella.template.service.moduleE.ModuleEApp;

public class App {
    public static void main(String[] args) {
        System.out.println("Running ModuleBApp:");
        ModuleBApp.main(args);

        System.out.println("\nRunning ModuleDApp:");
        ModuleEApp.main(args);
    }
}
