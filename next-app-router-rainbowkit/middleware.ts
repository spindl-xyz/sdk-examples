import { NextResponse } from "next/server";

export function middleware(request: any) {
  const hostname = "spindl.link";

  const requestHeaders = new Headers(request.headers);
  requestHeaders.set("host", hostname);

  let url = request.nextUrl.clone();
  url.protocol = "https";
  url.hostname = hostname;
  url.port = 443;
  url.pathname = url.pathname.replace(/^\/ingest/, "");

  console.log("xxx requestHeaders", requestHeaders);

  return NextResponse.rewrite(url, {
    headers: requestHeaders,
  });
}

export const config = {
  matcher: "/ingest/:path*",
};
