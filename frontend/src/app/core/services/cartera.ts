import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cartera, Activo, Tramo, ResumenPlataforma } from '../models/cartera';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class CarteraService {
  private http = inject(HttpClient);
  private baseUrl = `${environment.apiUrl}/carteras`;

  listar(): Observable<Cartera[]> {
    return this.http.get<Cartera[]>(this.baseUrl);
  }

  obtener(id: number): Observable<Cartera> {
    return this.http.get<Cartera>(`${this.baseUrl}/${id}`);
  }

  crear(cartera: Cartera): Observable<Cartera> {
    return this.http.post<Cartera>(this.baseUrl, cartera);
  }

  actualizar(id: number, cartera: Cartera): Observable<Cartera> {
    return this.http.put<Cartera>(`${this.baseUrl}/${id}`, cartera);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  agregarActivo(carteraId: number, activo: Activo): Observable<Activo> {
    return this.http.post<Activo>(`${this.baseUrl}/${carteraId}/activos`, activo);
  }

  agregarTramo(carteraId: number, tramo: Tramo): Observable<Tramo> {
    return this.http.post<Tramo>(`${this.baseUrl}/${carteraId}/tramos`, tramo);
  }

  resumen(): Observable<ResumenPlataforma> {
    return this.http.get<ResumenPlataforma>(`${environment.apiUrl}/dashboard/resumen`);
  }
}
