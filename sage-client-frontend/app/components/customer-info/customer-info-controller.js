(function() {

	var CustomerInfoController =  function($http) 
	{		
		this.customerInfoAPIFieldNames = [];
		this.fieldNames = {}
		this.customerInfoData = {};
		this.customerName = undefined;
		this.isTableVisible = false;
		this.isCustomerInfoLoading = false;
		this.connectionAPIDetails = {connectedToSage: false};
		this.connectionInterfaceDetails = {sageInterfaceConnected: false};
		this.errorMessages = {};

		
		this.testSageAPIConnection = () => 
		{
			let vm = this;
			$http({
				method: "GET",
				url: "http://localhost:8080/test_sageAPI_connection"
			})
			.then(function(response) {
				vm.connectionAPIDetails = response.data;
				if(vm.connectionAPIDetails.connectedToSage == false) vm.setError("testSageAPI", "Could not connect to Sage API!");
				else vm.clearError("testSageAPI"); 
				console.log(vm.connectionAPIDetails);
			}
			,function(response){
				vm.setError("testSageAPI", "Could not connect to Sage API!");
			});
		}


		this.testSageInterfaceConnection = () => 
		{
			let vm = this;
			$http({
				method: "GET",
				url: "http://localhost:8080/test_sage_interface_connection"
			})
			.then(function(response) {
				vm.connectionInterfaceDetails = response.data;
				vm.clearError("testSageInterface"); 
			}
			,function(response){
				vm.setError("testSageInterface", "Could not connect to Sage Interface!");
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
				vm.customerInfoData = response.data;
				console.log(vm.customerInfoData);
				vm.isCustomerInfoLoading = false;
				vm.isTableVisible = true;
				vm.clearError("customerInfoData"); 
			})
			,function(response){
				vm.setError("customerInfoData", "Could not retrieve customer data from Sage!");
			};
		}

		this.getCustomerInfoFields = () => 
		{
			let vm = this;
			$http({
				method: "GET",
				url: "http://localhost:8080/customer_info/fields"
			})
			.then(function(response) {
				vm.fieldNames = response.data.fieldNames;
				vm.customerInfoAPIFieldNames = response.data.fieldAPINames;
			});
		}

		this.getTopCustomerNames = (name) =>{
			let vm = this;
			return $http({
				method: "GET",
				url: "http://localhost:8080/customers/names",
				params: {
					customerNamePart : name
				}
			})
			.then(function(response) {
				vm.clearError("topCustomerNames"); 
				return response.data;
			}
			,function(response){
				vm.setError("topCustomerNames", "Could not retrieve customer search names!");
			});
		}


		this.saveListData = (listDataKey) =>{
			let listData = this.customerInfoData.listData;
			listData[listDataKey].shouldBeUpdated = true;
			console.log(listData[listDataKey]);
		
			let vm = this;
			$http({
				method: "POST",
				url: "http://localhost:8080/customer_info/add_list_data?customerID=" + listData.customerID,
				data: listData[listDataKey]
			})
			.then(function(response) {
				console.log(response);
			});
		}


		this.setError = (errorSrc, message) =>{
			this.errorMessages[errorSrc] = message;
		}

		this.clearError = (errorSrc) =>{
			if(this.errorMessages.hasOwnProperty(errorSrc) ) delete this.errorMessages[errorSrc];
		}

		this.isShowErrorMessages = () =>{
			return Object.keys(this.errorMessages).length > 0 ? true : false;
		}


		this.testSageAPIConnection();
		this.testSageInterfaceConnection();
		this.getCustomerInfoFields();
    };

    angular.module('sageClientApp').controller('customerInfoController', ['$http', CustomerInfoController]);
}());