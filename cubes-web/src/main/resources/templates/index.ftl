<!doctype html>
<html lang="en">
  <head>
    <title>Cube Summation Solver</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
  </head>
<body ng-app="CubeApp">
<div>
  <div class="container" ng-controller="CubeController as cubeCtrl">
    <h1>Cube Summation Solver</h1>
    <form class="form-inline">
      <div class="form-group">
        <label for="cubeSize">Cube Size</label>
        <input type="number" min="1" max="100" placeholder="Enter cube size" class="form-control" ng-model="cubeCtrl.cubeSize" id="cubeSize">
        <button class="btn btn-primary" type="submit" ng-click="cubeCtrl.create()">Create test case</button>
      </div>
    </form>
    <test-cases items="cubeCtrl.testCases">
    </div>
  </div>
</div>




  <script src="js/angular.min.js"></script>
  <script src="js/app.js"></script>
</body>
</html>
