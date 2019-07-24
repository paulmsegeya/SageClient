sageClientApp.directive("customerValueRow", function(){
	return{
        retrict: "E",
        scope: {
            values: '=',
            colSpan: "@"
        },
		templateUrl: "app/components/customer-info/directives/valueRow.html"
	};
});

sageClientApp.directive("customerHeaderRow", function(){
	return{
        retrict: "A",
        scope: {
            headers: '=',
            colSpan: "@"
        },
		templateUrl: "app/components/customer-info/directives/headerRow.html"
	};
});

sageClientApp.directive("customerListData", function(){
	return{
        retrict: "A",
        scope: {
            header: '@',
            listData: "=",
            saveDataFn: "=",
            listDataKey: "@"
        },
		templateUrl: "app/components/customer-info/directives/listData.html"
	};
});

sageClientApp.directive("connectionInfo", function(){
	return{
        retrict: "E",
        scope: {
            apiDetails: '=',
            interfaceDetails: '='
        },
		templateUrl: "app/components/customer-info/directives/connectionInfo.html"
	};
});


sageClientApp.directive("editListData", function(){
	return{
        retrict: "E",
        scope: {
            listData: '=',
            listDataType: '=',
            saveDataFn: "=",
            listDataKey: "="
        },

		templateUrl: "app/components/customer-info/directives/editListData.html"
	};
});

