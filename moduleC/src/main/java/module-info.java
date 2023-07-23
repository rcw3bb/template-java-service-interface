module template.moduleC {
    requires template.moduleA;

    provides xyz.ronella.template.service.moduleA.ServiceInterface with
            xyz.ronella.template.service.moduleC.ServiceImplementation2,
            xyz.ronella.template.service.moduleC.ServiceImplementation3;

}