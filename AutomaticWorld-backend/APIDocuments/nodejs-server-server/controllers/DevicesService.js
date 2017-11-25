'use strict';

exports.addDeviceType = function(args, res, next) {
  /**
   * Add a new device type
   * Add a new device type
   *
   * device type object Device Device type object
   * returns Device
   **/
  var examples = {};
  examples['application/json'] = {
  "name" : "string",
  "description" : "string",
  "code_name" : "string"
};
  if (Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  } else {
    res.end();
  }
}

exports.deleteDeviceType = function(args, res, next) {
  /**
   * Deletes a device
   * Deletes a device
   *
   * id Long Device Type id
   * no response value expected for this operation
   **/
  res.end();
}

exports.getADeviceType = function(args, res, next) {
  /**
   * Get a device type
   * Get a device type with id
   *
   * id Long Device Type id
   * no response value expected for this operation
   **/
  res.end();
}

exports.getAllDeviceType = function(args, res, next) {
  /**
   * Get all device types
   * Get all device types
   *
   * no response value expected for this operation
   **/
  res.end();
}

exports.updateDeviceType = function(args, res, next) {
  /**
   * Update an existing device type
   * Update an existing device type
   *
   * id Long Device Type id
   * device Type object Device Device Type objects
   * returns Device
   **/
  var examples = {};
  examples['application/json'] = {
  "name" : "string",
  "description" : "string",
  "code_name" : "string"
};
  if (Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  } else {
    res.end();
  }
}

