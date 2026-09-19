# Infraestructura de testing E2E (Playwright)

Esta carpeta contiene la suite de pruebas end-to-end que demuestra la
infraestructura de testing automatizado del frontend.

## Qué se demuestra

- **Configuración multi-navegador**: cada test se ejecuta en Chromium, Firefox
  y un viewport móvil (Pixel 7), definido en `playwright.config.ts`.
- **Arranque automático del servidor**: Playwright levanta `ng serve` antes de
  la suite (`webServer`) y lo reutiliza si ya está corriendo, para que la demo
  sea un único comando (`npm run e2e`).
- **Selectores estables**: toda la UI expone atributos `data-testid`
  específicos para testing, en lugar de depender de clases CSS o texto que
  puedan cambiar con el diseño.
- **Flujos reales de negocio, no smoke tests aislados**: cada spec ejercita un
  flujo completo (crear cartera → verla en el listado → añadir tramos/activos
  → comprobar que la visualización de estructura de capital se actualiza).
- **Aislamiento entre ejecuciones**: los datos de prueba se generan con
  nombres únicos (`utils.ts` → `nombreUnico`) para poder repetir la suite
  sobre la misma base de datos sin colisiones.
- **Artefactos de diagnóstico**: capturas en fallo, vídeo bajo demanda y
  trazas en el primer reintento, además de un informe HTML navegable
  (`npm run e2e:report`).

## Estructura

```
e2e/
├── utils.ts                    Helpers compartidos (generación de nombres únicos)
├── dashboard.spec.ts            Panel de control y navegación principal
├── carteras-listado.spec.ts     Listado, filtros por nombre y por estado
├── carteras-crear.spec.ts       Alta de cartera: validación y flujo feliz
└── carteras-detalle.spec.ts     Detalle: alta de activos y tramos, waterfall
```

## Ejecutar la suite

```bash
# Una sola vez: descarga los binarios de los navegadores
npx playwright install

# Backend arrancado en :8080 (ver README raíz)

npm run e2e            # ejecución completa, modo headless
npm run e2e:headed     # con navegador visible, útil para demo en vivo
npm run e2e:ui          # modo UI interactivo (recomendado para demo)
npm run e2e:report     # abre el último informe HTML generado
```

## Extender la suite

Para añadir un nuevo flujo:

1. Añade `data-testid` a los elementos relevantes del componente.
2. Crea `e2e/<flujo>.spec.ts` siguiendo el patrón `test.describe` / `test`.
3. Reutiliza `nombreUnico()` de `utils.ts` si el test crea datos, para poder
   ejecutar la suite en paralelo o de forma repetida sin limpiar la base de datos.
