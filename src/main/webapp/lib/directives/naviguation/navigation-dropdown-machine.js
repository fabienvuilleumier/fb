var naviguation = angular.module('Fablab');
naviguation.directive('fabNavDropdownMachine', function () {
    return {
        restrict: 'EA',
        scope: {
            icon: '@',
            label: '@',
            show: '='
        },
        template: '<a data-toggle="collapse" data-target="#machineListDD" ng-show="show===undefined || show" href="javascript:;">'
                + '	<i class="fa fa-fw fa-{{icon}}"></i> {{label | translate}}'
                + '     <b class="caret"></b>'
                + '</a>'
                + '<ul id="machineListDD" class="collapse">'
                + '     <li fab-nav-item-af link="machines" icon="sliders" '
                + '         label="menu.machines" '
                + '         show="hasAnyRole(\'MACHINE_MANAGE\')"></li>'
                + '     <li fab-nav-item-af link="machineTypes" icon="print" '
                + '         label="menu.machineTypes" '
                + '         show="hasAnyRole(\'MACHINE_MANAGE\')"></li>'
                + '     <li fab-nav-item-af link="machineStates" icon="file-text-o" '
                + '	    label="menu.machineState" '
                + '	    show="hasAnyRole(\'MACHINE_MANAGE\')"></li>'
                + '     <li fab-nav-item-af link="machineStatus" icon="exclamation-triangle" '
                + '	    label="menu.machineStatus" '
                + '	    show="hasAnyRole(\'MACHINE_MANAGE\')"></li>'
                + '     <hr show="hasAnyRole(\'MACHINE_MANAGE\')"></hr>'
                + '     <li fab-nav-item-af link="revisions" icon="check-square-o" '
                + '         label="menu.revision" '
                + '         show="hasAnyRole(\'MACHINE_MANAGE\')"></li>'
                + '     <hr show="hasAnyRole(\'MACHINE_MANAGE\')"></hr>'
                + '     <li fab-nav-item-af link="tickets" icon="ticket" '
                + '         label="menu.tickets" '
                + '         show="hasAnyRole(\'TICKET_MANAGE\')"></li>'
                + '     <li fab-nav-item-af link="ticketStatus" icon="terminal" '
                + '         label="menu.ticketStatus" '
                + '         show="hasAnyRole(\'TICKET_MANAGE\')"></li>'
                + '</ul>'
    };
});


