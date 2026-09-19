El objetivo de este PoC es doble:

1. Demostrar una arquitectura **Angular + Spring Boot** end-to-end, funcional y navegable.
2. Dejar montada y lista para demo la **infraestructura de testing automatizado
   E2E en el frontend con Playwright**, con suites reales ejecutándose contra
   flujos completos de la aplicación (no mocks).

```
├── backend/     Spring Boot 3.3 (Java 17) · API REST + H2 en memoria
└── frontend/    Angular 20 · UI + Playwright E2E
```

## Arranque rápido

### 1. Backend (puerto 8080)

```bash
cd backend
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080/api`, con datos de ejemplo
precargados automáticamente (3 carteras, activos y tramos) y consola H2 en
`http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:titulizacion`, usuario `sa`, sin contraseña).

### 2. Frontend (puerto 4200)

```bash
cd frontend
npm install
npm start
```

Abre `http://localhost:4200`. El frontend espera la API en `http://localhost:8080/api`
(configurable en `src/environments/environment.ts`).

### 3. Tests E2E con Playwright

```bash
cd frontend
npx playwright install   # una sola vez, descarga los navegadores
npm run e2e               # ejecuta toda la suite (arranca ng serve automáticamente)
npm run e2e:ui             # modo UI interactivo, ideal para demo
npm run e2e:report         # abre el último informe HTML
```

El backend debe estar arrancado en `:8080` antes de lanzar `npm run e2e`, ya
que los tests ejercitan la integración real contra la API en lugar de usar mocks.

Ver `frontend/e2e/README.md` para el detalle de la suite.

## Stack técnico

- **Frontend**: Angular 20 (standalone components, signals, `@if`/`@for`),
  Reactive Forms, SCSS con sistema de tokens propio.
- **Backend**: Spring Boot 3.3 sobre Java 17, Spring Data JPA, Bean Validation,
  base de datos H2 en memoria (sin dependencias externas para la demo).
- **Testing**: Playwright (`@playwright/test`) para E2E multi-navegador
  (Chromium, Firefox, viewport móvil); Karma/Jasmine para unit tests de
  componentes; JUnit 5 + MockMvc para tests de integración del backend.
