package net.collaud.fablab.api.generator;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabien Vuilleumier
 */
public class BackendAngularListEditGenerator {

    private final Field[] FIELDS;
    private final String CLASS_NAME;
    private final String CLASS_ATTRIBUTE;
    private final String CLASS_SERVICE;

    private boolean hasList = false;
    private boolean hasEO = false;

    private final String SERVICE;
    private final String COMPONENTS;

    private final StringBuilder SERVICE_FP;
    private final StringBuilder EDIT_JS_FP;
    private final StringBuilder EDIT_VIEW_FP;
    private final StringBuilder LIST_JS_FP;
    private final StringBuilder LIST_VIEW_FP;
    private final StringBuilder STYLE_FP;

    private static BackendAngularListEditGenerator instance;

    private <T> BackendAngularListEditGenerator(Class<T> klazz) {
        this.FIELDS = klazz.getDeclaredFields();
        this.CLASS_NAME = klazz.getSimpleName().substring(0, klazz.getSimpleName().length() - 2);
        this.CLASS_ATTRIBUTE = CLASS_NAME.substring(0, 1).toLowerCase() + CLASS_NAME.substring(1);
        this.CLASS_SERVICE = CLASS_NAME + "Service";

        this.SERVICE = ".\\src\\main\\webapp\\components\\services";
        this.COMPONENTS = ".\\src\\main\\webapp\\components\\";

        SERVICE_FP = new StringBuilder();
        EDIT_JS_FP = new StringBuilder();
        EDIT_VIEW_FP = new StringBuilder();
        LIST_JS_FP = new StringBuilder();
        LIST_VIEW_FP = new StringBuilder();
        STYLE_FP = new StringBuilder();
        initFullPaths();
    }

    private void initFullPaths() {
        SERVICE_FP.append(SERVICE).append("\\").append(CLASS_ATTRIBUTE).append("-service");
        EDIT_JS_FP.append(COMPONENTS).append("\\").append(CLASS_ATTRIBUTE).append("\\").append(CLASS_ATTRIBUTE).append("-edit-ctrl");
        EDIT_VIEW_FP.append(COMPONENTS).append("\\").append(CLASS_ATTRIBUTE).append("\\").append(CLASS_ATTRIBUTE).append("-edit-view");
        LIST_JS_FP.append(COMPONENTS).append("\\").append(CLASS_ATTRIBUTE).append("\\").append(CLASS_ATTRIBUTE).append("-list-ctrl");
        LIST_VIEW_FP.append(COMPONENTS).append("\\").append(CLASS_ATTRIBUTE).append("\\").append(CLASS_ATTRIBUTE).append("-list-view");
        STYLE_FP.append(COMPONENTS).append("\\").append(CLASS_ATTRIBUTE).append("\\style");
    }

    public static synchronized <T> BackendAngularListEditGenerator getInstance(Class<T> klazz) {
        if (instance == null) {
            instance = new BackendAngularListEditGenerator(klazz);
        }
        return instance;
    }

    private String genereListVIEW() {

        StringBuilder str = new StringBuilder();
        str.append("<h1 translate=\"").append(CLASS_ATTRIBUTE).append(".title\"></h1>").append("\n");
        str.append("<div class=\"header-control\">").append("\n");
        str.append("    ").append("<div class=\"btn-group\" >").append("\n");
        str.append("        ").append("<a class=\"btn btn-success\" href=\"#/").append(CLASS_ATTRIBUTE).append(endWithS()).append("/").append(CLASS_ATTRIBUTE).append("-edit\">{{'button.create'| translate}} <span class=\"glyphicon glyphicon-plus\"> </span></a>").append("\n");
        str.append("    ").append("</div>").append("\n");
        str.append("</div>").append("\n");
        str.append("<div class=\"").append(CLASS_ATTRIBUTE).append("List\" >").append("\n");
        str.append("    ").append("<table ng-table=\"tableParams\" class=\"table\" show-filter=\"true\">").append("\n");
        str.append("        ").append("<tr ng-repeat=\"").append(CLASS_ATTRIBUTE).append(" in $data\">").append("\n");

        for (Field f : FIELDS) {
            if (!f.getName().equals("id") && !f.getName().equals("active")) {
                if (!f.getType().getSimpleName().contains("List")) {
                    str.append("            ").append("<td ").append("\n");
                    str.append("                ").append("data-title=\"'").append(CLASS_ATTRIBUTE).append(".").append(f.getName()).append("' | translate\" ").append("\n");
                    str.append("                ").append("sortable=\"'").append(f.getName()).append("'\"").append("\n");
                    str.append("                ").append("filter=\"{'").append(f.getName()).append("':'text'}\">").append("\n");
                    if (f.getType().getSimpleName().contains("EO")) {
                        str.append("                ").append("{{").append(CLASS_ATTRIBUTE).append(".").append(f.getName()).append(".").append("label").append("}}").append("\n");
                    } else {
                        str.append("                ").append("{{").append(CLASS_ATTRIBUTE).append(".").append(f.getName()).append("}}").append("\n");
                    }
                    str.append("            ").append("</td>").append("\n");

                }
            }
        }

        str.append("            ").append("<td>").append("\n");
        str.append("                ").append("<div class=\"btn-group\">").append("\n");
        str.append("                    ").append("<a class=\"btn btn-sm btn-default\" href=\"#/").append(CLASS_ATTRIBUTE).append(endWithS()).append("/").append(CLASS_ATTRIBUTE).append("-edit/{{").append(CLASS_ATTRIBUTE).append(".id}}\"><span class=\"glyphicon glyphicon-pencil\"></span></a>").append("\n");
        str.append("                    ").append("<button class=\"btn btn-sm btn-danger\" ").append("\n");
        str.append("                        ").append("ng-really-click=\"softRemove(").append(CLASS_ATTRIBUTE).append(")\"").append("\n");
        str.append("                        ").append("ng-really-message=\"{{'").append(CLASS_ATTRIBUTE).append(".confirmation.remove'|translate}}\">").append("\n");
        str.append("                        ").append("<span class=\"glyphicon glyphicon-trash\"></span>").append("\n");
        str.append("                    ").append("</button>").append("\n");
        str.append("                ").append("</div>").append("\n");
        str.append("            ").append("</td>").append("\n");
        str.append("        ").append("</tr>").append("\n");
        str.append("    ").append("</table> ").append("\n");
        str.append("</div>").append("\n");
        return str.toString();
    }

    private String appendType(String input) {
        String res;
        switch (input.toUpperCase()) {
            case "INTEGER":
                return "number";
            case "DOUBLE":
                return "number\" step=\"0.05\"";
            case "DATE":
                return "date";
            default:
                return "text";
        }
    }

    private String genereListJS() {
        StringBuilder str = new StringBuilder();
        str.append("'use strict';").append("\n");
        str.append("var app = angular.module('Fablab');").append("\n");
        str.append("app.controller('").append(CLASS_NAME).append("ListController', function ($scope, $filter, $location,").append("\n");
        str.append("        ").append("ngTableParams, ").append(CLASS_SERVICE).append(", NotificationService) {").append("\n");
        str.append("    ").append("$scope.tableParams = new ngTableParams(").append("\n");
        str.append("            ").append("angular.extend({").append("\n");
        str.append("                ").append("page: 1, // show first page").append("\n");
        str.append("                ").append("count: 25, // count per page").append("\n");
        str.append("                ").append("sorting: {").append("\n");
        for (Field f : FIELDS) {
            if (!f.getName().equals("id") && !f.getName().equals("active")) {
                if (!f.getType().getSimpleName().contains("List")) {
                    str.append("                    ").append(f.getName()).append(":'asc',").append("\n");
                }
            }
        }
        str.deleteCharAt(str.length() - 2);
        str.append("                ").append("}").append("\n");
        str.append("            ").append("}, $location.search()), {").append("\n");
        str.append("        ").append("getData: function ($defer, params) {").append("\n");
        str.append("            ").append("if ($scope.").append(CLASS_ATTRIBUTE).append(endWithS()).append(") {").append("\n");
        str.append("                ").append("params.total($scope.").append(CLASS_ATTRIBUTE).append(endWithS()).append(".length);").append("\n");
        str.append("                ").append("$location.search(params.url());").append("\n");
        str.append("                ").append("var filteredData = params.filter() ? $filter('filter')($scope.").append(CLASS_ATTRIBUTE).append(endWithS()).append(", params.filter()) : $scope.").append(CLASS_ATTRIBUTE).append(endWithS()).append(";").append("\n");
        str.append("                ").append("var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;").append("\n");
        str.append("                ").append("$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));").append("\n");
        str.append("            ").append("}").append("\n");
        str.append("        ").append("}").append("\n");
        str.append("    ").append("});").append("\n");
        str.append("    ").append("var update").append(CLASS_NAME).append("List = function () {").append("\n");
        str.append("        ").append(CLASS_SERVICE).append(".list(function (data) {").append("\n");
        str.append("            ").append("$scope.").append(CLASS_ATTRIBUTE).append(endWithS()).append(" = JSOG.decode(data);").append("\n");
        str.append("            ").append("$scope.tableParams.reload();").append("\n");
        str.append("        ").append("});").append("\n");
        str.append("    ").append("};").append("\n");

        str.append("    ").append("$scope.remove = function (").append(CLASS_ATTRIBUTE).append(") {").append("\n");
        str.append("        ").append(CLASS_SERVICE).append(".remove(").append(CLASS_ATTRIBUTE).append(".id, function () {").append("\n");
        str.append("            ").append("NotificationService.notify(\"success\", \"").append(CLASS_ATTRIBUTE).append(".notification.removed\");").append("\n");
        str.append("            ").append("update").append(CLASS_NAME).append("List();").append("\n");
        str.append("        ").append("});").append("\n");
        str.append("    ").append("};").append("\n");

        str.append("    ").append("$scope.softRemove = function (").append(CLASS_ATTRIBUTE).append(") {").append("\n");
        str.append("        ").append(CLASS_SERVICE).append(".softRemove(").append(CLASS_ATTRIBUTE).append(".id, function () {").append("\n");
        str.append("            ").append("NotificationService.notify(\"success\", \"").append(CLASS_ATTRIBUTE).append(".notification.removed\");").append("\n");
        str.append("            ").append("update").append(CLASS_NAME).append("List();").append("\n");
        str.append("        ").append("});").append("\n");
        str.append("    ").append("};").append("\n");

        str.append("    ").append("update").append(CLASS_NAME).append("List();").append("\n");
        str.append("});").append("\n");
        return str.toString();
    }

    private String genereEditVIEW() {
        StringBuilder str = new StringBuilder();
        str.append("<div class=\"").append(CLASS_ATTRIBUTE).append("Edit\">").append("\n");
        str.append("    ").append("<form class=\"form-horizontal\" name=\"edit").append(CLASS_NAME).append("\" ng-submit=\"save(false)\">").append("\n");
        str.append("        ").append("<div class=\"panel panel-default\">").append("\n");
        str.append("            ").append("<div class=\"panel-heading\">").append("\n");
        str.append("                ").append("<span ng-if=\"new").append(CLASS_NAME).append("\" translate=\"").append(CLASS_ATTRIBUTE).append(".create\"></span>").append("\n");
        str.append("                ").append("<span ng-if=\"!new").append(CLASS_NAME).append("\" translate=\"").append(CLASS_ATTRIBUTE).append(".edit\"></span>").append("\n");
        str.append("                ").append("<span ng-if=\"!new").append(CLASS_NAME).append("\"> {{").append(CLASS_ATTRIBUTE).append(".").append(FIELDS[1].getName()).append("}}</span>").append("\n");
        str.append("            ").append("</div>").append("\n");
        str.append("            ").append("<div class=\"panel-body\">").append("\n");
        for (Field f : FIELDS) {
            if (!f.getName().equals("id") && !f.getName().equals("active")) {
                if (!f.getType().getSimpleName().contains("List")) {
                    if (f.getType().getSimpleName().contains("EO")) {
                        str.append("            ").append("<div class=\"form-group\">").append("\n");
                        str.append("                ").append("<label class=\"col-sm-2 control-label\" translate=\"").append(CLASS_ATTRIBUTE).append(".").append(f.getName()).append("\"></label>").append("\n");
                        str.append("                    ").append("<div class=\"col-sm-8\">").append("\n");
                        str.append("                        ").append("<select class=\"form-control\" ").append("\n");
                        str.append("                        ").append("required").append("\n");
                        str.append("                        ").append("ng-model=\"").append(CLASS_ATTRIBUTE).append(".").append(f.getName()).append("\"").append("\n");
                        str.append("                    ").append("ng-options=\"common.label for common in ").append(f.getName()).append("List track by common.id\"></select>").append("\n");
                        str.append("                ").append("</div>").append("\n");
                        str.append("            ").append("</div>").append("\n");
                    } else {
                        str.append("                ").append("<div class=\"form-group\">").append("\n");
                        str.append("                    ").append("<label class=\"col-sm-2 control-label\" translate=\"").append(CLASS_ATTRIBUTE).append(".").append(f.getName()).append("\"></label>").append("\n");
                        str.append("                    ").append("<div class=\"col-sm-8\">").append("\n");
                        str.append("                        ").append("<input type=\"").append(appendType(f.getType().getSimpleName())).append("\" class=\"form-control\" ng-model=\"").append(CLASS_ATTRIBUTE).append(".").append(f.getName()).append("\" required/>").append("\n");
                        str.append("                    ").append("</div>").append("\n");
                        str.append("                ").append("</div>").append("\n");

                    }
                }
            }
        }
        str.append("            ").append("</div>").append("\n");
        str.append("            ").append("<div class=\"panel-footer right\">").append("\n");
        str.append("                ").append("<btn-cancel link=\"#/").append(CLASS_ATTRIBUTE).append(endWithS()).append("\"></btn-cancel>").append("\n");
        str.append("                ").append("<btn-submit form=\"edit").append(CLASS_NAME).append("\"></btn-submit>").append("\n");
        str.append("            ").append("</div>").append("\n");
        str.append("        ").append("</div>").append("\n");
        str.append("    ").append("</form>").append("\n");
        str.append("</div>").append("\n");
        return str.toString();
    }

    private String genereEditJS() {
        StringBuilder str = new StringBuilder();
        str.append("'use strict';").append("\n");
        str.append("var app = angular.module('Fablab');").append("\n");
        str.append("app.controller('Global").append(CLASS_NAME).append("EditController', function ($scope, $location,").append("\n");
        str.append("    ").append(CLASS_SERVICE).append(", NotificationService");
        if (hasEO) {
            str.append(", StaticDataService");
        }
        str.append(") {").append("\n");

        str.append("    ").append("$scope.selected = {").append(CLASS_ATTRIBUTE).append(": undefined};").append("\n");

        str.append("    ").append("$scope.load").append(CLASS_NAME).append(" = function (id) {").append("\n");
        str.append("        ").append(CLASS_SERVICE).append(".get(id, function (data) {").append("\n");
        str.append("            ").append("$scope.").append(CLASS_ATTRIBUTE).append(" = data;").append("\n");
        str.append("        ").append("});").append("\n");
        str.append("    ").append("};").append("\n");

        str.append("    ").append("$scope.save = function () {").append("\n");
        str.append("        ").append("var ").append(CLASS_ATTRIBUTE).append("Current = angular.copy($scope.").append(CLASS_ATTRIBUTE).append(");").append("\n");
        str.append("        ").append(CLASS_SERVICE).append(".save(").append(CLASS_ATTRIBUTE).append("Current, function (data) {").append("\n");
        str.append("            ").append("$scope.").append(CLASS_ATTRIBUTE).append(" = data;").append("\n");
        str.append("            ").append("NotificationService.notify(\"success\", \"").append(CLASS_ATTRIBUTE).append(".notification.saved\");").append("\n");
        str.append("            ").append("$location.path(\"").append(CLASS_ATTRIBUTE).append(endWithS()).append("\");").append("\n");
        str.append("        ").append("});").append("\n");
        str.append("    ").append("};").append("\n");

        for (Field f : FIELDS) {
            if (f.getType().getSimpleName().contains("hasEO") && !f.getType().getSimpleName().contains("List")) {
                str.append("    ").append("StaticDataService.load").append(f.getName().substring(0, 1).toUpperCase()).append(f.getName().substring(1)).append(endWithS()).append("(function (data) {").append("\n");
                str.append("        ").append("$scope.").append(f.getName()).append("List = data;").append("\n");
                str.append("    ").append("});").append("\n");
            }
        }

        str.append("}").append("\n");
        str.append(");").append("\n");

        str.append("app.controller('").append(CLASS_NAME).append("NewController', function ($scope, $controller) {").append("\n");
        str.append("    ").append("$controller('Global").append(CLASS_NAME).append("EditController', {$scope: $scope});").append("\n");
        str.append("    ").append("$scope.new").append(CLASS_NAME).append(" = true;").append("\n");
        str.append("    ").append("$scope.").append(CLASS_ATTRIBUTE).append(" = new Object();").append("\n");
        str.append("}").append("\n");
        str.append(");").append("\n");
        str.append("    ").append("app.controller('").append(CLASS_NAME).append("EditController', function ($scope, $routeParams, $controller) {").append("\n");
        str.append("    ").append("$controller('Global").append(CLASS_NAME).append("EditController', {$scope: $scope});").append("\n");
        str.append("    ").append("$scope.new").append(CLASS_NAME).append(" = false;").append("\n");
        str.append("    ").append("$scope.load").append(CLASS_NAME).append("($routeParams.id);").append("\n");
        str.append("}").append("\n");
        str.append(");").append("\n");
        return str.toString();
    }

    private String genereServiceJS() {
        StringBuilder str = new StringBuilder();
        str.append("(function () {").append("\n");
        str.append("    ").append("'use strict';").append("\n\n");
        str.append("    ").append("var app = angular.module('Fablab');").append("\n");
        str.append("    ").append("app.factory('").append(CLASS_SERVICE).append("', function ($log, $resource, $http) {").append("\n");
        str.append("        ").append("var ").append(CLASS_ATTRIBUTE).append(" = $resource(App.API.").append(getApiName()).append(" + \"/:id\", {id: '@id'});").append("\n");

        str.append("        ").append("return {").append("\n");

        str.append("            ").append("list: function (successFn) {").append("\n");
        str.append("                ").append("$http(").append("\n");
        str.append("                    ").append("{").append("\n");
        str.append("                        ").append("method: 'GET',").append("\n");
        str.append("                        ").append("url: App.API.").append(getApiName()).append("\n");
        str.append("                    ").append("}").append("\n");
        str.append("                ").append(").success(successFn);").append("\n");
        str.append("            ").append("},").append("\n");

        str.append("            ").append("remove: function (id, successFn) {").append("\n");
        str.append("                ").append("$log.debug(\"").append(CLASS_SERVICE).append(": remove...\");").append("\n");
        str.append("                ").append(CLASS_ATTRIBUTE).append(".remove({id: id}, successFn);").append("\n");
        str.append("            ").append("},").append("\n");

        str.append("            ").append("softRemove: function (id, successFn) {").append("\n");
        str.append("            ").append("$http.get(App.API.").append(getApiName()).append(" + \"/softRemove?id=\" + id).success(successFn);").append("\n");
        str.append("            ").append("$log.debug(\"").append(CLASS_SERVICE).append(": soft remove...\");").append("\n");
        str.append("            ").append("},").append("\n");

        str.append("            ").append("save: function (").append(CLASS_ATTRIBUTE).append("Param, successFn, errorFn) {").append("\n");
        str.append("                ").append("$log.debug(\"").append(CLASS_SERVICE).append(": save...\");").append("\n");
        str.append("                ").append("var saved = ").append(CLASS_ATTRIBUTE).append(".save(").append(CLASS_ATTRIBUTE).append("Param, successFn, errorFn);").append("\n");
        str.append("                ").append("return saved;").append("\n");
        str.append("            ").append("},").append("\n");

        str.append("            ").append("get: function (id, successFn) {").append("\n");
        str.append("                ").append("$log.debug(\"").append(CLASS_SERVICE).append(": get...\");").append("\n");
        str.append("                ").append("var ").append(CLASS_ATTRIBUTE).append("Res = ").append(CLASS_ATTRIBUTE).append(".get({id: id}, successFn);").append("\n");
        str.append("                ").append("return ").append(CLASS_ATTRIBUTE).append("Res;").append("\n");
        str.append("            ").append("}").append("\n");

        str.append("        ").append("};").append("\n");
        str.append("    ").append("});").append("\n");
        str.append("}());").append("\n");
        return str.toString();
    }

    public void genere(String styleContent, String... roles) throws IOException {
        createDirectory();
        init();
        createFile(genereServiceJS(), SERVICE_FP, "js");
        createFile(genereEditJS(), EDIT_JS_FP, "js");
        createFile(genereEditVIEW(), EDIT_VIEW_FP, "html");
        createFile(genereListJS(), LIST_JS_FP, "js");
        createFile(genereListVIEW(), LIST_VIEW_FP, "html");
        createFile(styleContent, STYLE_FP, "css");
        System.out.println("\n\n");
        System.out.println(printOther(roles));
    }

    private void createDirectory() throws IOException {
        FileSystem fs = FileSystems.getDefault();
        StringBuilder str = new StringBuilder();
        str.append(COMPONENTS).append("\\").append(CLASS_ATTRIBUTE);
        Path dir = fs.getPath(str.toString());
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
            System.out.println("Directory " + CLASS_ATTRIBUTE + " create");
        } else {
            System.out.println("Directory " + CLASS_ATTRIBUTE + " already created");
        }

    }

    private String printOther(String... roles) {
        StringBuilder str = new StringBuilder();
        str.append("ATTENTION A EDIT VIEW ET LIST VIEW SI IL Y A DES LISTS !!!!!\n\n");
        str.append("#####IN INDEX.HTML FILE#####").append("\n");
        str.append("        ").append("<link href=\"./components/").append(CLASS_ATTRIBUTE).append("/style.css\" rel=\"stylesheet\">").append("\n");
        str.append("\n");
        str.append("        ").append("<li fab-nav-item-af link=\"").append(CLASS_ATTRIBUTE).append(endWithS()).append("\" icon=\"\" ").append("\n");
        str.append("            ").append("label=\"menu.");
        str.append(CLASS_ATTRIBUTE).append(endWithS()).append("\" ").append("\n");
        str.append("            ").append("show=\"hasAnyRole('");
        for (String s : roles) {
            str.append(s).append(",");
        }
        str.deleteCharAt(str.length() - 1);
        str.append("')\"></li>").append("\n");
        str.append("\n");
        str.append("        ").append("<script src=\"./components/services/").append(CLASS_ATTRIBUTE).append("-service.js\"></script>").append("\n");
        str.append("\n");
        str.append("        ").append("<script src=\"./components/").append(CLASS_ATTRIBUTE).append("/").append(CLASS_ATTRIBUTE).append("-list-ctrl.js\"></script>").append("\n");
        str.append("        ").append("<script src=\"./components/").append(CLASS_ATTRIBUTE).append("/").append(CLASS_ATTRIBUTE).append("-edit-ctrl.js\"></script>").append("\n");
        str.append("########################\n\n").append("\n");
        str.append("#####IN APP.JS FILE#####").append("\n");
        str.append("    ").append(" }).when('/").append(CLASS_ATTRIBUTE).append(endWithS()).append("', {").append("\n");
        str.append("        ").append("templateUrl: './components/").append(CLASS_ATTRIBUTE).append("/").append(CLASS_ATTRIBUTE).append("-list-view.html',").append("\n");
        str.append("        ").append("controller: '").append(CLASS_NAME).append("ListController'").append("\n");
        str.append("    ").append("}).when('/").append(CLASS_ATTRIBUTE).append(endWithS()).append("/").append(CLASS_ATTRIBUTE).append("-edit/:id', {").append("\n");
        str.append("        ").append("templateUrl: './components/").append(CLASS_ATTRIBUTE).append("/").append(CLASS_ATTRIBUTE).append("-edit-view.html',").append("\n");
        str.append("        ").append("controller: '").append(CLASS_NAME).append("EditController'").append("\n");
        str.append("    ").append("}).when('/").append(CLASS_ATTRIBUTE).append(endWithS()).append("/").append(CLASS_ATTRIBUTE).append("-edit', {").append("\n");
        str.append("        ").append("templateUrl: './components/").append(CLASS_ATTRIBUTE).append("/").append(CLASS_ATTRIBUTE).append("-edit-view.html',").append("\n");
        str.append("        ").append("controller: '").append(CLASS_NAME).append("NewController'").append("\n");
        str.append("########################\n\n").append("\n");
        str.append("#####IN EN.JS FILE#####").append("\n");
        str.append("IN MENU : ").append("\n");
        str.append("    ,").append(CLASS_ATTRIBUTE).append(endWithS()).append("").append(" : '").append(CLASS_NAME).append(endWithS()).append("'");
        str.append("\n");
        str.append("EACH ").append("\n");
        str.append(",").append(CLASS_ATTRIBUTE).append(": {").append("\n");
        str.append("    ").append("title: '").append(CLASS_NAME).append("',").append("\n");
        for (Field f : FIELDS) {
            if (!f.getName().equals("id")  || !f.getName().equals("active")) {
                str.append("    ").append(f.getName()).append(": '").append((f.getName().substring(0, 1)).toUpperCase()).append(f.getName().substring(1)).append("',").append("\n");
            }
        }
        str.append("    ").append("create:'").append(CLASS_NAME).append(" creation',").append("\n");
        str.append("    ").append("edit:'Edit :',").append("\n");
        str.append("    ").append("notification: {").append("\n");
        str.append("        ").append("saved: '").append(CLASS_NAME).append(" saved',").append("\n");
        str.append("        ").append("removed: '").append(CLASS_NAME).append(" removed'").append("\n");
        str.append("    ").append("},").append("\n");
        str.append("    ").append("confirmation: {").append("\n");
        str.append("        ").append("remove: 'Do you really want to remove this ").append(CLASS_ATTRIBUTE).append(" ?'").append("\n");
        str.append("    ").append("}").append("\n");
        str.append("}").append("\n");
        str.append("########################\n\n").append("\n");
        str.append("#####IN ROLES FILE (net.collaud.fablab.api.security)#####").append("\n");
        for (String s : roles) {
            str.append("        ").append("public static final String ").append(s).append(" = \"").append(s).append("\";\n");
            str.append(",").append("\n").append("    ").append(s).append("\n");
        }
        str.append("########################\n\n").append("\n");

        str.append("#####STATIC_DATA IF NEEDED#####").append("\n");
        for (Field f : FIELDS) {
            if (f.getType().getSimpleName().contains("EO")) {
                str.append("    ").append("this.load").append(f.getName().substring(0, 1).toUpperCase()).append(f.getName().substring(1)).append(endWithS()).append(" = function (successFn) {").append("\n");
                str.append("        ").append(f.getName().substring(0, 1).toUpperCase()).append(f.getName().substring(1)).append("Service.list(successFn);").append("\n");
                str.append("    ").append("};").append("\n");
            }
        }
        str.append("########################\n\n").append("\n");
        return str.toString();
    }

    private void createFile(String content, StringBuilder path, String ext) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(path).append(".").append(ext);
        Path p = fs.getPath(fullPath.toString());
        Files.deleteIfExists(p);
        List<String> lines = new ArrayList<>();
        lines.add(content);
        Files.write(p, lines, StandardCharsets.UTF_8);
        System.out.println("File " + path + " correctly created...");
    }

    private StringBuilder getApiName() {
        StringBuilder str = new StringBuilder();
        String[] words = CLASS_NAME.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        for (String s : words) {
            str.append(s.toUpperCase()).append("_");
        }
        str.append("API");
        return str;
    }

    private void init() {
        for (Field f : FIELDS) {
            String type = f.getType().getSimpleName();
            hasList = hasList || type.contains("List");
            hasEO = hasEO || (type.contains("EO") && !type.contains("List"));
        }
    }

    private String endWithS() {
        if (CLASS_NAME.substring(CLASS_NAME.length() - 1).equals("s")) {
            return "";
        } else {
            return "s";
        }
    }
}
