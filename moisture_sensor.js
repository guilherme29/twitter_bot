/*
  This sensor have a value range from 0 to 1023 where:
    0 - 0%
    1023 - 100%
*/

var five = require('johnny-five');

function getMoisture(callback){
  var moist = new five.Sensor({
    pin: "A0",
    freq: null,
    enabled: true
  });

  moist.on("data", function(){
    callback(this.value);
    moist.disable();
  });

}

module.exports = {getMoisture};
