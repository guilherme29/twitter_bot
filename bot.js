

console.log('The bot is starting');
var Twit = require('twit');

var config = require('./config');
var T = new Twit(config);

/*
//setting up an user stream
var stream = T.stream('user');

//anytime someone follows me
stream.on('follow', followed);

function followed(eventMsg){
  console.log("Follow event!");
  var name = eventMsg.source.name;
  var screenName = eventMsg.source.screen_name;
  tweetIt('.@' + screenName + ' do you like plants');
}
*/


//tweeting function
function tweetIt(message){
  var tweet = {status: message};
  function tweeted(err, data, response){
    if(err){
      console.log("Something went wrong");
    }
    else {
      console.log("Tweet successful");
    }
  }
  T.post('statuses/update', tweet, tweeted);
}

//tweetIt("yet another test");
