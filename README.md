
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

You can probably use other similar sensors but you may have to tweak the code for it to work.


## How to use
Download the repository with the following in your terminal:
```bash
git clone https://github.com/guilherme29/twitter_bot
```

Add a ```config.js``` file to the repository with the keys obtained from the twitter developers website, to authenticate into your plant account.
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
node bot.js
```
Your plant's bot should be 'up and running'!

## Credits
This project was made by [zeterd][1] and [myself][2].

[1]
