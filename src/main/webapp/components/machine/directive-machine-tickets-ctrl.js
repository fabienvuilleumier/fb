(function () {
    'use strict';
    angular.module('Fablab').directive('machineTickets',
            function ($filter, ngTableParams, $location) {
                return {
                    restrict: 'EA',
                    scope: {
                        tickets: '=?'
                    },
                    templateUrl: 'components/machine/directive-machine-tickets-view.html',
                    controller: function ($scope) {
                        $scope.tableParams = new ngTableParams(
                                angular.extend({
                                    page: 1, // show first page
                                    count: 25, // count per page
                                    sorting: {
                                        title: 'asc',
                                        creationDate: 'asc',
                                        previsionCloseDate: 'asc',
                                        closeDate: 'asc',
                                        status: 'asc'
                                    }
                                }, $location.search()), {
                            getData: function ($defer, params) {
                                if ($scope.tickets) {
                                    params.total($scope.tickets.length);
                                    $location.search(params.url());
                                    var filteredData = params.filter() ? $filter('filter')($scope.tickets, params.filter()) : $scope.tickets;
                                    var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
                                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                                }
                            }
                        });
                        $scope.tableParams.reload();
                    }
                };
            });
}());