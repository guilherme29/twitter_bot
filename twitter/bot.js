console.log('The bot is starting');
var Twit = require('twit');
var fs = require('fs');

var config = require('./config.js');
var T = new Twit(config);

process.argv.forEach((val, index) => {
  if(index == 2)
    file = val;
});

fs.readFile(file, (err, data) => {
  if(err) {
    console.log("Error happend: " + err);
  }
  tweetIt(data.toString());
});

//tweeting function
function tweetIt(message){
  var tweet = {
    status: message
  }

  //post a tweet
  T.post('statuses/update', tweet, tweeted);

  function tweeted(err, data, response){
    if(err){
      console.log("Something went wrong \n" + err);
    }
    else {
      console.log("Tweet successful" + response);
      console.log("Tweet successful" + data);

    }
  }
}
