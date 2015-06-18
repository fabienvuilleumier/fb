(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('MotionStockService', function ($log, $resource, $http) {
        var motionStock = $resource(App.API.MOTION_STOCK_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.MOTION_STOCK_API
                    }
                ).success(successFn);
            },
            get: function (id, successFn) {
                $log.debug("MotionStockService: get...");
                var motionStockRes = motionStock.get({id: id}, successFn);
                return motionStockRes;
            }
        };
    });
}());

