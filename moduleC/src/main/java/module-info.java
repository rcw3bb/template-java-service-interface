import xyz.ronella.template.service.moduleA.ServiceInterface;
import xyz.ronella.template.service.moduleC.ServiceImplementation2;
import xyz.ronella.template.service.moduleC.ServiceImplementation3;

module template.moduleC {
    requires template.moduleA;
    provides ServiceInterface with ServiceImplementation2, ServiceImplementation3;
}