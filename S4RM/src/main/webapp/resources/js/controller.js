/**
 * @ S4RM System
 * @File Name: controller.js
 * @Description: Angular JS Controllers
 * 
 * @author Meilan Jiang
 * @since 2016.06.17
 * @version 1.0
 * 
 * Copyright(c) 2016 by CILAB All right reserved.
 */


/**
 * Tag Controller
 */
naempApp.controller('TagListCtrl',['$scope', 'TagCollectionService', 'TagService', '$location',
		function($scope, TagCollectionService, TagService, $location) {
	
		$scope.cancel = function() {
			$scope.search = "";
		}
		// callback for ng-click 'editTag':
		$scope.editTag = function(tagID) {
			$location.path('/tag-detail/' + tagID);
		};

		// callback for ng-click 'deleteTag':
		$scope.deleteTag = function(tagID) {
			TagService.remove({id:tagID});
			$location.path('/tag-list');
		};
		// callback for ng-click 'createTag':
		$scope.createTag = function() {
			$location.path('/tag-creation');
		};
		$scope.tags = TagCollectionService.find();

		// panination
		$scope.currentPage = 1, $scope.numPerPage = 10,
		$scope.maxSize = 10;
}]);

naempApp.controller('TagDetailCtrl', [ '$scope', '$routeParams', 'TagService', '$location',
		function($scope, $routeParams, TagService, $location) {
			
		// callback for ng-click 'updateTag':
		$scope.updateTag = function(tag) {
			TagService.update({id:tag.tagID}, tag);
			$location.path('/tag-list');
		};
		// callback for ng-click 'cancel':
		$scope.cancel = function() {
			window.history.back();
		};
		$scope.tag = TagService.find({
			id : $routeParams.id
		});
}]);

naempApp.controller('TagCreationCtrl', [ '$scope', 'NewTagService', '$location', 
        function($scope, NewTagService, $location) {
		
		// callback for ng-click 'createNewTag':
		$scope.createTag = function(tag) {
			NewTagService.create(tag);
			$location.path('/tag-list');
		};
		$scope.cancel = function() {
			window.history.back();
		};
}]);

/** 
 * Metadata Controller
 */
naempApp.controller('MetadataListCtrl',['$scope',	'MetadataCollectionService', 'MetadataService',	'$location',
		function($scope, MetadataCollectionService, MetadataService, $location) {

		$scope.cancel = function() {
			$scope.search = "";
		};
		
		// callback for ng-click 'editMetadata':
		$scope.editMetadata = function(metaID) {
			$location.path('/meta-detail/' + metaID);
		};
		
		// callback for ng-click 'deleteMetadata':
		$scope.deleteMetadata = function(metaID) {
			MetadataService.remove({id:metaID});
			$location.path('/meta-list');
		};
		// callback for ng-click 'createMetadata':
		$scope.createMetadata = function() {
			$location.path('/meta-creation');
		};
		$scope.metas = MetadataCollectionService.find();
		// panination
		$scope.currentPage = 1, $scope.numPerPage = 10,
		$scope.maxSize = 10;
}]);
naempApp.controller('MetadataDetailCtrl', [ '$scope', '$routeParams', 'MetadataService', '$location',
		function($scope, $routeParams, MetadataService, $location) {
		// callback for ng-click 'updateMetadata':
		$scope.updateMetadata = function(meta) {
			MetadataService.update({id:meta.metadataID}, meta);			
			$location.path('/meta-list');
		};
		// callback for ng-click 'cancel':
		$scope.cancel = function() {
			window.history.back();
		};
		$scope.meta = MetadataService.find({
			id : $routeParams.id
		});
}]);
naempApp.controller('MetadataCreationCtrl', [ '$scope', 'NewMetadataService', '$location', 
        function($scope, NewMetadataService, $location) {
	
		// callback for ng-click 'createNewMetadata':
		$scope.createMetadata = function() {
			NewMetadataService.create($scope.meta);
			$location.path('/meta-list');
		};
		$scope.cancel = function() {
			window.history.back();
		};
}]);

/**
 * Sensor Controller
 */
naempApp.controller('SensorListCtrl', ['$scope', 'SensorCollectionService', 'SensorService', '$location',
        function($scope, SensorCollectionService, SensorService, $location) {

		// callback for ng-click 'editSensor':
		$scope.editSensor = function(sensorID) {
			$location.path('/sensor-detail/' + sensorID);
		};
		// callback for ng-click 'deleteSensor':
		$scope.deleteSensor = function(sensorID) {
			SensorService.remove({id: sensorID});
			$location.path('/sensor-list');
		};
		// callback for ng-click 'createSensor':
		$scope.createSensor = function() {
			$location.path('/sensor-creation');
		};
	
		$scope.cancel = function() {
			$scope.search = "";
		};
	
		$scope.sensors = SensorCollectionService.find();
	
		// panination
		$scope.currentPage = 1, $scope.numPerPage = 10,
		$scope.maxSize = 10;
}]);

naempApp.controller('SensorDetailCtrl', [ '$scope', '$routeParams', 'SensorService', '$location',
		function($scope, $routeParams, SensorService, $location) {
	
		// callback for ng-click 'updateSensor':
		$scope.updateSensor = function(sensor) {
			SensorService.update({id: sensor.id}, sensor);
			$location.path('/sensor-list');
		};
		// callback for ng-click 'cancel':
		$scope.cancel = function() {
			window.history.back();
		};
		$scope.sensor = SensorService.find({
			id : $routeParams.id
		});
}]);

naempApp.controller('SensorCreationCtrl', [ '$scope', 'NewSensorService', 'MetadataCollectionService', 'TagCollectionService', 'StreamCollectionService', 'UserCollectionService', '$location', 
        function($scope, NewSensorService, MetadataCollectionService, TagCollectionService, StreamCollectionService, UserCollectionService, $location) {
	
		$scope.metas = MetadataCollectionService.find();
		$scope.tags = TagCollectionService.find();
		$scope.streams = StreamCollectionService.find();
		$scope.users = UserCollectionService.find();
		
		// callback for ng-click 'createSensor':
		$scope.createSensor = function(sensor) {
			NewSensorService.create(sensor);
			$location.path('/sensor-list');
		};
		$scope.cancel = function() {
			window.history.back();
		};
}]);


/**
 * Stream Controller
 */
naempApp.controller('StreamListCtrl', ['$scope', 'StreamCollectionService', 'StreamService', '$location',
		function($scope, StreamCollectionService, StreamService, $location) {
					
		$scope.cancel = function() {
			$scope.search = "";
		}
		// callback for ng-click 'editStream':
		$scope.editStream = function(streamID) {
			$location.path('/stream-detail/' + streamID);
		};

		// callback for ng-click 'deleteStream':
		$scope.deleteStream = function(streamID) {
			StreamService.remove({id:streamID});
			$location.path('/stream-list');
		};
		// callback for ng-click 'createStream':
		$scope.createStream = function() {
			$location.path('/stream-creation');
		};
		$scope.streams = StreamCollectionService.find();

		// panination
		$scope.currentPage = 1, $scope.numPerPage = 10,
		$scope.maxSize = 10;
}]);

naempApp.controller('StreamDetailCtrl', [ '$scope', '$routeParams',	'StreamService', 'MetadataCollectionService', 'TagCollectionService', '$location', '$log',
		function($scope, $routeParams, StreamService, MetadataCollectionService, TagCollectionService, $location, $log) {
	
		// callback for ng-click 'updateStream':
		$scope.updateStream = function(stream) {
			StreamService.update({id:stream.id}, stream);
			$location.path('/stream-list');
		};
		// callback for ng-click 'cancel':
		$scope.cancel = function() {
			window.history.back();
		};
		$scope.stream = StreamService.find({
			id : $routeParams.id
		});
		
		$scope.selectedA = [];
		$scope.selectedB = [];
		
		$scope.selectedTagA = [];
		$scope.selectedTagB = [];
				
		$scope.items = MetadataCollectionService.find();
		$scope.listA = MetadataCollectionService.find();
		
		$scope.tagItems = TagCollectionService.find();
		$scope.tagListA = TagCollectionService.find();
				
		function arrayObjectIndexOf(myArray, searchTerm, property) {
		    for(var i = 0, len = myArray.length; i < len; i++) {
		        if (myArray[i][property] === searchTerm) return i;
		    }
		    return -1;
		}
		  
		$scope.aToB = function() {
		  for (i in $scope.selectedA) {
		    var moveId = arrayObjectIndexOf($scope.items, $scope.selectedA[i], "key");
		    var meta= {}; 
		    meta.key = $scope.items[moveId].key;
		    meta.value = $scope.items[moveId].value;
		    meta.streamID = $scope.stream.id;
		    
		    $scope.stream.metas.push(meta);
		    var delId = arrayObjectIndexOf($scope.listA, $scope.selectedA[i], "key"); 
		    $scope.listA.splice(delId,1);
		  }
		  reset();
		};
		  
		$scope.bToA = function() {
		  for (i in $scope.selectedB) {
		    var moveId = arrayObjectIndexOf($scope.items, $scope.selectedB[i], "key"); 
		    $scope.listA.push($scope.items[moveId]);
		    var delId = arrayObjectIndexOf($scope.stream.metas, $scope.selectedB[i], "key"); 
		    $scope.stream.metas.splice(delId,1);
		  }
		  reset();
		};
		
		function reset(){
		  	$scope.selectedA=[];
		    $scope.selectedB=[];
		  }
		  
		  $scope.selectA = function(i) {
		    $scope.selectedA.push(i);
		  };

		  $scope.selectB = function(i) {
		  	$scope.selectedB.push(i);
		  };

		  $scope.tagToB = function() {
			  for (i in $scope.selectedTagA) {
			    var moveId = arrayObjectIndexOf($scope.tagItems, $scope.selectedTagA[i], "name");
			    // to add Tag to Stream_Tag, should add streamID element.
			    var tag= {}; 
			    tag.name = $scope.tagItems[moveId].name;
			    tag.streamID = $scope.stream.id;
			    $scope.stream.tags.push(tag);
			    
			    $log.info( 'aToB ===> Added Tag Info: ' + $scope.tagItems[moveId].name );
			    var delId = arrayObjectIndexOf($scope.tagListA, $scope.selectedTagA[i], "name"); 
			    $scope.tagListA.splice(delId,1);
			  }
			  resetTag();
			};
			  
			$scope.tagToA = function() {
			  for (i in $scope.selectedTagB) {
			    var moveId = arrayObjectIndexOf($scope.tagItems, $scope.selectedTagB[i], "name"); 
			    $scope.tagListA.push($scope.tagItems[moveId]);
			    var delId = arrayObjectIndexOf($scope.stream.tags, $scope.selectedTagB[i], "kenamey"); 
			    $scope.stream.tags.splice(delId,1);
			  }
			  resetTag();
			};
			
			function resetTag(){
			  	$scope.selectedTagA=[];
			    $scope.selectedTagB=[];
			  }
			  
			  $scope.selectTagA = function(i) {
			    $scope.selectedTagA.push(i);
			  };

			  $scope.selectTagB = function(i) {
			  	$scope.selectedTagB.push(i);
			  };
}]);

naempApp.controller('StreamCreationCtrl', [ '$scope', 'NewStreamService', 'SensorCollectionService', 'MetadataCollectionService','TagCollectionService', 'UserCollectionService', '$location', 
         function($scope, NewStreamService, SensorCollectionService,MetadataCollectionService, TagCollectionService, UserCollectionService, $location) {
		
		$scope.sensors = SensorCollectionService.find();
		$scope.metas = MetadataCollectionService.find();
		$scope.tags = TagCollectionService.find();
		$scope.users = UserCollectionService.find();
		
		// callback for ng-click 'createNewStream':
		$scope.createStream = function(stream) {
			NewStreamService.create(stream);
			$location.path('/stream-list');
		};
		$scope.cancel = function() {
			window.history.back();
		};
}]);

/**
 * Log Controller
 */
naempApp.controller('LogListCtrl',['$scope', 'LogCollectionService', 'LogService', '$location',
		function($scope, LogCollectionService, LogService, $location) {
	
		$scope.cancel = function() {
			$scope.search = "";
		}
		// callback for ng-click 'editLog':
		$scope.editLog = function(logID) {
			$location.path('/log-detail/' + logID);
		};

		// callback for ng-click 'deleteLog':
		$scope.deleteLog = function(logID) {
			LogService.remove({id:logID});
			$location.path('/log-list');
		};
		// callback for ng-click 'createLog':
		$scope.createLog = function() {
			$location.path('/log-creation');
		};
		$scope.logs = LogCollectionService.find();

		// panination
		$scope.currentPage = 1, $scope.numPerPage = 10,
		$scope.maxSize = 10;
}]);

naempApp.controller('LogDetailCtrl', [ '$scope', '$routeParams', 'LogService', '$location',
		function($scope, $routeParams, LogService, $location) {

		// callback for ng-click 'updateLog':
		$scope.updateLog = function(log) {
			LogService.update({id:log.logID}, log);
			$location.path('/log-list');
		};
		// callback for ng-click 'cancel':
		$scope.cancel = function() {
			window.history.back();
		};
		$scope.log = LogService.find({
			id : $routeParams.id
		});
}]);

naempApp.controller('LogCreationCtrl', [ '$scope', 'NewLogService','StreamCollectionService','$location', 
        function($scope, NewLogService, StreamCollectionService, $location) {
			
		$scope.streams = StreamCollectionService.find();
			
		// callback for ng-click 'createNewFeature':
		$scope.createLog = function(log) {
			NewLogService.create(log);
			$location.path('/log-list');
		};
		$scope.cancel = function() {
			window.history.back();
		};
}]);
