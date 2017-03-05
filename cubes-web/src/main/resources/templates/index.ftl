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

      <label>
        <input type="radio" ng-model="cubeCtrl.mode" value="Interactive">
        Interactive
      </label><br/>
      <label>
        <input type="radio" ng-model="cubeCtrl.mode" value="Batch">
        Batch
      </label><br/>

      <div name="interactive" ng-if="cubeCtrl.mode == 'Interactive'">
        <form class="form-inline" name="testCaseForm">
          <h2>Interactive mode</h2>
          <div class="form-group" ng-class="{'has-error': testCaseForm.cubeSize.$invalid}">
            <label for="cubeSize">Cube Size</label>
            <input type="number" min="1" max="100" placeholder="n" class="form-control" ng-model="cubeCtrl.cubeSize" name="cubeSize" required>
            <button class="btn btn-primary" type="submit" ng-disabled="testCaseForm.$invalid" ng-click="cubeCtrl.create()">Create test case</button>
          </div>
        </form>
        <test-cases items="cubeCtrl.testCases"></test-cases>
      </div>
      <div name="batch" ng-if="cubeCtrl.mode == 'Batch'">
        <form name="batchForm">
          <h2>Batch mode</h2>
          <div class="btn btn-primary" ngf-select="cubeCtrl.upload($file)">Upload test suite file</div>
          <table class="row table table-striped" ng-if="cubeCtrl.sums.length > 0">
              <thead>
                  <tr>
                      <th>Query result</th>
                  </tr>
              </thead>
              <tbody>
                  <tr ng-repeat="sum in cubeCtrl.sums">
                      <td>{{sum.result}}</td>
                  </tr>
              </tbody>
          </table>
        </form>
      </div>
    </div>
  </div>
  <script src="js/angular.min.js"></script>
  <script src="js/app.js"></script>
  <script src="js/ng-file-upload-shim.min.js"></script> <!-- for no html5 browsers support -->
  <script src="js/ng-file-upload.min.js"></script>
  <script src="js/ui-bootstrap-tpls-2.5.0.min.js"></script>
</body>
</html>
