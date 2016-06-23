/**
 * @ S4RM System
 * @File Name: app.js
 * @Description: Angular JS Module using modules: ngResource, ngRoute(Angular
 *               JS) and ui.bootstrap (BootStrap)
 * 
 * @author Meilan Jiang
 * @since 2016.06.09
 * @version 1.0
 * 
 * Copyright(c) 2016 by CILAB All right reserved.
 */

var naempApp = angular.module('naempApp', [ 'ngResource', 'ngRoute', 'ui.multiselect',
		'ngAnimate', 'ui.bootstrap', 'smart-table', 'leaflet-directive', 'ng-fusioncharts']);
var resourceRoot = 'resources/templates';

// metadata management configuration
naempApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {				
		templateUrl : resourceRoot + '/home.jsp'
	}).when('/api', {				
		templateUrl : resourceRoot + '/api.jsp'
	}).when('/tag-list', {
		templateUrl : resourceRoot + '/tag/list.html',
		controller : 'TagListCtrl'
	}).when('/tag-detail/:id', {
		templateUrl : resourceRoot + '/tag/detail.html',
		controller : 'TagDetailCtrl'
	}).when('/tag-creation', {
		templateUrl : resourceRoot + '/tag/creation.html',
		controller : 'TagCreationCtrl'
	}).when('/meta-list', {
		templateUrl : resourceRoot + '/metadata/list.html',
		controller : 'MetadataListCtrl'
	}).when('/meta-detail/:id', {
		templateUrl : resourceRoot + '/metadata/detail.html',
		controller : 'MetadataDetailCtrl'
	}).when('/meta-creation', {
		templateUrl : resourceRoot + '/metadata/creation.html',
		controller : 'MetadataCreationCtrl'
	}).when('/sensor-list', {
		templateUrl : resourceRoot + '/sensor/list.html',
		controller : 'SensorListCtrl'
	}).when('/sensor-detail/:id', {
		templateUrl : resourceRoot + '/sensor/detail.html',
		controller : 'SensorDetailCtrl'
	}).when('/sensor-creation', {
		templateUrl : resourceRoot + '/sensor/creation.html',
		controller : 'SensorCreationCtrl'
	}).when('/stream-list', {
		templateUrl : resourceRoot + '/stream/list.html',
		controller : 'StreamListCtrl'
	}).when('/stream-detail/:id', {
		templateUrl : resourceRoot + '/stream/detail.html',
		controller : 'StreamDetailCtrl'
	}).when('/stream-creation', {
		templateUrl : resourceRoot + '/stream/creation.html',
		controller : 'StreamCreationCtrl'
	}).when('/log-list', {
		templateUrl : resourceRoot + '/log/list.html',
		controller : 'LogListCtrl'
	}).when('/log-detail/:id', {
		templateUrl : resourceRoot + '/log/detail.html',
		controller : 'LogDetailCtrl'
	}).when('/log-creation', {
		templateUrl : resourceRoot + '/log/creation.html',
		controller : 'LogCreationCtrl'
	});
} ]);
