import { test, expect } from '@playwright/test';
import { nombreUnico } from './utils';

test.describe('Constitución de una nueva cartera', () => {
  test('muestra errores de validación con datos incompletos', async ({ page }) => {
    await page.goto('/carteras/nueva');

    await page.getByTestId('input-nombre').fill('ab'); // menos de 3 caracteres
    await page.getByTestId('input-nombre').blur();

    await page.getByTestId('btn-guardar').click();

    await expect(page.locator('.error-text')).toBeVisible();
    await expect(page).toHaveURL(/\/carteras\/nueva$/);
  });

  test('crea una cartera y redirige a su detalle', async ({ page }) => {
    const nombre = nombreUnico('RMBS Serie');

    await page.goto('/carteras/nueva');
    await page.getByTestId('input-nombre').fill(nombre);
    await page.getByTestId('input-fecha').fill('2026-03-01');
    await page.getByTestId('input-moneda').selectOption('EUR');
    await page.getByTestId('input-estado').selectOption('EN_CONSTITUCION');
    await page.getByTestId('input-valor').fill('12500000');

    await page.getByTestId('btn-guardar').click();

    await expect(page).toHaveURL(/\/carteras\/\d+$/);
    await expect(page.getByTestId('cartera-nombre')).toHaveText(nombre);
  });
});
