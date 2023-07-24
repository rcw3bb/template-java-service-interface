import xyz.ronella.template.service.moduleA.ServiceInterface;
import xyz.ronella.template.service.moduleD.ServiceImplementation4;

module template.moduleD {
    requires template.moduleA;
    provides ServiceInterface with ServiceImplementation4;
}