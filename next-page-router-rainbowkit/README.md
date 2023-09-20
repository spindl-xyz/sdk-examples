## Spindl SDK Demo Using Rainbow Starter Kit (inc. wagmi + next.js)

### Initalizing SDK & Tracking Page Views

- In Page Router, you can add config code to `pages/_app.tsx`
- For other react apps, you can use the root file when react is getting initlized

```tsx
import spindl from "@spindl-xyz/attribution"; // we recommend this package for most clients since it has full set of features
// import spindl from "@spindl-xyz/attribution-lite" // only for lite version customers

spindl.configure({
  sdkKey: process.env.NEXT_PUBLIC_SPINDL_SDK_KEY as string,
  debugMode: true, // we recommend only to have this when testing. you will see console.logs of emitted events in browser
});

spindl.enableAutoPageViews(); // this auto tracks page views
```

### Attributing/Tracking Wallet Connects

example is found in `pages/index.tsx`

With `wagmi`, rainbow kit, and other popular wallet libraries, each time a user is signed in with their wallet, you can use ` spindl.attribute(address)`

```tsx
import spindl from "@spindl-xyz/attribution";
// import spindl from "@spindl-xyz/attribution-lite" // only for lite version customers

import { useAccount } from "wagmi";

// ...
const Component = () => {
  const { address } = useAccount();

  useEffect(() => {
    if (address) {
      spindl.attribute(address);
    }
  }, [address]);
  // ...
};
```

### Tracking Custom Events

example is found in `pages/index.tsx`

Any Custom Events you want to track can be done so via `spindl.track(eventName, optionalJsonProperties)`

```tsx
import spindl from "@spindl-xyz/attribution";
// import spindl from "@spindl-xyz/attribution-lite" // only for lite version customers

// ...
const Component = () => {
  const { address } = useAccount();

  const customEventName = "ADD_TO_CART";
  const optionalJsonProperties = { custom_properties: "here" };

  return (
    <div>
      <button
        onClick={() => {
          spindl.track(customEventName, optionalCusomProperties);
        }}
      >
        Custom Event Trigger
      </button>
    </div>
  );
};
```

## Running The Repo

1. Install

```bash
npm install
```

2. Copy variables and fill them.

```
cp .env.local.example .env.local
```

- For Spindl SDK, you need to populate: `NEXT_PUBLIC_SPINDL_SDK_KEY`.
- In order to make rainbowkit work, you will also need a project id `NEXT_PUBLIC_PROJECT_ID`. More info [here](https://www.rainbowkit.com/docs/installation#configure)

3. You can run development Server

```bash
npm run dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.
