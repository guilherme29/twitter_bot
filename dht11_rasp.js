var sensor = require("node-dht-sensor");
var temp = 0;
var humd = 0;

sensor.read(11, 4, function(err, temperature, humidity) {
	temp = temperature;
	humd = humidity;
});

function getTemp(callback){
	callback(temp);
}

function getHumidity(callback){
	callback(humd);
}

module.exports = {getTemp, getHumidity}
