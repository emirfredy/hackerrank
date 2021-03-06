(function () {
'use strict';

angular.module('CubeApp', ['ngFileUpload', 'ui.bootstrap'])
.controller('CubeController', CubeController)
.service('CubeService', CubeService)
.directive('testCases', TestCasesDirective)
.directive('testCase', TestCaseDirective)
.controller('TestCaseDirectiveController', TestCaseDirectiveController)
.constant('ApiBasePath', "/api/problem");

CubeController.$inject = ['$scope', 'CubeService'];
function CubeController($scope, CubeService) {
  var cubeCtrl = this;
  cubeCtrl.testCases = [];
  cubeCtrl.sums = [];
  cubeCtrl.clicked = false;
  cubeCtrl.mode = "Interactive";
  cubeCtrl.create = function() {
	  cubeCtrl.clicked = true;
    if (!cubeCtrl.cubeSize || cubeCtrl > 0) {
    	cubeCtrl.found = [];
    } else {
      var promise = CubeService.create(cubeCtrl.cubeSize);
      promise
        .then(function (result) {
          cubeCtrl.testCases.push({id: result, size: cubeCtrl.cubeSize});
        })
        .catch(function () { });
    }
  }

  cubeCtrl.removeItem = function(index) {
	  cubeCtrl.testCases.splice(index, 1);
  }

  cubeCtrl.upload = function (file) {
      cubeCtrl.sums = [];
      var promise = CubeService.upload(file);
      promise
        .then(function (result) {
          var arrayLength = result.length;
          for (var i = 0; i < arrayLength; i++) {
              cubeCtrl.sums.push({index: i, result: result[i]});
          }
        })
        .catch(function () { });
  };

}

CubeService.$inject = ['$http', 'ApiBasePath', 'Upload'];
function CubeService($http, ApiBasePath, Upload) {
  var service = this;
  service.getSample = function(cubeSize) {
	  return $http({
	      method: "GET",
	      url: ApiBasePath
	    }).then(function (result) {
	    	return result.data;
	    });
  };

  service.create = function(cubeSize) {
	  return $http({
	      method: "POST",
	      url: ApiBasePath,
	      headers: {"Content-Type": "application/json"},
	      data: {cubeSize: "10" }
	    }).then(function (result) {
	    	return result.data;
	    });
  };

  service.solve = function(id) {
	  return $http({
	      method: "DELETE",
	      url: (ApiBasePath + "/" + id)
	    }).then(function (result) {
	    	return result.data;
	    });
  };

  service.addQuery = function(id, query) {
	  return $http({
	      method: "PUT",
	      url: (ApiBasePath + "/" + id + "/query"),
	      headers: {"Content-Type": "application/json"},
	      data: query
	    }).then(function (result) {
	    	return result.data;
	    });
  };

  service.addUpdate = function(id, update) {
	  return $http({
	      method: "PUT",
	      url: (ApiBasePath + "/" + id + "/update"),
	      headers: {"Content-Type": "application/json"},
	      data: update
	    }).then(function (result) {
	    	return result.data;
	    });
  };

  service.upload = function (file) {
      return Upload.upload({
          url: (ApiBasePath + "/batch"),
          data: {file: file}
      }).then(function (result) {
          return result.data;
      }, function (result) {
          console.log('Error status: ' + result.status);
      }, function (evt) {
          var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
          console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
      });
  };
}

function TestCasesDirective() {
  var ddo = {
    templateUrl: 'testCasesList.html',
    scope: {
      items: '<',
      clicked: '<'
    }
  };
  return ddo;
}

TestCaseDirectiveController.$inject = ['$scope', 'CubeService'];
function TestCaseDirectiveController($scope, CubeService) {
  var testCaseCtrl = this;

  testCaseCtrl.queries = [];
  testCaseCtrl.updates = [];
  testCaseCtrl.steps = 0;
  testCaseCtrl.query = {};
  testCaseCtrl.update = {};
  testCaseCtrl.sums = [];
  testCaseCtrl.solved = false;

  testCaseCtrl.addQuery = function() {
    var promise = CubeService.addQuery(testCaseCtrl.testCaseId, testCaseCtrl.query);
    promise
      .then(function (result) {
        testCaseCtrl.query.step = ++testCaseCtrl.steps;
        testCaseCtrl.queries.push(testCaseCtrl.query);
        testCaseCtrl.query = {};
      })
      .catch(function () { });
  };

  testCaseCtrl.addUpdate = function() {
    var promise = CubeService.addUpdate(testCaseCtrl.testCaseId, testCaseCtrl.update);
    promise
      .then(function (result) {
        testCaseCtrl.update.step = ++testCaseCtrl.steps;
        testCaseCtrl.updates.push(testCaseCtrl.update);
        testCaseCtrl.update = {};
      })
      .catch(function () { });
  };

  testCaseCtrl.solve = function() {
	  var promise = CubeService.solve(testCaseCtrl.testCaseId);
      promise
        .then(function (result) {
          var arrayLength = result.length;
          for (var i = 0; i < arrayLength; i++) {
              testCaseCtrl.sums.push({index: i, result: result[i]});
          }
          testCaseCtrl.solved = true;
        })
        .catch(function () { });
  }
}


function TestCaseDirective() {
  var ddo = {
    templateUrl: 'testCase.html',
    scope: {
      testCaseId: '<',
      cubeSize: '<'
    },
    controller: TestCaseDirectiveController,
    controllerAs: 'testCaseCtrl',
    bindToController: true
  };
  return ddo;
}

})();
