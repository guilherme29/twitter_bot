
# Twitter bot for plants

This is a repository for a node js bot that tweets it's status from time to time.

## How to use
add a config.js file with the keys obtained from the twitter developers website, to authenticate into your plant account.
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


just run
```bash
node bot.js
```
