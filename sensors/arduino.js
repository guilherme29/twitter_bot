var five = require('johnny-five');
var m = require('./sensors/moisture_sensor.js');
var l = require('./sensors/light_sensor.js');
var dht = require('./sensors/dht11_rasp.js');

var board = new five.Board({
	port: '/dev/ttyUSB0'
});



board.on("ready", function(){
	m.getMoisture(function(value){console.log(value)});
	l.getLight(function(value){console.log(value)});

	setTimeout(() => {
  		process.exit(1);
  	}, 500);
});

//dht.getTemp(function(value){console.log(value)});
//dht.getHumidity(function(value){console.log(value)})
