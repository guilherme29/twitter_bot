var five = require('johnny-five');
var m = require('./moisture_sensor.js');
var board = new five.Board();

board.on("ready", function(){
	console.log("moist_value: ", m.getMoisture();)
});
