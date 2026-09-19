import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CarteraService } from '../../core/services/cartera';

@Component({
  selector: 'app-cartera-form',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './cartera-form.html',
  styleUrl: './cartera-form.scss'
})
export class CarteraForm {
  private fb = inject(FormBuilder);
  private carteraService = inject(CarteraService);
  private router = inject(Router);

  enviando = signal(false);
  error = signal<string | null>(null);

  form = this.fb.nonNullable.group({
    nombre: ['', [Validators.required, Validators.minLength(3)]],
    fechaConstitucion: ['', Validators.required],
    moneda: ['EUR', Validators.required],
    estado: ['EN_CONSTITUCION' as const, Validators.required],
    valorNominalTotal: [0, [Validators.required, Validators.min(1)]]
  });

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.enviando.set(true);
    this.error.set(null);
    this.carteraService.crear(this.form.getRawValue()).subscribe({
      next: (creada) => this.router.navigate(['/carteras', creada.id]),
      error: () => {
        this.error.set('No se ha podido crear la cartera. Revisa los datos e inténtalo de nuevo.');
        this.enviando.set(false);
      }
    });
  }
}
