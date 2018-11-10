	
var app = angular.module('myApp', ['ui.grid','ui.grid.pagination']);



	app.service('StudentServicePagin',['$http', function ($http) {
	
	function getStudents(pageNumber,size) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'student/get?page='+pageNumber+'&size='+size
        });
    }
	
    return {
    	getStudents:getStudents
    };
	
}]);
	







// Controller Part
app.controller("StudentController", function($scope, $http) {
 
 
    $scope.students = [];
    $scope.studentForm = {
        stuId: 1,
        stuName: "",
        stuSex: "",
        address: ""
    };
 
    // Now load the data from server
    _refreshStudentData();
 
    // HTTP POST/PUT methods for add/edit student  
    // Call: http://localhost:8080/student
    $scope.submitStudent = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.studentForm.stuId == -1) {
            method = "POST";
            url = '/student';
        } else {
          alert('update'+$scope.studentForm.stuId);
            method = "PUT";
            url = '/student/'+$scope.studentForm.stuId;
          
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.studentForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createStudent = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete student by Id
    // Call: http://localhost:8080/student/{stuId}
    $scope.deleteStudent= function(student) {
        $http({
            method: 'DELETE',
            url: '/student/' + student.stuId
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editStudent = function(student) {
        $scope.studentForm.stuId = student.stuId;
        $scope.studentForm.stuName = student.stuName;
        $scope.studentForm.stuSex = student.stuSex;
        $scope.studentForm.Address = student.address;
       
    };
 
    // Private Method  
    // HTTP GET- get all students collection
    // Call: http://localhost:8080/students
    function _refreshStudentData() {
        $http({
            method: 'GET',
            url: '/students'
        }).then(
            function(res) { // success
                $scope.students = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
        _refreshStudentData();
        _clearFormData();
    }
 
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
 
    // Clear the form
    function _clearFormData() {
        $scope.studentForm.stuId = -1;
        $scope.studentForm.stuName = "";
        $scope.studentForm.stuSex = "";
        $scope.studentForm.address = "";
    };
});







 app.controller('StudentCtrl', ['$scope','StudentServicePagin', function ($scope, StudentServicePagin) {
        var paginationOptions = {
            pageNumber: 1,
            pageSize: 5,
        sort: null
        };
 
    StudentServicePagin.getStudents(
      paginationOptions.pageNumber,
      paginationOptions.pageSize).success(function(data){
        $scope.gridOptions.data = data.content;
        $scope.gridOptions.totalItems = data.totalElements;
      });
 
    $scope.gridOptions = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: paginationOptions.pageSize,
        enableColumnMenus:false,
    useExternalPagination: true,
    
        columnDefs: [
           { name: 'Id' , field: 'stuId'},
          
           { name: 'Name' , field: 'stuName'},
           { name: 'Sex' , field: 'stuSex'},
           { name: 'Address', field: 'address' },
             {name:'Edit',cellTemplate:'<div><a ng-click="editStudent(student)" class="edit-button">Edit</a></div>'}
             
            
        ],
        onRegisterApi: function(gridApi) {
           $scope.gridApi = gridApi;
           
           gridApi.pagination.on.paginationChanged(
             $scope, 
             function (newPage, pageSize) {
               paginationOptions.pageNumber = newPage;
               paginationOptions.pageSize = pageSize;
               StudentServicePagin.getStudents(newPage,pageSize)
                 .success(function(data){
                   $scope.gridOptions.data = data.content;
                   $scope.gridOptions.totalItems = data.totalElements;
                 });
            });
        }
    };
}]);
	
	

	