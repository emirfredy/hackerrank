(function () {
'use strict';

angular.module('CubeApp', [])
.controller('CubeController', CubeController)
.service('CubeService', CubeService)
.directive('testCases', TestCasesDirective)
.directive('testCase', TestCaseDirective)
.controller('TestCaseDirectiveController', TestCaseDirectiveController)
.constant('ApiBasePath', "http://localhost:8080/api/problem");

CubeController.$inject = ['$scope', 'CubeService'];
function CubeController($scope, CubeService) {
  var cubeCtrl = this;
  cubeCtrl.testCases = [];
  cubeCtrl.clicked = false;
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
}

CubeService.$inject = ['$http', 'ApiBasePath'];
function CubeService($http, ApiBasePath) {
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
          testCaseCtrl.sums = result;
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
