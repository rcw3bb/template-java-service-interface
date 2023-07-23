import xyz.ronella.template.service.moduleA.ServiceInterface;
import xyz.ronella.template.service.moduleA.impl.ServiceImplementation1;

module template.moduleA {
    exports xyz.ronella.template.service.moduleA;
    exports xyz.ronella.template.service.moduleA.impl;

    provides ServiceInterface with ServiceImplementation1;
}