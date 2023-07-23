module template.moduleE {
    requires template.moduleA;
    requires template.moduleC;
    requires template.moduleD;

    exports  xyz.ronella.template.service.moduleE;
    uses xyz.ronella.template.service.moduleA.ServiceInterface;
}