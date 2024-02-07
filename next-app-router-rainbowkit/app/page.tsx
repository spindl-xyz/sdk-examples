"use client";

import { ConnectButton } from "@rainbow-me/rainbowkit";
import { useAccount } from "wagmi";
import { BannerAd } from "@spindl-xyz/embed-react";

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
    <>
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
            spindl.track(
              "test_event",
              {
                custom: "data goes here", // optional
              },
              {
                address: "0x1234...", // optional
                customerUserId: "1234", // optional
              }
            );
          }}
        >
          Track Custom Event
        </button>
        <br />
      </div>
      <BannerAd
        publisherId="collabland"
        style={{
          width: "970px",
          height: "250px",
        }}
        placementId="1"
        format={"970x250"}
        walletAddress="0x123"
      />
    </>
  );
}

export default Page;
