'use strict';

var url = require('url');

var Devices = require('./DevicesService');

module.exports.addDeviceType = function addDeviceType (req, res, next) {
  Devices.addDeviceType(req.swagger.params, res, next);
};

module.exports.deleteDeviceType = function deleteDeviceType (req, res, next) {
  Devices.deleteDeviceType(req.swagger.params, res, next);
};

module.exports.getADeviceType = function getADeviceType (req, res, next) {
  Devices.getADeviceType(req.swagger.params, res, next);
};

module.exports.getAllDeviceType = function getAllDeviceType (req, res, next) {
  Devices.getAllDeviceType(req.swagger.params, res, next);
};

module.exports.updateDeviceType = function updateDeviceType (req, res, next) {
  Devices.updateDeviceType(req.swagger.params, res, next);
};
