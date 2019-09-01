"use strict";

(function () {

    angular.module('sageClientApp').config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/customer");
        
        $stateProvider.state("customer", {
            url: "/customer",
            templateUrl: "app/components/customer-info/customer-info.html"
        }).state("reports", {
        	url: "/reports",
        	templateUrl: "app/components/customer-info/reports.html",
    		params: {
    	        reportData: []
    	    }
    	})
    });
}());

