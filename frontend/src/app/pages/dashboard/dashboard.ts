import { Component, inject, signal, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DecimalPipe } from '@angular/common';
import { CarteraService } from '../../core/services/cartera';
import { Cartera, ResumenPlataforma } from '../../core/models/cartera';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink, DecimalPipe],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard implements OnInit {
  private carteraService = inject(CarteraService);

  resumen = signal<ResumenPlataforma | null>(null);
  carterasRecientes = signal<Cartera[]>([]);
  cargando = signal(true);
  error = signal<string | null>(null);

  ngOnInit(): void {
    this.carteraService.resumen().subscribe({
      next: (r) => this.resumen.set(r),
      error: () => this.error.set('No se ha podido conectar con la API. Comprueba que el backend está arrancado en el puerto 8080.')
    });

    this.carteraService.listar().subscribe({
      next: (carteras) => {
        this.carterasRecientes.set(carteras.slice(0, 5));
        this.cargando.set(false);
      },
      error: () => this.cargando.set(false)
    });
  }
}
