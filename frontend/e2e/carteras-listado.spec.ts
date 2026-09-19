import { test, expect } from '@playwright/test';
import { nombreUnico } from './utils';

test.describe('Listado de carteras', () => {
  test('filtra por nombre y por estado', async ({ page }) => {
    const nombre = nombreUnico('Cartera Filtro');

    // Preparación: crea una cartera vía UI para asegurar que hay datos que filtrar
    await page.goto('/carteras/nueva');
    await page.getByTestId('input-nombre').fill(nombre);
    await page.getByTestId('input-fecha').fill('2026-01-15');
    await page.getByTestId('input-estado').selectOption('ACTIVA');
    await page.getByTestId('input-valor').fill('5000000');
    await page.getByTestId('btn-guardar').click();
    await expect(page).toHaveURL(/\/carteras\/\d+$/);

    // Verificación en el listado
    await page.goto('/carteras');
    await page.getByTestId('filtro-nombre').fill(nombre);
    await expect(page.getByTestId('carteras-table')).toContainText(nombre);

    await page.getByTestId('filtro-nombre').fill('nombre-que-no-existe-xyz');
    await expect(page.getByTestId('sin-resultados')).toBeVisible();

    await page.getByTestId('filtro-nombre').fill('');
    await page.getByTestId('filtro-estado').selectOption('ACTIVA');
    await expect(page.getByTestId('carteras-table')).toContainText(nombre);
  });

  test('el botón de nueva cartera navega al formulario', async ({ page }) => {
    await page.goto('/carteras');
    await page.getByTestId('btn-nueva-cartera').click();
    await expect(page).toHaveURL(/\/carteras\/nueva$/);
  });
});
