var sensor = require("node-dht-sensor");

sensor.read(11,4,function(err,temperature,humidity){
	if(err){
	   throw err;
	}
	console.log(temperature);
	console.log(humidity);

});
