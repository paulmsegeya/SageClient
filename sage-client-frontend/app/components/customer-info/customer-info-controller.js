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
		this.newNote = {
			customerId: this.customerInfoData.customerID,
			id: 0,
			note: "",
			shouldBeDeleted: false
		};

		
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
				vm.newNote.customerId = vm.customerInfoData.customerID;
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


		this.saveListData = (listDataKey) =>
		{
			let listData = this.customerInfoData.listData;
			listData[listDataKey].shouldBeUpdated = true;
					
			let vm = this;
			$http({
				method: "POST",
				url: "http://localhost:8080/add/list_data",
				data: {
					listData: listData[listDataKey],
					customerID: listData.customerID
				}
			})
			.then(function(response) {
				if(response.data.success){
					vm.clearError("listData");
					alert("Data successfully updated!");
				} 
				else vm.setError("listData", "Error updating data!")
			}
			,function(response){
				vm.setError("listData", "Error updating data!");
			});
		}


		this.saveNotes = () =>
		{
			console.log(this.customerInfoData.memos);
			let vm = this;
			$http({
				method: "POST",
				url: "http://localhost:8080/add/notes",
				data: vm.customerInfoData.memos
			})
			.then(function(response) {
				let isSuccess = true;
				for(let serverResponse of response.data){
					if(serverResponse.success == false){
						isSuccess = false;
						break;
					}						
				}
				if(isSuccess){
					vm.clearError("Memos");
					alert("Memos successfully updated!");	
				}		
				else vm.setError("Memos", "Error updating memos!");
			}
			,function(response){
				vm.setError("Memos", "Error updating memos!");
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