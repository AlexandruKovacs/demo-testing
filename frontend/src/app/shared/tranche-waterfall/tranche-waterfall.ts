import { Component, input, computed } from '@angular/core';
import { Tramo } from '../../core/models/cartera';

@Component({
  selector: 'app-tranche-waterfall',
  imports: [],
  templateUrl: './tranche-waterfall.html',
  styleUrl: './tranche-waterfall.scss'
})
export class TrancheWaterfall {
  tramos = input<Tramo[]>([]);

  segments = computed(() => {
    const list = this.tramos();
    const total = list.reduce((sum, t) => sum + t.porcentajeEstructura, 0) || 1;
    return list
      .slice()
      .sort((a, b) => order(a.tipo) - order(b.tipo))
      .map((t) => ({ ...t, width: (t.porcentajeEstructura / total) * 100 }));
  });
}

function order(tipo: string): number {
  return tipo === 'SENIOR' ? 0 : tipo === 'MEZZANINE' ? 1 : 2;
}
