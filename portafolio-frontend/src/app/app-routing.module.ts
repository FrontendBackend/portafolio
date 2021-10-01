import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'pages'
  },

  {
    path: 'pages',
    component: LayoutComponent,
    loadChildren: () => import('./pages/pages.module').then(m => m.PagesModule),
    // canActivate: [SecureInnerPagesGuard],
    // canActivate: [GuardService],
    // children: Content
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
