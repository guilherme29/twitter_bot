var sensor = require("node-dht-sensor");

/*
function getTemp(callback){
	sensor.read(11,4,function(err, temperature, humidity){
		temp = temperature;
		setTimeout(function(){callback(temp);}, 100);
	});

}

function getHumidity(callback){
	sensor.read(11,4,function(err, temperature, humidity){
		humd = humidity;
		setTimeout(function(){callback(humd);}, 100);
	});

}
module.exports = {getTemp, getHumidity}
*/

sensor.read(11,4,function(err,temperature,humidity){
	if(err){
	   throw err;
	}
	console.log();
	console.log(temperature);
	console.log(humidity);

});
