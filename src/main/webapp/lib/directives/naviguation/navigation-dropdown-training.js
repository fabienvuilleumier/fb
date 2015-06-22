var naviguation = angular.module('Fablab');
naviguation.directive('fabNavDropdownTraining', function () {
    return {
        restrict: 'EA',
        scope: {
            icon: '@',
            label: '@',
            show: '='
        },
        template: '<a data-toggle="collapse" data-target="#trainingListDD" ng-show="show===undefined || show" href="javascript:;">'
                + '	<i class="fa fa-fw fa-{{icon}}"></i> {{label | translate}}'
                + '     <b class="caret"></b>'
                + '</a>'
                + '<ul id="trainingListDD" class="collapse">'
                + '    <li fab-nav-item-af link="trainingLevels" icon="list" '
                + '        label="menu.trainingLevels" '
                + '        show="hasAnyRole(\'TRAINING_MANAGE\')"></li>'
                + '    <li fab-nav-item-af link="trainings" icon="book" '
                + '        label="menu.trainings" '
                + '        show="hasAnyRole(\'TRAINING_MANAGE\')"></li>'
                + '    <li fab-nav-item-af link="trainingInstances" icon="mortar-board" '
                + '        label="menu.trainingInstances" '
                + '        show="hasAnyRole(\'TRAINING_MANAGE\')"></li>'
                + '</ul>'
    };
});


