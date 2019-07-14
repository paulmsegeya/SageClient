"use strict";

(function () {

    angular.module('sageClientApp').config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/customer");
        
        $stateProvider.state("test", {
            url: "/customer",
            templateUrl: "app/components/customer-info/customer-info.html"
        }).state("test2", {
        	url: "/test2",
        	templateUrl: "app/components/customer-info/customer-info.html",
    		params: {
    	        data: null
    	    }
    	})
    });
}());

