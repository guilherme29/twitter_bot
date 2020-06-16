
# Twitter bot for plants
This is a repository for a node js bot that tweets it's status from time to time.

## Materials
We used an Arduino Uno board coupled with some sensors. You can find them here:
* [Soil humidity sensor][1]
* [Humidity and temperature sensor][2]
* [Light sensor][3]

[1]: https://www.amazon.com/HUABAN-Hygrometer-Humidity-Detection-Moisture/dp/B077PW1VW5
[2]: https://www.amazon.com/Temperature-Relative-Humidity-Compatible-Arduino/dp/B00TM87YRS
[3]: https://www.ptrobotics.com/opticos/4052-gravity-analog-grayscale-sensor-v2.html

You can probably use other similar sensors, but you may have to tweak the code for it to work.
We used java 11 to run our code and so we advise you to have this version installed in your raspberry machine as well.

## How to use
Download the repository with the following in your terminal:
```bash
git clone https://github.com/guilherme29/twitter_bot
```

Add a ```config.js``` file to the repository in the twitter directory with the keys obtained from the twitter developers website to authenticate into your plant account.
The structure should be:

```javascript
module.exports = {
  consumer_key:         ' ... ',
  consumer_secret:      ' ... ',
  access_token:         ' ... ',
  access_token_secret:  ' ... ',
  timeout_ms:           60*1000,  // optional HTTP request timeout to apply to all requests.
  strictSSL:            true,     // optional - requires SSL certificates to be valid.
}
```

Then run the following in your terminal.
```bash
./setup.sh
```
This should compile the java files and create a directory for the test files. 
Should you already have done this you can use this file to clean everything up as well. 

Next you're gonna need to start the TestingServer. 
We advise you to start it like this so it runs in the background and you can see the output logs in case of error.
Go to the server folder and run the following:
```bash
java TestingServer >> log_testing &
```

Once the tests directory in the home folder contains both a rasp_perm and ardu_perm files, you may start the TwitterServer.
This may take up to a minute or two, but no more than that.
Then, go back to the server folder and run the following.
```bash
java TwitterServer >> log_twitter &
```

You should now get at least a tweet in your account with your plant status (two if it's conditions aren't ideal).
Your plant's bot should be 'up and running'!

## Credits
This project was made by [zeterd][4] and [guilherme29][5].

[4]: https://github.com/Zeterd
[5]: https://github.com/guilherme29
