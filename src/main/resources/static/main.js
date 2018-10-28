
var app = angular.module("app", []);
 
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
