import { Component, inject, signal, computed, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DecimalPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CarteraService } from '../../core/services/cartera';
import { Cartera, EstadoCartera } from '../../core/models/cartera';

@Component({
  selector: 'app-cartera-list',
  imports: [RouterLink, FormsModule, DecimalPipe],
  templateUrl: './cartera-list.html',
  styleUrl: './cartera-list.scss'
})
export class CarteraList implements OnInit {
  private carteraService = inject(CarteraService);

  carteras = signal<Cartera[]>([]);
  cargando = signal(true);
  error = signal<string | null>(null);
  filtro = signal('');
  estadoFiltro = signal<EstadoCartera | 'TODOS'>('TODOS');

  carterasFiltradas = computed(() => {
    const texto = this.filtro().trim().toLowerCase();
    const estado = this.estadoFiltro();
    return this.carteras().filter((c) => {
      const coincideTexto = !texto || c.nombre.toLowerCase().includes(texto);
      const coincideEstado = estado === 'TODOS' || c.estado === estado;
      return coincideTexto && coincideEstado;
    });
  });

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.cargando.set(true);
    this.carteraService.listar().subscribe({
      next: (data) => {
        this.carteras.set(data);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('No se ha podido conectar con la API.');
        this.cargando.set(false);
      }
    });
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (!confirm('¿Eliminar esta cartera de titulización? Esta acción no se puede deshacer.')) return;
    this.carteraService.eliminar(id).subscribe(() => this.cargar());
  }
}
