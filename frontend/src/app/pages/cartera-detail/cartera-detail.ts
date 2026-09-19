import { Component, inject, signal, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { DecimalPipe } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarteraService } from '../../core/services/cartera';
import { Cartera } from '../../core/models/cartera';

@Component({
  selector: 'app-cartera-detail',
  imports: [RouterLink, ReactiveFormsModule, DecimalPipe],
  templateUrl: './cartera-detail.html',
  styleUrl: './cartera-detail.scss'
})
export class CarteraDetail implements OnInit {
  private route = inject(ActivatedRoute);
  private carteraService = inject(CarteraService);
  private fb = inject(FormBuilder);

  cartera = signal<Cartera | null>(null);
  cargando = signal(true);
  error = signal<string | null>(null);

  mostrarFormActivo = signal(false);
  mostrarFormTramo = signal(false);

  formActivo = this.fb.nonNullable.group({
    tipoActivo: ['HIPOTECARIO' as const, Validators.required],
    valorNominal: [0, [Validators.required, Validators.min(1)]],
    tasaInteres: [0, [Validators.required, Validators.min(0)]],
    plazoMeses: [12, [Validators.required, Validators.min(1)]],
    calificacionRiesgo: ['A' as const, Validators.required]
  });

  formTramo = this.fb.nonNullable.group({
    nombre: ['', Validators.required],
    tipo: ['SENIOR' as const, Validators.required],
    porcentajeEstructura: [0, [Validators.required, Validators.min(1), Validators.max(100)]],
    calificacionCrediticia: ['AAA' as const, Validators.required],
    tasaCupon: [0, [Validators.required, Validators.min(0)]],
    importeNominal: [0, [Validators.required, Validators.min(1)]]
  });

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargar(id);
  }

  private cargar(id: number): void {
    this.cargando.set(true);
    this.carteraService.obtener(id).subscribe({
      next: (c) => {
        this.cartera.set(c);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('No se ha encontrado la cartera solicitada.');
        this.cargando.set(false);
      }
    });
  }

  guardarActivo(): void {
    const c = this.cartera();
    if (!c?.id || this.formActivo.invalid) {
      this.formActivo.markAllAsTouched();
      return;
    }
    this.carteraService.agregarActivo(c.id, this.formActivo.getRawValue()).subscribe(() => {
      this.formActivo.reset({ tipoActivo: 'HIPOTECARIO', valorNominal: 0, tasaInteres: 0, plazoMeses: 12, calificacionRiesgo: 'A' });
      this.mostrarFormActivo.set(false);
      this.cargar(c.id!);
    });
  }

  guardarTramo(): void {
    const c = this.cartera();
    if (!c?.id || this.formTramo.invalid) {
      this.formTramo.markAllAsTouched();
      return;
    }
    this.carteraService.agregarTramo(c.id, this.formTramo.getRawValue()).subscribe(() => {
      this.formTramo.reset({ nombre: '', tipo: 'SENIOR', porcentajeEstructura: 0, calificacionCrediticia: 'AAA', tasaCupon: 0, importeNominal: 0 });
      this.mostrarFormTramo.set(false);
      this.cargar(c.id!);
    });
  }

  tramoClass(tipo: string): string {
    return tipo.toLowerCase();
  }
}
