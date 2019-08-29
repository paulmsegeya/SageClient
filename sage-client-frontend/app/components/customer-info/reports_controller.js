(function() {

	var ReportsController =  function(reportsService) 
	{	

    this.data = reportsService.dateFrom;
      

    this.getReport = () =>{
      
			let vm = this;
			return $http({
				method: "GET",
				url: "http://localhost:8080/report",
				params: {
          date1 : myVar,
          date2: myVar2
				}
			})
			.then(function(response) {
				console.log(respose);
			}
			,function(response){
			  console.log("error", response);
			});
		}
 

            
  }


    angular.module('sageClientApp').controller('reportsController', ['reportsService', ReportsController]);
}());