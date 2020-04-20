var five = require('johnny-five');
var m = require('./moisture_sensor.js');
var l = require('./light_sensor.js');

var board = new five.Board({
	port: '/dev/ttyUSB0'
});

board.on("ready", function(){
  setInterval(function(){
		m.getMoisture(function(value){console.log("Moisture level: ", value)});
		l.getLight(function(value){console.log("Light intensity: ", value)});
	}, 3000);
});
