sageClientApp.directive("nestedTable", function(){
	return{
        retrict: "E",
        scope: {
            displayName: '@',
            value: '@'
        },
		templateUrl: "app/components/customer-info/directives/nestedInfoTable.html"
	};
});
