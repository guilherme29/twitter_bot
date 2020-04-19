var five = require('johnny-five');
var board = new five.Board();

board.on("ready", function(){
	console.log("Connect and ready!");
  //var whatever = require('./moist_sensor.js');
  var moist = new five.Sensor({
    pin: "A0",
    freq: null,
    enabled: true
  });

  moist.on("data", function(){
    console.log("Moisture value: ", this.value);
    moist.disable();
  });
});
