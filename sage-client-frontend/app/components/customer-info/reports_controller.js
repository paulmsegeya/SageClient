(function() {

	var ReportsController =  function($http, $state, $stateParams) 
	{	
		this.dateFrom = new Date();
		this.dateTo = new Date();
		this.reports = [];
		this.isReportLoading = false;
		
		this.setReportsFromState = () =>{
			if($stateParams.reportData !== undefined) this.reports = $stateParams.reportData;
		}

		this.getReport = () =>
		{
			let vm = this;
			$http({
				method: "GET",
				url: "http://localhost:8080/report",
				params: {
					date1 : moment(this.dateFrom).format('YYYY-MM-DD'),
					date2: moment(this.dateTo).format('YYYY-MM-DD')
				}
			})
			.then(function(response) {
				this.isReportLoading = false;
				console.log(response);
				vm.reports = response.data;
				$state.go('reports', {reportData: response.data});
			}
			,function(response){
				this.isReportLoading = false;
				console.log("error", response);
			});
			this.isReportLoading = true;
		}


		this.printReport = () =>{			
			window.print();
		}

		this.formatDate = (date) =>{
			return moment(date).format('DD-MM-YYYY');
		}
 

		this.setReportsFromState();
  }


    angular.module('sageClientApp').controller('reportsController', ['$http', '$state', '$stateParams', ReportsController]);
}());