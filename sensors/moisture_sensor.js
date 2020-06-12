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
    var val = parseFloat((this.value*100)/1023).toFixed(2);
    callback(val);
    moist.disable();
  });

}

module.exports = {getMoisture};
