(function() {

	var CustomerInfoController =  function($http) 
	{		
		this.customerInfoAPIFieldNames = [];
		this.fieldNames = {}
		this.customerInfoData = {};
		this.customerName = undefined;
		this.isTableVisible = false;
		this.isCustomerInfoLoading = false;
		this.connectionDetails = {connectedToSage: false};

		
		this.testSageConnection = () => 
		{
			let vm = this;
			$http({
				method: "GET",
				url: "http://localhost:8080/test_sage_connection"
			})
			.then(function(response) {
				console.log(response.data);
				vm.connectionDetails = response.data; 
			});
		}


		this.getCustomerInfoData = () => 
		{
			this.isCustomerInfoLoading = true;
			let vm = this;
			$http({
				method: "GET",
				url: "http://localhost:8080/customer_info",
				params: {
					customerName : vm.customerName
				}
			})
			.then(function(response) {
				console.log(response.data);
				vm.customerInfoData = response.data;
				vm.isCustomerInfoLoading = false;
				vm.isTableVisible = true;
			});
		}

		this.getCustomerInfoFields = () => 
		{
			let vm = this;
			$http({
				method: "GET",
				url: "http://localhost:8080/customer_info/fields"
			})
			.then(function(response) {
				console.log(response.data);
				vm.fieldNames = response.data.fieldNames;
				vm.customerInfoAPIFieldNames = response.data.fieldAPINames;
			});
		}

		this.getTopCustomerNames = (name) =>{
			return $http({
				method: "GET",
				url: "http://localhost:8080/customers/names",
				params: {
					customerNamePart : name
				}
			})
			.then(function(response) {
				console.log(response.data);
				return response.data;
			});
		}


		this.testSageConnection();
		this.getCustomerInfoFields();
    };

    angular.module('sageClientApp').controller('customerInfoController', ['$http', CustomerInfoController]);
}());