/**
 * @ S4RM System
 * @File Name: service.js
 * @Description: Angular JS Services
 * 
 * @author Meilan Jiang
 * @since 2016.06.17
 * @version 1.0
 * 
 * Copyright(c) 2016 by CILAB All right reserved.
 */

var contextRoot = '/s4rm';

/**
 * Tag
 */
naempApp.factory('TagCollectionService', function($resource) {
	return $resource(contextRoot + '/tags', {}, {
		find : {
			method : 'GET',
			isArray : true
		}
	})
});
naempApp.factory('NewTagService', function($resource) {
	return $resource(contextRoot + '/tags/new', {}, {
		create : {
			method : 'POST'
		}
	})
});
naempApp.factory('TagService', function($resource) {
	return $resource(contextRoot + '/tags/:id', {id: '@id'}, {
		find : {
			method : 'GET',
			params : {
				id : '@id'
			}
		},
		update : {
			method : 'PUT'			
		},
		remove : {
			method : 'DELETE'
		}
	})
});

/**
 * Meatadata
 */
naempApp.factory('MetadataCollectionService', function($resource) {
	return $resource(contextRoot + '/metas', {}, {
		find : {
			method : 'GET',
			isArray : true
		}
	})
});
naempApp.factory('NewMetadataService', function($resource) {
	return $resource(contextRoot + '/metas/new', {}, {
		create : {
			method : 'POST'
		}
	})
});
naempApp.factory('MetadataService', function($resource) {
	return $resource(contextRoot + '/metas/:id', {}, {

		find : {
			method : 'GET',
			params : {
				id : '@id'
			}
		},
		update : {
			method : 'PUT'
		},
		remove : {
			method : 'DELETE'
		}
	})
});

/**
 * Sensors
 */
naempApp.factory('SensorCollectionService', function($resource) {
	return $resource(contextRoot + '/sensors', {}, {
		find : {
			method : 'GET',
			isArray : true
		},
		search : {
			method : 'POST',
			isArray : true
		}
	})
});
naempApp.factory('NewSensorService', function($resource) {
	return $resource(contextRoot + '/sensors/new', {}, {
		create : {
			method : 'POST'
		}
	})
});
naempApp.factory('SensorService', function($resource) {
	return $resource(contextRoot + '/sensors/:id', {id : '@id'}, {
		find : {
			method : 'GET',
			params : {
				id : '@id'
			}
		},
		update : {
			method : 'PUT'
		},
		remove : {
			method : 'DELETE'
		}
	})
});


/**
 * Streams
 */
naempApp.factory('StreamCollectionService', function($resource) {
	return $resource(contextRoot + '/streams', {}, {
		find : {
			method : 'GET',
			isArray : true
		}
	})
});
naempApp.factory('NewStreamService', function($resource) {
	return $resource(contextRoot + '/streams/new', {}, {
		create : {
			method : 'POST'
		}
	})
});
naempApp.factory('StreamService', function($resource) {
	return $resource(contextRoot + '/streams/:id', {id: '@id'}, {
		find : {
			method : 'GET',
			params : {
				id : '@id'
			}
		},
		update : {
			method : 'PUT'
		},
		remove : {
			method : 'DELETE'
		}
	})
});


/**
 * Log
 */
naempApp.factory('LogCollectionService', function($resource) {
	return $resource(contextRoot + '/logs', {}, {
		find : {
			method : 'GET',
			isArray : true
		}
	})
});
naempApp.factory('NewLogService', function($resource) {
	return $resource(contextRoot + '/logs/new', {}, {
		create : {
			method : 'POST'
		}
	})
});
naempApp.factory('LogService', function($resource) {
	return $resource(contextRoot + '/logs/:id', {id: '@id'}, {
		find : {
			method : 'GET',
			params : {
				id : '@id'
			}
		},
		update : {
			method : 'PUT'
		},
		remove : {
			method : 'DELETE'
		}
	})
});

/**
 * Users
 */
naempApp.factory('UserCollectionService', function($resource) {
	return $resource(contextRoot + '/users', {}, {
		find : {
			method : 'GET',
			isArray : true
		},
		search : {
			method : 'POST',
			isArray : true
		}
	})
});
naempApp.factory('UserSensorService', function($resource) {
	return $resource(contextRoot + '/users/new', {}, {
		create : {
			method : 'POST'
		}
	})
});
naempApp.factory('UserService', function($resource) {
	return $resource(contextRoot + '/users/:id', {id : '@id'}, {
		find : {
			method : 'GET',
			params : {
				id : '@id'
			}
		},
		update : {
			method : 'PUT'
		},
		remove : {
			method : 'DELETE'
		}
	})
});

