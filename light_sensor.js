/*
  This sensor is a gray scale sensor
  It mesure the level os intensity of light by a gray scale
  0 - no light
  500 - a lot of light
*/

var five = require('johnny-five');

function getLight(callback){
  var light = new five.Sensor({
      pin: "A1",
      frep: null,
      enabled: true
  });

  light.on("data", function(){
    callback(this.value);
    light.disable();
  });

}

module.exports = {getLight};
