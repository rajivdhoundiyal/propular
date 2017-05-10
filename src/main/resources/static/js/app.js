var app = angular.module('propular', []);

app.filter('formatProjectId' , formatProjectId)

function formatProjectId() {

 return function(input,prefix) {
     if(!input) return;

      if(input.length > 11)
         return prefix + '-' + input.toUpperCase().substr(10,25);
     else
         return prefix + '-' + input;

 }


}

app.factory('notificationService', function($rootScope) {
    var notificationService = {
    	message: '',
    	status: '',
    	type: '',
    	show: false
    };

    notificationService.prepForBroadcast = function(message, status, type, show) {
        this.message = message;
        this.status=status;
        this.type=type;
        this.show=show;
        this.broadcastItem();
    };

    notificationService.broadcastItem = function() {
        $rootScope.$broadcast('handleBroadcast');
    };

    return notificationService;
});

app.directive('notification', ['$interval', function ($interval) {
    return {
        restrict: 'E',
        replace: true,
        controller: function($scope, $attrs, notificationService) {
        	$scope.alertData = [];
        		$scope.$on('handleBroadcast', function() {
	        		var index = $scope.alertData.indexOf(notificationService);
	        		if (index > -1) {
	        			$scope.alertData.splice(notificationService, 1);
	        		} else {
	        			$scope.alertData.push(notificationService);
	        		}
            });
        },
        template:"<div class='container-fluid'><div ng-repeat='alert in alertData'><div class='alert alert-{{alert.type}} alert-dismissible fade show' ng-show='alert.show' " +
        		"role='alert' data-notification='{{alert.status}}'><button type='button' class='close' data-dismiss='alert' " +
        		"aria-label='Close'><span aria-hidden='true'>&times;</span></button>{{alert.message}}</div></div></div>",
        scope:{
          alertData:"="
        },
        link: function($scope, element, attrs) {
        	$interval(function(){
              $(element).find('.alert').fadeOut(2000,function(){
            	this.remove();  
            	$scope.alertData = [];
              })
            }, 3000);
          }
    };
}]); 

app.controller('projectController', function($scope, $http, notificationService) {
	
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
			//$scope.$digest();
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
	
	$scope.addAllProjectsDelete = function(allProjectDelCheck) {
		angular.forEach($scope.projects, function (item) {
            item.checked = allProjectDelCheck;
            $scope.addForDelete(item);
        });
		
		if(!allProjectDelCheck) {
			$scope.projectsToDelete = [];
		}
	}

	$scope.deleteProjects = function() {
		$http({
			method : "PATCH",
			url : "/project",
			data : $scope.projectsToDelete
		}).then(function mySucces(response) {
			$scope.projects = response.data;
			//$scope.$digest();
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
			//$scope.$digest();
		}, function myError(response) {
			console.log(response);
			$scope.error = response.statusText;
			notificationService.prepForBroadcast(response.data.errors[0].defaultMessage,"show", "danger", true);
			/*$scope.notification.status = "show";
			$scope.notification.message = response.data.errors[0].defaultMessage;
			$scope.notification.type = "danger";*/
		})
	};

	$scope.viewProjectDetail = function(project) {
		$scope.projectDetail = project;
		$http({
			method : "GET",
			url : "/"+project.projectId+"/propertygroup"
		}).then(function mySucces(response) {
			$scope.propertyGroup = response.data;
		}, function myError(response) {
			$scope.error = response.statusText;
		})
	};

});

app.controller('propertyGroupController', function($scope, $http, notificationService) {
	$scope.propertyGroup = {
		propertyGroupName : "",
		propertyGroupDescription : "",
		properties : []
	};

	$scope.property = {
		key : "",
		value : ""
	};

	$scope.groupsToDelete = [];
	
	$scope.propsToDelete = [];
	
	$scope.viewPropertyGroup = function(propertyGroup) {
		$scope.propertyGroup = propertyGroup;
		$scope.properties = $scope.propertyGroup.properties;
		//$scope.$apply();
	};

	$scope.addGroupForDelete = function(group) {
		var index = $scope.groupsToDelete.indexOf(group);
		if (index > -1) {
			$scope.groupsToDelete.splice(group, 1);
		} else {
			$scope.groupsToDelete.push(group);
		}
	}
	
	$scope.addAllGroupsDelete = function(project, allGroupsDelCheck) {
		angular.forEach(project.propertyGroup, function (item) {
            item.checked = allGroupsDelCheck;
            $scope.addGroupForDelete(item);
        });
		
		if(!allGroupsDelCheck) {
			$scope.groupsToDelete = [];
		}
	}
	
	$scope.addPropForDelete = function(prop) {
		var index = $scope.propsToDelete.indexOf(prop);
		if (index > -1) {
			$scope.propsToDelete.splice(prop, 1);
		} else {
			$scope.propsToDelete.push(prop);
		}
	}
	
	$scope.addAllPropertyDelete = function(propertyGroup, allPropDelCheck) {
		angular.forEach(propertyGroup.properties, function (item) {
            item.checked = allPropDelCheck;
            $scope.addPropForDelete(item);
        });
		
		if(!allPropDelCheck) {
			$scope.propsToDelete = [];
		}
	}
	
	$scope.addProperty = function() {
		var index = $scope.propertyGroup.properties.indexOf($scope.property);
		if (index > -1) {
			alert('Key already exists.');
		} else {
			$scope.propertyGroup.properties.push($scope.property);
		}
		$scope.property = {};
		//$scope.$digest();
	};

	$scope.savePropertyGroup = function(projectDetail) {
		if (projectDetail.propertyGroup == ''
				|| projectDetail.propertyGroup == undefined
				|| projectDetail.propertyGroup == null) {
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
			//$scope.$digest();
		}, function myError(response) {
			$scope.error = response.statusText;
		})

		$scope.propertyGroup = {};
		$scope.property = {};
		//$scope.$digest();
	};
	
	$scope.deleteProperties = function(event) {
		
		if($scope.propsToDelete == null || ($scope.propsToDelete.length == 0)) {
			notificationService.prepForBroadcast("Please choose the properties to delete.","show", "danger", true);
			event.stopPropagation();
			return;
		}
		
		angular.forEach($scope.propsToDelete, function(item) {
			$scope.properties.splice(item, 1);
		});
		
	}

});

app.controller('environmentController', function($scope, $http, notificationService) {
	
	$scope.environmentAriaFlag = true;
	
	$scope.environment = {
		name : "",
		description : "",
	};

	$scope.environmentsToDelete = [];
	
	$scope.getEnvironments = function() {
		$http({
			method : "GET",
			url : "/environment"
		}).then(function mySucces(response) {
			$scope.environments = response.data;
		}, function myError(response) {
			$scope.error = response.statusText;
		})
	};
	
	$scope.addEnvironmentForDelete = function(environment) {
		var index = $scope.environmentsToDelete.indexOf(environment);
		if (index > -1) {
			$scope.environmentsToDelete.splice(environment, 1);
		} else {
			$scope.environmentsToDelete.push(environment);
		}
	}
	
	$scope.addAllEnvironmentForDelete = function(allEnvDelCheck) {
		angular.forEach($scope.environments, function (item) {
            item.checked = allEnvDelCheck;
            $scope.addEnvironmentForDelete(item);
        });
		
		if(!allEnvDelCheck) {
			$scope.addEnvironmentForDelete = [];
		}
	}
	
	$scope.saveEnvironment = function(event) {
		var regexp = '^[a-zA-Z]*$';
		if($scope.environment.name == null || $scope.environment.name == '') {
			notificationService.prepForBroadcast("Please correct environment name, it should not be empty or have special characters.","show", "danger", true);
			event.stopPropagation();
			return;
		}
		
		$scope.environmentAriaFlag = true;
		
		$http({
			method : "POST",
			url : "/environment",
			data : $scope.environment
		}).then(function mySucces(response) {
			$scope.environments = response.data;
		}, function myError(response) {
			$scope.error = response.statusText;
		})

	};

	$scope.deleteEnvironments = function(event) {
		
		if($scope.environmentsToDelete == null || $scope.environmentsToDelete.length <= 0) {
			notificationService.prepForBroadcast("Please select environment to delete first.","show", "danger", true);
			event.stopPropagation();
		}
		
		$http({
			method : "PATCH",
			url : "/environment",
			data : $scope.environmentsToDelete
		}).then(function mySucces(response) {
			$scope.environments = response.data;
		}, function myError(response) {
			$scope.error = response.statusText;
		})

	};
});

