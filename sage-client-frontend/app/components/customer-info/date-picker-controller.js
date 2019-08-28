(function() {

	var DatePickerController =  function() 
	{	
        this.data = "some tesxt";


        this.today = function() {
            this.dt = new Date();
          };
          this.today();
        
          this.clear = function() {
            this.dt = null;
          };
        
          this.inlineOptions = {
            customClass: getDayClass,
            minDate: new Date(),
            showWeeks: true
          };
        
          this.dateOptions = {
            dateDisabled: disabled,
            formatYear: 'yy',
            maxDate: new Date(2020, 5, 22),
            minDate: new Date(),
            startingDay: 1
          };

 

            
    }


    angular.module('sageClientApp').controller('datePickerController', [DatePickerController]);
}());