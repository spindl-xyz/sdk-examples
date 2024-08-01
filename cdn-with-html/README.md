## Example of using ASYNC CDN with Attribution SDK

This CDN Script Does the following:

1. Async script automatically enables autoPageViews
2. Wallet Connects only track Wallets that are connected through Metamask
3. You can access it in `window.spindl` and track extra custom events & other properties

### To Install Script SDK

1. Use the latest version of the script SDK here: [Script Installation Guide](https://docs.spindl.xyz/spindl/techncial/javascript-sdk/install#installation-via-script-cdn)
2. Once script tag is added to head of html, you should have access to SDK via `window.spindl` object and can send custom events such as `window.spindl.track('eventName', {...eventData})`

### To run locally

1. To have the wallet connect button & script work, you need the live server VS code plugin locally:
   https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer

- Run
  `Cmd + L` to run live server 2. Make sure to enter `sdkKey` in `index.html` &
  wallet connect `projectId` in `main.js`
