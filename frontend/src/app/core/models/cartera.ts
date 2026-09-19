export type EstadoCartera = 'EN_CONSTITUCION' | 'ACTIVA' | 'AMORTIZADA';
export type TipoActivo = 'HIPOTECARIO' | 'CONSUMO' | 'LEASING' | 'PYME';
export type TipoTramo = 'SENIOR' | 'MEZZANINE' | 'EQUITY';
export type Calificacion = 'AAA' | 'AA' | 'A' | 'BBB' | 'BB' | 'B' | 'CCC';

export interface Activo {
  id?: number;
  tipoActivo: TipoActivo;
  valorNominal: number;
  tasaInteres: number;
  plazoMeses: number;
  calificacionRiesgo: Calificacion;
}

export interface Tramo {
  id?: number;
  nombre: string;
  tipo: TipoTramo;
  porcentajeEstructura: number;
  calificacionCrediticia: Calificacion;
  tasaCupon: number;
  importeNominal: number;
}

export interface Cartera {
  id?: number;
  nombre: string;
  fechaConstitucion: string;
  moneda: string;
  estado: EstadoCartera;
  valorNominalTotal: number;
  activos?: Activo[];
  tramos?: Tramo[];
}

export interface ResumenPlataforma {
  numeroCarteras: number;
  valorTotalTitulizado: number;
  numeroActivos: number;
  distribucionPorTipoTramo: Record<TipoTramo, number>;
  distribucionPorEstado: Record<EstadoCartera, number>;
}
