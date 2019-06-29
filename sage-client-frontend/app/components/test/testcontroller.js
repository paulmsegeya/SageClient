(function() {

    var TestController =  function($http) {
        
    	let data = "test data";
			
		
		this.getCustomerViews = () => 
		{
			let vm = this;
			$http.get("http://localhost:8080/customerViews")
			.then(function(response) {
				vm.data = response.data;
				console.log(response);
			});
		}


		console.log("Test Controller");
		this.getCustomerViews();
    };

    angular.module('sageClientApp').controller('testController', ['$http', TestController]);
}());