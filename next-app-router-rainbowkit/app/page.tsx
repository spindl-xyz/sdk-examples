"use client";

import { ConnectButton } from "@rainbow-me/rainbowkit";
import { useAccount } from "wagmi";

import spindl from "@spindl-xyz/attribution";
import { useEffect } from "react";
// import spindl from "@spindl-xyz/attribution-lite" // for lite version customers

function Page() {
  const { address } = useAccount();

  useEffect(() => {
    if (address) {
      spindl.attribute(address);
    }
  }, [address]);

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "flex-end",
        padding: 12,
      }}
    >
      <ConnectButton />
      <button
        onClick={() => {
          spindl.track("test_event", {
            custom: "data goes here",
          });
        }}
      >
        Track Custom Event
      </button>
    </div>
  );
}

export default Page;
