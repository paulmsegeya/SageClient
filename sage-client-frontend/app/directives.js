sageClientApp.directive("customerInfo", function(){
	return{
        retrict: "E",
        scope: {
            data: '='
        },
		templateUrl: "app/components/customer-info/directives/customerInfo.html"
	};
});

sageClientApp.directive("customerInfoRow", function(){
	return{
        retrict: "A",
        scope: {
            data: '='
        },
		templateUrl: "app/components/customer-info/directives/customerInfoRow.html"
	};
});

sageClientApp.directive("customerInfoCell", function(){
	return{
        retrict: "E",
        scope: {
            header: '@',
            value: '=',
            key: '@',
            editable: '='
        },
		templateUrl: "app/components/customer-info/directives/customerInfoCell.html"
	};
});


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
            header: "@",
            listData: "=",
            saveDataFn: "=",
            listDataKey: "@"
        },
		templateUrl: "app/components/customer-info/directives/listData.html"
	};
});

sageClientApp.directive("editListData", function(){
	return{
        retrict: "A",
        scope: {
            header: '=',
            listData: '=',
            saveDataFn: "=",
            listDataKey: "="
        },

		templateUrl: "app/components/customer-info/directives/editListData.html"
	};
});

sageClientApp.directive("editNotes", function(){
	return{
        retrict: "A",
        scope: {
            memos: '=',
            saveNotesFn: "=",
            newNote: "="
        },

		templateUrl: "app/components/customer-info/directives/editNotes.html"
	};
});


sageClientApp.directive("editField", function(){
	return{
        retrict: "A",
        scope: {
            header: '=',
            saveFieldFn: "=",
            value: "=",
            key: '='
        },

		templateUrl: "app/components/customer-info/directives/editField.html"
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



