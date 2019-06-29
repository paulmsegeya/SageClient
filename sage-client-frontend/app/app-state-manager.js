"use strict";

(function () {

    angular.module('sageClientApp').config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/test");
        
        $stateProvider.state("test", {
            url: "/test",
            templateUrl: "app/components/test/test.html"
        }).state("test2", {
        	url: "/test2",
        	templateUrl: "app/components/test/test2.html",
    		params: {
    	        data: null
    	    }
    	})
    });
}());

