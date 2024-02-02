/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  webpack: (config) => {
    config.resolve.fallback = { fs: false, net: false, tls: false };
    config.externals.push("pino-pretty", "lokijs", "encoding");
    return config;
  },
  async rewrites() {
    return [
      {
        source: "/api/_proxy/ingest/:path*",
        destination: "https://spindl.link/:path*",
        basePath: false,
      },
    ];
  },
};

module.exports = nextConfig;
