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
        source: "/ingest/:path*",
        destination: "https://spindl.link/:path*",
        // destination: "http://192.168.100.6:8787/:path*",
        basePath: false,
      },
    ];
  },
};

module.exports = nextConfig;
