import { NextRequest, NextResponse } from "next/server";

export function middleware(request: NextRequest) {
  const hostname = "spindl.link";

  const requestHeaders = new Headers(request.headers);

  requestHeaders.set("host", hostname);

  console.log("xxx request.ip", request.ip);
  console.log("xxx requestHeaders", requestHeaders);

  if (request.ip) {
    requestHeaders.set("X-Forwarded-To", request.ip);
  }

  let url = request.nextUrl.clone();
  url.protocol = "https";
  url.hostname = hostname;
  url.port = "443";
  url.pathname = url.pathname.replace(/^\/ingest/, "");

  return NextResponse.rewrite(url, {
    headers: requestHeaders,
  });
}

export const config = {
  matcher: "/ingest/:path*",
};
