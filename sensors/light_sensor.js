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
    var val = parseFloat(100.00 - ((this.value*100)/550)).toFixed(2);
    callback(val);
    light.disable();

  });


}

module.exports = {getLight};



