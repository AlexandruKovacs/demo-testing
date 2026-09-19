import { defineConfig, devices } from '@playwright/test';

/**
 * Configuración de Playwright para el PoC de titulización.
 * Arranca automáticamente `ng serve` antes de ejecutar los tests
 * y lo apaga al finalizar (ver `webServer`).
 *
 * Requisitos para el backend:
 * El backend Spring Boot debe estar arrancado en http://localhost:8080
 * antes de lanzar la suite, ya que los tests ejercitan flujos reales
 * contra la API (no se usan mocks) para demostrar la integración end-to-end.
 */
export default defineConfig({
  testDir: './e2e',
  fullyParallel: false, // los tests comparten estado (BD H2), se ejecutan en serie
  forbidOnly: !!process.env['CI'],
  retries: process.env['CI'] ? 1 : 0,
  workers: 1,
  reporter: [
    ['html', { open: 'never', outputFolder: 'playwright-report' }],
    ['list']
  ],
  use: {
    baseURL: 'http://localhost:4200',
    trace: 'on-first-retry',
    screenshot: 'only-on-failure',
    video: 'retain-on-failure',
    launchOptions: {
      slowMo: 100 // 2000 ralentiza la ejecución para ver mejor lo que ocurre
    }
  },
  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] }
    },
    {
      name: 'firefox',
      use: { ...devices['Desktop Firefox'] }
    },
    {
      name: 'mobile-chrome',
      use: { ...devices['Pixel 7'] }
    }
  ],
  webServer: {
    command: 'npm run start -- --port 4200',
    url: 'http://localhost:4200',
    reuseExistingServer: !process.env['CI'],
    timeout: 120_000
  }
});
