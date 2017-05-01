var app = angular.module('propular', []);

app.controller('projectController', function($scope, $http) {
	$scope.project = {
		name : "",
		description : ""
	};

	$scope.projectsToDelete = [];

	$scope.getProjects = function() {
		$http({
			method : "GET",
			url : "/project"
		}).then(function mySucces(response) {
			$scope.projects = response.data;
			$scope.$digest();
		}, function myError(response) {
			$scope.error = response.statusText;
		})
	};

	$scope.addForDelete = function(project) {

		var index = $scope.projectsToDelete.indexOf(project);
		if (index > -1) {
			$scope.projectsToDelete.splice(project, 1);
		} else {
			$scope.projectsToDelete.push(project);
		}

	}

	$scope.deleteProjects = function() {
		$http({
			method : "PATCH",
			url : "/project",
			data : $scope.projectsToDelete
		}).then(function mySucces(response) {
			$scope.projects = response.data;
			$scope.$digest();
		}, function myError(response) {
			$scope.error = response.statusText;
		})
	}

	$scope.createProject = function() {
		$http({
			method : "POST",
			url : "/project",
			data : $scope.project
		}).then(function mySucces(response) {
			$scope.projects = response.data;
			$scope.$digest();
		}, function myError(response) {
			$scope.error = response.statusText;
		})
	};

	$scope.viewProjectDetail = function(project) {
		$scope.projectDetail = project;
		/*$http({
			method : "GET",
			url : "/project/"+id
		}).then(function mySucces(response) {
			$scope.success = response.data;
		}, function myError(response) {
			$scope.error = response.statusText;
		})*/
	};

});

app.controller('propertyGroupController', function($scope, $http) {
	$scope.propertyGroup = {
		propertyGroupName : "",
		propertyGroupDescription : "",
		properties : []
	};

	$scope.property = {
		key : "",
		value : ""
	};

	$scope.viewPropertyGroup = function(propertyGroup) {
		$scope.propertyGroup = propertyGroup;
		$scope.property = $scope.propertyGroup.properties;
		//$scope.$apply();
	};
	
	$scope.addProperty = function() {
		var index = $scope.propertyGroup.properties.indexOf($scope.property);
		if (index > -1) {
			alert('Key already exists.');
		} else {
			$scope.propertyGroup.properties.push($scope.property);
		}
		$scope.property = {};
		$scope.$digest();
	};
	
	$scope.savePropertyGroup = function(projectDetail) {
		if(projectDetail.propertyGroup=='' || projectDetail.propertyGroup == undefined || projectDetail.propertyGroup == null) {
			projectDetail.propertyGroup = [];
		}
		/*var index = projectDetail.propertyGroup.indexOf($scope.propertyGroup);
		if (index > -1) {
			alert('Group already exists.');
		} else {*/
			projectDetail.propertyGroup.push($scope.propertyGroup);
		/*}*/
		
		$http({
			method : "POST",
			url : "/project",
			data : projectDetail
		}).then(function mySucces(response) {
			$scope.projects = response.data;
			$scope.$digest();
		}, function myError(response) {
			$scope.error = response.statusText;
		})
		
		$scope.propertyGroup = {};
		$scope.property = {};
		//$scope.$digest();
	};

});