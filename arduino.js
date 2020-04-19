var five = require('johnny-five');
var board = new five.Board();

var moist_value = board.on("ready", getMoisture());
	//console.log("Connect and ready!");
  //var whatever = require('./moist_sensor.js');
	console.log("moist_value:" + moist_value);

});
