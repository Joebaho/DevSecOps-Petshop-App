package com.example;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(8080);

        get("/", (req, res) -> {
            res.type("text/html");
            return renderPage(
                "Petshop Cloud Store",
                """
                <section class="hero">
                  <div class="hero-copy">
                    <span class="eyebrow">Live on Amazon EKS</span>
                    <h1>Fresh Picks For Happy Pets, Shipped Through DevSecOps.</h1>
                    <p class="lead">
                      Welcome to the Petshop storefront demo. This application runs on Amazon EKS and is delivered
                      with Terraform, GitHub Actions, Trivy, Helm, ArgoCD, and Amazon ECR.
                    </p>
                    <div class="actions">
                      <a class="button primary" href="/products">Browse Products</a>
                      <a class="button secondary" href="/about">See Platform Story</a>
                    </div>
                  </div>
                  <div class="hero-panel">
                    <div class="stat-card">
                      <small>Status</small>
                      <strong>Healthy</strong>
                    </div>
                    <div class="stat-card">
                      <small>Environment</small>
                      <strong>Dev</strong>
                    </div>
                    <div class="stat-card">
                      <small>Region</small>
                      <strong>us-west-2</strong>
                    </div>
                  </div>
                </section>

                <section class="feature-band">
                  <article class="feature-pill">
                    <strong>Same-Day Grooming</strong>
                    Clean coats, trimmed nails, and calm care for dogs and cats.
                  </article>
                  <article class="feature-pill">
                    <strong>Healthy Nutrition</strong>
                    Vet-informed meals and treats chosen for age, breed, and energy.
                  </article>
                  <article class="feature-pill">
                    <strong>Playtime Essentials</strong>
                    Durable toys, enrichment kits, and comfort picks for every pet personality.
                  </article>
                </section>

                <section class="section-head">
                  <div>
                    <span class="section-label">Featured Picks</span>
                    <h2>Customer favorites for playful, healthy pets.</h2>
                  </div>
                  <a class="text-link" href="/products">View full catalog</a>
                </section>

                <section class="catalog">
                  <article class="product honey">
                    <span class="emoji">🐶</span>
                    <h3>Puppy Starter Box</h3>
                    <p>Training treats, a soft chew toy, calming wipes, and a cozy bandana for new best friends.</p>
                    <span class="price">$34</span>
                  </article>
                  <article class="product mint">
                    <span class="emoji">🐱</span>
                    <h3>Indoor Cat Comfort Kit</h3>
                    <p>A scratch pad, feather wand, premium snacks, and a warm nap blanket for stylish house cats.</p>
                    <span class="price">$29</span>
                  </article>
                  <article class="product rose">
                    <span class="emoji">🦴</span>
                    <h3>Wellness Snack Pack</h3>
                    <p>Protein bites, joint-care chews, and hydration boosts selected for active pets and daily care.</p>
                    <span class="price">$22</span>
                  </article>
                </section>

                <section class="section-head">
                  <div>
                    <span class="section-label">Delivery Pipeline</span>
                    <h2>From commit to cluster in a secure GitOps flow.</h2>
                  </div>
                </section>

                <section class="info-grid">
                  <article class="info-card">
                    <h3>Secure Delivery</h3>
                    <p>Every push builds the app, scans the container image with Trivy, and publishes a versioned image into Amazon ECR.</p>
                  </article>
                  <article class="info-card">
                    <h3>GitOps Deployment</h3>
                    <p>ArgoCD watches Git for changes and keeps the Kubernetes deployment synchronized with the declared state.</p>
                  </article>
                  <article class="info-card">
                    <h3>Promotion Ready</h3>
                    <p>The platform supports dev, stage, and prod so one tested release can be promoted forward with control.</p>
                  </article>
                </section>
                """
            );
        });

        get("/products", (req, res) -> {
            res.type("text/html");
            return renderPage(
                "Petshop Products",
                """
                <section class="section-head top-space">
                  <div>
                    <span class="section-label">Catalog</span>
                    <h1 class="page-title">Shop the Petshop collection.</h1>
                    <p class="lead narrow">
                      A playful catalog demo designed to make the application feel closer to a real storefront while
                      still serving as a DevSecOps showcase.
                    </p>
                  </div>
                </section>

                <section class="catalog wide">
                  <article class="product honey">
                    <span class="emoji">🐶</span>
                    <h3>Puppy Starter Box</h3>
                    <p>Training treats, a chew toy, wipes, and a soft bandana for energetic puppies.</p>
                    <span class="price">$34</span>
                  </article>
                  <article class="product mint">
                    <span class="emoji">🐱</span>
                    <h3>Indoor Cat Comfort Kit</h3>
                    <p>Scratcher, feather toy, premium snacks, and a warm nap blanket for curious indoor cats.</p>
                    <span class="price">$29</span>
                  </article>
                  <article class="product rose">
                    <span class="emoji">🦴</span>
                    <h3>Wellness Snack Pack</h3>
                    <p>Protein bites, joint-care chews, and hydration support selected for active pets.</p>
                    <span class="price">$22</span>
                  </article>
                  <article class="product sky">
                    <span class="emoji">🐾</span>
                    <h3>Daily Walk Bundle</h3>
                    <p>Leash, collapsible bowl, waste bag set, and an all-weather treat pouch.</p>
                    <span class="price">$26</span>
                  </article>
                  <article class="product sand">
                    <span class="emoji">🛁</span>
                    <h3>Spa & Groom Set</h3>
                    <p>Shampoo, brush, paw balm, and fresh-coat spray for after-bath shine.</p>
                    <span class="price">$31</span>
                  </article>
                  <article class="product lavender">
                    <span class="emoji">🎾</span>
                    <h3>Play Box Deluxe</h3>
                    <p>Enrichment toys and fetch favorites built for high-energy afternoons and cozy evenings.</p>
                    <span class="price">$27</span>
                  </article>
                </section>
                """
            );
        });

        get("/about", (req, res) -> {
            res.type("text/html");
            return renderPage(
                "About Petshop",
                """
                <section class="section-head top-space">
                  <div>
                    <span class="section-label">About</span>
                    <h1 class="page-title">A storefront demo backed by a real AWS delivery pipeline.</h1>
                    <p class="lead narrow">
                      Petshop is a portfolio-style Java application used to demonstrate end-to-end DevSecOps delivery
                      on AWS with infrastructure as code, secure CI, and GitOps deployment.
                    </p>
                  </div>
                </section>

                <section class="about-grid">
                  <article class="info-card">
                    <h3>Application Layer</h3>
                    <p>The Java app runs in Docker and is packaged for Kubernetes deployment through a Helm chart.</p>
                  </article>
                  <article class="info-card">
                    <h3>Infrastructure Layer</h3>
                    <p>Terraform provisions the VPC, EKS clusters, ECR repository, and GitHub Actions IAM role in `us-west-2`.</p>
                  </article>
                  <article class="info-card">
                    <h3>Delivery Layer</h3>
                    <p>GitHub Actions builds, scans, and publishes the image. ArgoCD watches Git and syncs the cluster automatically.</p>
                  </article>
                </section>

                <section class="timeline">
                  <div class="timeline-item">
                    <strong>1. Code Push</strong>
                    <p>A change lands on the `main` branch and triggers CI.</p>
                  </div>
                  <div class="timeline-item">
                    <strong>2. Build and Scan</strong>
                    <p>The app is packaged, containerized, and scanned with Trivy.</p>
                  </div>
                  <div class="timeline-item">
                    <strong>3. Publish</strong>
                    <p>The image is pushed to Amazon ECR and the Helm values for `dev` are updated in Git.</p>
                  </div>
                  <div class="timeline-item">
                    <strong>4. GitOps Sync</strong>
                    <p>ArgoCD detects the Git change and applies it to the EKS cluster.</p>
                  </div>
                </section>
                """
            );
        });

        get("/health", (req, res) -> {
            res.type("application/json");
            return "{\"status\":\"UP\",\"service\":\"petshop\",\"environment\":\"dev\"}";
        });
    }

    private static String renderPage(String title, String content) {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8">
              <meta name="viewport" content="width=device-width, initial-scale=1.0">
              <title>%s</title>
              <style>
                :root {
                  --bg: #f8f1e7;
                  --panel: rgba(255, 255, 255, 0.88);
                  --text: #1f2937;
                  --muted: #5f6b7a;
                  --accent: #d97706;
                  --accent-dark: #92400e;
                  --line: rgba(148, 64, 14, 0.16);
                  --shadow: 0 24px 70px rgba(31, 41, 55, 0.15);
                }

                * {
                  box-sizing: border-box;
                }

                body {
                  margin: 0;
                  font-family: "Trebuchet MS", "Segoe UI", sans-serif;
                  color: var(--text);
                  background:
                    radial-gradient(circle at top left, rgba(249, 115, 22, 0.20), transparent 28%),
                    radial-gradient(circle at right, rgba(16, 185, 129, 0.16), transparent 24%),
                    linear-gradient(135deg, #fff8ef 0%, #f7efe4 45%, #f2e7da 100%);
                  min-height: 100vh;
                }

                a {
                  color: inherit;
                }

                .shell {
                  max-width: 1120px;
                  margin: 0 auto;
                  padding: 40px 24px 72px;
                }

                .topbar {
                  display: flex;
                  justify-content: space-between;
                  align-items: center;
                  gap: 18px;
                  margin-bottom: 22px;
                  color: var(--muted);
                  font-size: 0.95rem;
                }

                .brand {
                  display: flex;
                  align-items: center;
                  gap: 12px;
                  font-weight: 800;
                  color: var(--accent-dark);
                  text-decoration: none;
                }

                .brand-mark {
                  width: 44px;
                  height: 44px;
                  border-radius: 14px;
                  display: grid;
                  place-items: center;
                  background: linear-gradient(135deg, #f59e0b, #f97316);
                  color: white;
                  font-size: 1.25rem;
                  box-shadow: 0 14px 28px rgba(217, 119, 6, 0.22);
                }

                .nav {
                  display: flex;
                  flex-wrap: wrap;
                  gap: 12px;
                }

                .nav a {
                  text-decoration: none;
                  padding: 10px 14px;
                  border-radius: 999px;
                  background: rgba(255, 255, 255, 0.7);
                  border: 1px solid rgba(148, 64, 14, 0.12);
                }

                .hero,
                .content-panel {
                  background: var(--panel);
                  border: 1px solid var(--line);
                  border-radius: 28px;
                  padding: 36px;
                  box-shadow: var(--shadow);
                  backdrop-filter: blur(12px);
                }

                .hero {
                  display: grid;
                  grid-template-columns: 1.4fr 0.8fr;
                  gap: 24px;
                }

                .eyebrow,
                .section-label {
                  display: inline-block;
                  padding: 8px 14px;
                  border-radius: 999px;
                  background: rgba(217, 119, 6, 0.1);
                  color: var(--accent-dark);
                  font-size: 0.82rem;
                  letter-spacing: 0.08em;
                  text-transform: uppercase;
                  font-weight: 700;
                }

                h1,
                h2,
                h3 {
                  margin-top: 0;
                }

                .hero h1,
                .page-title {
                  margin: 18px 0 14px;
                  font-size: clamp(2.2rem, 5vw, 4.4rem);
                  line-height: 0.98;
                  letter-spacing: -0.04em;
                }

                .lead {
                  max-width: 58ch;
                  font-size: 1.08rem;
                  line-height: 1.7;
                  color: var(--muted);
                }

                .lead.narrow {
                  max-width: 60ch;
                }

                .actions {
                  display: flex;
                  flex-wrap: wrap;
                  gap: 14px;
                  margin-top: 28px;
                }

                .button {
                  text-decoration: none;
                  padding: 14px 18px;
                  border-radius: 14px;
                  font-weight: 700;
                  transition: transform 0.18s ease, box-shadow 0.18s ease;
                }

                .button:hover {
                  transform: translateY(-2px);
                }

                .button.primary {
                  background: linear-gradient(135deg, var(--accent), #f59e0b);
                  color: white;
                  box-shadow: 0 14px 28px rgba(217, 119, 6, 0.28);
                }

                .button.secondary {
                  color: var(--accent-dark);
                  border: 1px solid rgba(146, 64, 14, 0.22);
                  background: rgba(255, 255, 255, 0.72);
                }

                .text-link {
                  color: var(--accent-dark);
                  font-weight: 700;
                  text-decoration: none;
                }

                .hero-panel,
                .feature-band,
                .catalog,
                .info-grid,
                .about-grid {
                  display: grid;
                  gap: 18px;
                }

                .hero-panel {
                  grid-template-columns: 1fr;
                  align-content: start;
                }

                .stat-card,
                .feature-pill,
                .info-card,
                .timeline-item {
                  padding: 20px;
                  border-radius: 20px;
                  background: rgba(255, 255, 255, 0.74);
                  border: 1px solid rgba(148, 64, 14, 0.12);
                }

                .stat-card {
                  background: #1f2937;
                  color: #f9fafb;
                }

                .stat-card small {
                  display: block;
                  opacity: 0.75;
                  margin-bottom: 8px;
                  text-transform: uppercase;
                  letter-spacing: 0.08em;
                }

                .stat-card strong {
                  font-size: 1.35rem;
                }

                .feature-band {
                  margin-top: 22px;
                  grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
                }

                .feature-pill strong,
                .info-card h3,
                .timeline-item strong {
                  display: block;
                  margin-bottom: 8px;
                }

                .section-head {
                  display: flex;
                  justify-content: space-between;
                  align-items: end;
                  gap: 16px;
                  margin: 32px 0 18px;
                }

                .top-space {
                  margin-top: 10px;
                }

                .catalog {
                  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
                }

                .catalog.wide {
                  grid-template-columns: repeat(auto-fit, minmax(230px, 1fr));
                }

                .product {
                  position: relative;
                  overflow: hidden;
                  border-radius: 22px;
                  padding: 22px;
                  min-height: 230px;
                  box-shadow: var(--shadow);
                  border: 1px solid rgba(31, 41, 55, 0.08);
                }

                .honey {
                  background: linear-gradient(180deg, #fff5d9, #ffe6b0);
                }

                .mint {
                  background: linear-gradient(180deg, #dff8f2, #c5efe6);
                }

                .rose {
                  background: linear-gradient(180deg, #ffe5eb, #ffd1dc);
                }

                .sky {
                  background: linear-gradient(180deg, #e6f3ff, #d4e9ff);
                }

                .sand {
                  background: linear-gradient(180deg, #f8eed7, #f3dfb0);
                }

                .lavender {
                  background: linear-gradient(180deg, #efe7ff, #e0d0ff);
                }

                .emoji {
                  font-size: 2rem;
                  display: inline-block;
                  margin-bottom: 14px;
                }

                .product p,
                .feature-pill,
                .info-card p,
                .timeline-item p {
                  color: var(--muted);
                  line-height: 1.6;
                  margin: 0;
                }

                .price {
                  position: absolute;
                  bottom: 20px;
                  left: 22px;
                  display: inline-block;
                  padding: 9px 12px;
                  border-radius: 999px;
                  background: rgba(255, 255, 255, 0.78);
                  font-weight: 800;
                }

                .info-grid,
                .about-grid {
                  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
                }

                .timeline {
                  margin-top: 20px;
                  display: grid;
                  grid-template-columns: repeat(auto-fit, minmax(210px, 1fr));
                  gap: 16px;
                }

                footer {
                  margin-top: 28px;
                  color: var(--muted);
                  font-size: 0.95rem;
                  text-align: center;
                }

                @media (max-width: 820px) {
                  .hero {
                    grid-template-columns: 1fr;
                  }

                  .section-head,
                  .topbar {
                    flex-direction: column;
                    align-items: start;
                  }
                }
              </style>
            </head>
            <body>
              <main class="shell">
                <div class="topbar">
                  <a class="brand" href="/">
                    <div class="brand-mark">🐾</div>
                    <div>
                      <div>Petshop Cloud Store</div>
                      <small>DevSecOps demo on AWS</small>
                    </div>
                  </a>
                  <nav class="nav">
                    <a href="/">Home</a>
                    <a href="/products">Products</a>
                    <a href="/about">About</a>
                    <a href="/health">Health</a>
                  </nav>
                </div>

                <div class="content-panel">
                  %s
                </div>

                <footer>
                  Built for storefront demos, portfolio walkthroughs, and end-to-end DevSecOps practice on AWS.
                </footer>
              </main>
            </body>
            </html>
            """.formatted(title, content);
    }
}
