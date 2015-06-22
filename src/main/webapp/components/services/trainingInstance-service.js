(function () {
    'use strict';

    var app = angular.module('Fablab');
    app.factory('TrainingInstanceService', function ($log, $resource, $http) {
        var trainingInstance = $resource(App.API.TRAINING_INSTANCE_API + "/:id", {id: '@id'});
        return {
            list: function (successFn) {
                $http(
                    {
                        method: 'GET',
                        url: App.API.TRAINING_INSTANCE_API
                    }
                ).success(successFn);
            },
            remove: function (id, successFn) {
                $log.debug("TrainingInstanceService: remove...");
                trainingInstance.remove({id: id}, successFn);
            },
            softRemove: function (id, successFn) {
            $http.get(App.API.TRAINING_INSTANCE_API + "/softRemove?id=" + id).success(successFn);
            $log.debug("TrainingInstanceService: soft remove...");
            },
            save: function (trainingInstanceParam, successFn, errorFn) {
                $log.debug("TrainingInstanceService: save...");
                var saved = trainingInstance.save(trainingInstanceParam, successFn, errorFn);
                return saved;
            },
            get: function (id, successFn) {
                $log.debug("TrainingInstanceService: get...");
                var trainingInstanceRes = trainingInstance.get({id: id}, successFn);
                return trainingInstanceRes;
            }
        };
    });
}());

