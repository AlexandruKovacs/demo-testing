import { test, expect } from '@playwright/test';
import { nombreUnico } from './utils';

/** Crea una cartera de prueba vía UI y devuelve la URL de su detalle. */
async function crearCarteraDePrueba(page: import('@playwright/test').Page): Promise<string> {
  const nombre = nombreUnico('Cartera Detalle');
  await page.goto('/carteras/nueva');
  await page.getByTestId('input-nombre').fill(nombre);
  await page.getByTestId('input-fecha').fill('2026-02-10');
  await page.getByTestId('input-estado').selectOption('ACTIVA');
  await page.getByTestId('input-valor').fill('8000000');
  await page.getByTestId('btn-guardar').click();
  await expect(page).toHaveURL(/\/carteras\/\d+$/);
  return page.url();
}

test.describe('Detalle de una cartera', () => {
  test('permite añadir un activo subyacente', async ({ page }) => {
    await crearCarteraDePrueba(page);

    await page.getByTestId('btn-toggle-activo').click();
    await page.getByTestId('activo-tipo').selectOption('HIPOTECARIO');
    await page.getByTestId('activo-valor').fill('250000');
    await page.getByTestId('activo-tasa').fill('3.2');
    await page.getByTestId('activo-plazo').fill('240');
    await page.getByTestId('activo-calificacion').selectOption('A');
    await page.getByTestId('btn-guardar-activo').click();

    await expect(page.getByTestId('activos-table')).toContainText('HIPOTECARIO');
    await expect(page.getByTestId('activos-table')).toContainText('240 meses');
  });
});
