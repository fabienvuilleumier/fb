(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('PriceMachineService', function ($log, $resource, $q, $http) {
        var priceMachine = $resource(App.API.PRICE_MACHINE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                        {
                            method: 'GET',
                            url: App.API.PRICE_MACHINE_API
                        }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("PriceMachineService: remove...");
                priceMachine.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
                $http.get(App.API.PRICE_MACHINE_API + "/softRemove?id=" + id).success(successFn);
                $log.debug("PriceMachineService: soft remove...");
            },
            save: function (priceMachineParam, successFn, errorFn) {
                $log.debug("PriceMachineService: save...");
                var saved = priceMachine.save(priceMachineParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("PriceMachineService: get...");
                var priceMachineRes = priceMachine.get({id: id}, successFn);
                return priceMachineRes;
            },
            getPriceMachineType: function (machineTypeId, membershipTypeId, successFn) {
                $http.get(App.API.PRICE_MACHINE_API + "/getPriceMachineType?machineTypeId="
                        + machineTypeId + "&membershipTypeId=" + membershipTypeId).success(successFn);
            },
            getPMT: function (machineTypeId, membershipTypeId) {
                var temp = {};
                var defer = $q.defer();
                $http.get(App.API.PRICE_MACHINE_API + "/getPriceMachineType?machineTypeId="
                        + machineTypeId + "&membershipTypeId=" + membershipTypeId).success(function (data) {
                    temp = data;
                    defer.resolve(data);
                });
                return defer.promise;
            }
        };
    });
}());

