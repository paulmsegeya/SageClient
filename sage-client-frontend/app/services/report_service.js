sageClientApp.factory('reportsService', function() {
    
    let dateFrom = new Date();//undefined;
    let dateTo = "myDateto";//undefined;

    let getDateFrom = () =>{
        return dateFrom;
    }

    let setDateFrom = (val) =>{
        dateFrom = val;
    }

    let getDateTo = () =>{
        return dateTo;
    }

    let setDateTo = (val) =>{
        dateTo = val;
    }

    return {
        dateFrom: {
            get: getDateFrom,
            set: setDateFrom,
        },
        dateTo: {
            get: getDateTo,
            set: setDateTo,
        }
    }

});