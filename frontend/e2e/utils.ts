/** Genera un nombre único por ejecución para evitar colisiones entre tests. */
export function nombreUnico(prefijo: string): string {
  return `${prefijo} ${Date.now()}-${Math.floor(Math.random() * 1000)}`;
}
