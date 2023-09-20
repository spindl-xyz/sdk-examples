## Example of using ASYNC CDN with Attribution SDK

This CDN Script Does the following:

1. Async script automatically enables autoPageViews + walletConnects
2. Wallet Connects only track Wallets that are connected through Metamask
3. You can access it in `window.spindl` and track extra custom events & other properties

```html
...

<script
  async
  data-key="ADD_sdkKey_HERE"
  data-name="spindl-sdk"
  src="https://cdn.spindl.xyz/attribution-latest.js"
></script>
```

### To run locally

1. To have the wallet connect button & script work, you need the live server VS code plugin locally:
   https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer

- Run
  `Cmd + L` to run live server 2. Make sure to enter `sdkKey` in `index.html` &
  wallet connect `projectId` in `main.js`
