
getMoisture(){
  var moist = new five.Sensor({
    pin: "A0",
    freq: null,
    enabled: true
  });

  moist.on("data", function(){
    var value = this.value;
    console.log("moisture: " + value);
    moist.disable();
    return value;
  });
}
