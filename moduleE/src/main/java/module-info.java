import xyz.ronella.template.service.moduleA.ServiceInterface;

module template.moduleE {
    uses ServiceInterface;
    requires template.moduleA;
    requires template.moduleC;
    requires template.moduleD;
    exports xyz.ronella.template.service.moduleE;
}