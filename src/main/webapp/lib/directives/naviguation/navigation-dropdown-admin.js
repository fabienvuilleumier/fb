var naviguation = angular.module('Fablab');
naviguation.directive('fabNavDropdownAdmin', function () {
    return {
        restrict: 'EA',
        scope: {
            icon: '@',
            label: '@',
            show: '='
        },
        template: '<a data-toggle="dropdown" class="dropdown-toggle" ng-show="show===undefined || show" href="javascript:;">'
                + '	<i class="fa fa-fw fa-{{icon}}"></i> {{label | translate}}'
                + '     <b class="caret"></b>'
                + '</a>'
                + '<ul class="dropdown-menu">'
                + '     <li class="dropdown-header" show="hasAnyRole(\'ADMIN\')">'
                + '            <span translate="menu.users"></span>'
                + '     </li> '
                + '     <li fab-nav-item-af link="users" icon="user" '
                + '         label="menu.users" '
                + '         show="hasAnyRole(\'ADMIN\')"></li>'
                + '     <li fab-nav-item-af link="membershipTypes" '
                + '         label="menu.membershipTypes" icon="users"'
                + '         show="hasAnyRole(\'ADMIN\')"></li>'
                + '     <li class="divider" show="hasAnyRole(\'ADMIN\')"></li>'
                + '     <li class="dropdown-header" show="hasAnyRole(\'ADMIN\')">'
                + '         <span translate="menu.configurations"></span>'
                + '     </li> '
                + '     <li fab-nav-item-af link="configurations" icon="key"'
                + '         label="menu.key"'
                + '         show="hasAnyRole(\'ADMIN\')"></li>'
                + '     <li fab-nav-item-af link="roles" icon="key"'
                + '         label="menu.roles"'
                + '         show="hasAnyRole(\'ADMIN\')"></li>'
                + '</ul>'
    };
});


