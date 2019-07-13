(function() {

	var CustomerInfoController =  function($http) 
	{
		
		this.customerInfoHeaders = []
		this.customerInfoAPIFieldNames = [];
		this.customerInfoData = {};
		this.customerName = undefined;
		this.isTableVisible = false;
		this.isDataTableStarted = false;

		
		this.getCustomerInfoData = () => 
		{
			let vm = this;
			$http({
				method: "GET",
				url: "http://192.168.0.19:8080/customer_info",
				params: {
					customerName : vm.customerName
				}
			})
			.then(function(response) {
				vm.customerInfoData = [response.data];
				vm.startDataTables();
				vm.isTableVisible = true;
			});
		}

		this.getCustomerInfoFields = () => 
		{
			let vm = this;
			$http({
				method: "GET",
				url: "http://192.168.0.19:8080/customer_info/fields"
			})
			.then(function(response) {
				vm.customerInfoHeaders = response.data.fieldDisplayNames;
				vm.customerInfoAPIFieldNames = response.data.fieldAPINames;
			});
		}

		this.getTopCustomerNames = (name) =>{
			return $http({
				method: "GET",
				url: "http://192.168.0.19:8080/customers/names",
				params: {
					customerNamePart : name
				}
			})
			.then(function(response) {
				console.log(response.data);
				return response.data;
			});
		}


		this.generateDataTablesAPIColumnNames = () =>{
			let dataTableColumns = [];
			for(let columnApiName of this.customerInfoAPIFieldNames)
			{
				dataTableColumns.push({
					data: columnApiName
				})
			}
			return dataTableColumns;
		}


		this.startDataTables = () =>{
			let vm = this;
			$('#customerInfoTbl').DataTable({
				data: vm.customerInfoData,
				columns: vm.generateDataTablesAPIColumnNames(),
				destroy: true,
			})
			vm.isDataTableStarted = true;
		}

		this.getCustomerInfoFields();
    };

    angular.module('sageClientApp').controller('customerInfoController', ['$http', CustomerInfoController]);
}());