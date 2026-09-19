import { Routes } from '@angular/router';
import { Dashboard } from './pages/dashboard/dashboard';
import { CarteraList } from './pages/cartera-list/cartera-list';
import { CarteraDetail } from './pages/cartera-detail/cartera-detail';
import { CarteraForm } from './pages/cartera-form/cartera-form';

export const routes: Routes = [
  { path: '', component: Dashboard, title: 'Panel · Titulización' },
  { path: 'carteras', component: CarteraList, title: 'Carteras · Titulización' },
  { path: 'carteras/nueva', component: CarteraForm, title: 'Nueva cartera · Titulización' },
  { path: 'carteras/:id', component: CarteraDetail, title: 'Detalle de cartera · Titulización' },
  { path: '**', redirectTo: '' }
];
