'use strict';

var url = require('url');

var UserDevices = require('./UserDevicesService');

module.exports.addUserDevice = function addUserDevice (req, res, next) {
  UserDevices.addUserDevice(req.swagger.params, res, next);
};

module.exports.getAUserDevice = function getAUserDevice (req, res, next) {
  UserDevices.getAUserDevice(req.swagger.params, res, next);
};

module.exports.getAllUserDevices = function getAllUserDevices (req, res, next) {
  UserDevices.getAllUserDevices(req.swagger.params, res, next);
};

module.exports.getUserToken = function getUserToken (req, res, next) {
  UserDevices.getUserToken(req.swagger.params, res, next);
};

module.exports.updateSensorInformation = function updateSensorInformation (req, res, next) {
  UserDevices.updateSensorInformation(req.swagger.params, res, next);
};

module.exports.updateUserDevice = function updateUserDevice (req, res, next) {
  UserDevices.updateUserDevice(req.swagger.params, res, next);
};
