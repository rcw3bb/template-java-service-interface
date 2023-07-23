import xyz.ronella.template.service.moduleA.ServiceImplementation1;

module template.moduleA {
    exports xyz.ronella.template.service.moduleA;
    //uses xyz.ronella.template.service.moduleA.ServiceInterface;
    provides ServiceImplementation1 with ServiceImplementation1;
}