var five = require('johnny-five');
var m = require('./moisture_sensor.js');
var l = require('./light_sensor.js');
var dht = require('./dht11_rasp.js');

var board = new five.Board({
	port: '/dev/ttyUSB0'
});

board.on("ready", function(){
	m.getMoisture(function(value){console.log("Moisture soil level: " + value)});
	l.getLight(function(value){console.log("Light intensity: " + value)});
	dht.getTemp(function(value){console.log("Temperature; " + value + "ºC")});
	dht.getHumidity(function(value){console.log("Humidity: " + value + "%")});
});
