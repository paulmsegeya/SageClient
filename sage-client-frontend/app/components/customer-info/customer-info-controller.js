(function() {

	var CustomerInfoController =  function($http) 
	{		
		this.customerInfoAPIFieldNames = [];
		this.fieldNames = {};
		this.searchType = "NAME";
		this.customerInfoData = {};
		this.customerName = undefined;
		this.telSearchInput = "";
		this.isTableVisible = false;
		this.isCustomerInfoLoading = false;
		this.connectionAPIDetails = {connectedToSage: false};
		this.connectionInterfaceDetails = {sageInterfaceConnected: false};
		this.isConnectionStatusVisible = false;
		this.errorMessages = {};
		this.signedDateObj = null;
		this.alert = alert.bind(window);
		this.customerContactName = {
			firstName: "",
			middleName: "",
			lastName: ""
		}
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
				vm.splitCustomerContactName(vm.customerContactName);
				vm.signedDateObj = new Date(vm.customerInfoData.signedDate);
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


		this.splitCustomerContactName = (customerContactNames) =>
		{
			let concatenatedName = this.customerInfoData.customerName;
			let nameTitlePrefixPos = concatenatedName.indexOf(".");
			let nonPreFixedName = concatenatedName;
			if(nameTitlePrefixPos != -1){
				nonPreFixedName = concatenatedName.substr( nameTitlePrefixPos+2, concatenatedName.length-1 );
			}
			let splitNames = nonPreFixedName.split(" ");

			if(splitNames.length > 0) customerContactNames.firstName = splitNames[0];

			
			if(splitNames.length > 2){
				let lastName = splitNames[2];
				for(let x = 3; x < splitNames.length; x++){
					lastName += ("-" + splitNames[x]);
				}				
				customerContactNames.lastName = lastName;
				customerContactNames.middleName = splitNames[1];
			}
			else customerContactNames.lastName = splitNames[1]; //only two names => second name is stored as lastName
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


		this.getNewNote = () =>{
			return Object.assign({}, this.newNote);
		}


		this.saveNotes = () =>
		{
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


		this.saveEmail = (emailKey) =>
		{
			let vm = this;
			$http({
				method: "POST",
				url: "http://localhost:8080/add/email",
				data: vm.customerInfoData[emailKey]
			})
			.then(function(response) {
				let isSuccess = true;
				if(response.data.success){
					vm.clearError("Emails");
					alert("Email successfully updated!");	
				}		
				else vm.setError("Emails", "Error updating emails!");
			}
			,function(response){
				vm.setError("Emails", "Error updating emails!");
			});
		}


		this.saveTel = (telKey) =>
		{
			let vm = this;
			$http({
				method: "POST",
				url: "http://localhost:8080/add/tel",
				data: vm.customerInfoData[telKey]
			})
			.then(function(response) {
				let isSuccess = true;
				if(response.data.success){
					vm.clearError("Tel");
					alert("Telephone successfully updated!");	
				}		
				else vm.setError("Tel", "Error updating telephone!");
			}
			,function(response){
				vm.setError("Tel", "Error updating telephone!");
			});
		}

		this.saveSignedDate = (signedDateKey) =>
		{
			let vm = this;
			console.log("signedDate orig", this.customerInfoData.signedDate);			
			console.log("signedDate edit", this.signedDateObj);
			$http({
				method: "PUT",
				url: "http://localhost:8080/update/signed_date",
				params: {
					customerID: vm.customerInfoData.customerID,
					signedDate: moment(vm.customerInfoData.signedDate).format('YYYY-MM-DD')
				}
			})
			.then(function(response) {
				if(response.data.success){
					vm.clearError("signedDate");
					alert("Signed date successfully updated!");	
				}		
				else vm.setError("signedDate", "Error updating signed date!");
			}
			,function(response){
				vm.setError("signedDate", "Error updating signed date!");
			})
		}


		this.saveName = () =>
		{
			this.customerInfoData.customerName = this.customerContactName.firstName + " " + this.customerContactName.middleName + " " + this.customerContactName.lastName;

			let vm = this;
			$http({
				method: "PUT",
				url: "http://localhost:8080/update/customer_name",
				params: { customerID: vm.customerInfoData.customerContactID },
				data: vm.customerContactName
			})
			.then(function(response) {
				if(response.data.success){
					vm.clearError("nameEdit");
					alert("Customer Name successfully updated!");	
				}		
				else vm.setError("nameEdit", "Error updating customer name!");
			}
			,function(response){
				vm.setError("nameEdit", "Error updating customer name!");
			});
		}


		this.getCustomerNamesByTel = (tel) =>{
			let vm = this;
			return $http({
				method: "GET",
				url: "http://localhost:8080/search/tel/names",
				params: {
					tel : tel
				}
			})
			.then(function(response) {
				vm.clearError("nameTelSearch"); 
				return response.data;
			}
			,function(response){
				vm.setError("nameTelSearch", "Error finding customers for given telephone number");
			});
		}


		this.getCustomerNamesByEmail = (email) =>{
			let vm = this;
			return $http({
				method: "GET",
				url: "http://localhost:8080/search/email/names",
				params: {
					email : email
				}
			})
			.then(function(response) {
				vm.clearError("nameEmailSearch"); 
				return response.data;
			}
			,function(response){
				vm.setError("nameEmailSearch", "Error finding customers for given email address.");
			});
		}

		this.toggleConnectionStatusVisiblity = () =>{
			this.isConnectionStatusVisible = !this.isConnectionStatusVisible;
		}

		this.formatDate = (date) =>{
			return moment(date).format('DD-MM-YYYY');
		}

		this.parseDate = (date) =>{
			return moment(date);
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