import { test, expect } from '@playwright/test';

test.describe('Panel de control', () => {
  test('muestra los KPIs agregados de la plataforma', async ({ page }) => {
    await page.goto('/');

    await expect(page.getByRole('heading', { name: 'Vista general de la titulización' })).toBeVisible();

    const kpis = page.getByTestId('kpi-section');
    await expect(kpis).toBeVisible({ timeout: 10_000 });
    await expect(kpis).toContainText('Carteras constituidas');
    await expect(kpis).toContainText('Valor total titulizado');
    await expect(kpis).toContainText('Activos subyacentes');
  });

  test('la navegación lateral permite moverse entre secciones', async ({ page }) => {
    await page.goto('/');

    await page.getByTestId('nav-carteras').click();
    await expect(page).toHaveURL(/\/carteras$/);
    await expect(page.getByRole('heading', { name: 'Carteras de titulización' })).toBeVisible();

    await page.getByTestId('nav-nueva-cartera').click();
    await expect(page).toHaveURL(/\/carteras\/nueva$/);
    await expect(page.getByRole('heading', { name: 'Constituir cartera de titulización' })).toBeVisible();

    await page.getByTestId('nav-dashboard').click();
    await expect(page).toHaveURL('/');
  });
});
