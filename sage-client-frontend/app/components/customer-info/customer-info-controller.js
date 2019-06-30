(function() {

	var CustomerInfoController =  function($http) 
	{
		
		this.customerInfoHeaders = ["id", "reference", "name", "short_name", "country_code_id", "country_code"];
		this.customerView = [];
		this.customerName = "doruk akici";
		this.isTableVisible = false;
		this.isDataTableStarted = false;
			
		
		this.getCustomerViews = () => 
		{
			let vm = this;

			$http({
				method: "GET",
				url: "http://localhost:8080/customerViews",
				params: {
					customerName : vm.customerName
				}
			})
			.then(function(response) {
				vm.customerView = response.data;
				console.log(vm.customerView);
				//if(!vm.isDataTableStarted) vm.startDataTables();
				vm.startDataTables();
				vm.isTableVisible = true;
			});
		}

		this.startDataTables = () =>{
			let vm = this;
			$('#customerInfoTbl').DataTable({
				data: vm.customerView,
				columns: [ 
					{data: "id"}, 
					{data: "reference"}, 
					{data: "name"}, 
					{data: "short_name"}, 
					{data: "country_code_id"}, 
					{data: "country_code"}
				],
				destroy: true,
			})
			vm.isDataTableStarted = true;
		}


		//this.getCustomerViews();
    };

    angular.module('sageClientApp').controller('customerInfoController', ['$http', CustomerInfoController]);
}());