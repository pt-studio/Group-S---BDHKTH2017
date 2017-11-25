'use strict';

exports.addUserDevice = function(args, res, next) {
  /**
   * Add a new device for user
   * Add a new device for user
   *
   * userDevice object CreateUserDevice UserDevice object
   * returns UserDevice
   **/
  var examples = {};
  examples['application/json'] = {
  "device_type" : 1,
  "attribute" : {
    "humidity_value" : 50,
    "safe_value" : 50
  },
  "user" : 1,
  "code_name" : "string",
  "status" : "sleep/working/off/not-used"
};
  if (Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  } else {
    res.end();
  }
}

exports.getAUserDevice = function(args, res, next) {
  /**
   * Get a user device
   * Get information of a device of user with id
   *
   * id Long User Device id
   * returns UserDevice
   **/
  var examples = {};
  examples['application/json'] = {
  "device_type" : 1,
  "attribute" : {
    "humidity_value" : 50,
    "safe_value" : 50
  },
  "user" : 1,
  "code_name" : "string",
  "status" : "sleep/working/off/not-used"
};
  if (Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  } else {
    res.end();
  }
}

exports.getAllUserDevices = function(args, res, next) {
  /**
   * Get all user's devices
   * Get all user's devices
   *
   * no response value expected for this operation
   **/
  res.end();
}

exports.getUserToken = function(args, res, next) {
  /**
   * Get user's token
   * Get user's token. Used for ESP devices.
   *
   * device&#39;s codename Devices codename Device's codename
   * no response value expected for this operation
   **/
  res.end();
}

exports.updateSensorInformation = function(args, res, next) {
  /**
   * Update information from humidity sensors
   * Update information from humidity sensors
   *
   * updateInfo UpdateInfo Status of device and information of sensors
   * returns inline_response_200
   **/
  var examples = {};
  examples['application/json'] = {
  "message" : "Update from ESP successfully"
};
  if (Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  } else {
    res.end();
  }
}

exports.updateUserDevice = function(args, res, next) {
  /**
   * Update an existing device for user
   * Update an existing device for user
   *
   * id Long User Device id
   * user Id User Id Id of the user that the current user want to transfer
   * returns UserDevice
   **/
  var examples = {};
  examples['application/json'] = {
  "device_type" : 1,
  "attribute" : {
    "humidity_value" : 50,
    "safe_value" : 50
  },
  "user" : 1,
  "code_name" : "string",
  "status" : "sleep/working/off/not-used"
};
  if (Object.keys(examples).length > 0) {
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify(examples[Object.keys(examples)[0]] || {}, null, 2));
  } else {
    res.end();
  }
}

