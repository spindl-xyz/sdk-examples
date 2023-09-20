## Example of using ASYNC CDN with Attribution SDK

The Main difference between Async CDN & Regular CDN is:

1. You pass in the sdkKey directly into the async script. For regular cdn script you will need to instantiate sdk manually.
2. Async script automatically enables autoPageViews + walletConnects

- Wallet Connects only track Wallets that are connected through Metamask

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
