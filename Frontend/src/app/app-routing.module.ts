import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FormCursoComponent} from './pages/curso/form-curso/form-curso.component';
import { FormProdutoComponent} from './pages/produto/form-produto/form-produto.component';
import { HomeComponent} from './pages/home/home.component';
import { TableCursoComponent } from './pages/curso/table-curso/table-curso.component';
import { HomeLayoutComponent} from "./pages/home-layout/home-layout.component";
import { TableProdutoComponent } from './pages/produto/table-produto/table-produto.component';
import { FormOfertaComponent } from "./pages/oferta/form-oferta/form-oferta.component";
import { TableOfertaComponent } from "./pages/oferta/table-oferta/table-oferta.component";


const routes: Routes = [
  {path: '', redirectTo: 'home', data: {title: 'First Component'}, pathMatch: 'full'},
  {
    path: 'home', component: HomeLayoutComponent, data: {title: 'First Component'},
    children: [
      {path: '', component: HomeComponent}
    ]
  },
  {
    path: 'main', component: HomeLayoutComponent,
    children: [

    {path: '**', redirectTo: 'home'},
    {
      path: '',
      component: HomeComponent,
    },
  ],
  },{
    path: 'main', component: HomeLayoutComponent ,
    children: [

      {
        path: 'cadastrocurso',
        component: FormCursoComponent,
      },
      {
        path: 'cadastrocurso/:id',
        component: FormCursoComponent,
      },
      {
        path: 'cadastroproduto',
        component: FormProdutoComponent,
      },
      {
        path: 'cadastrooferta',
        component: FormOfertaComponent,
      },
      {
        path: 'cadastrooferta/:code',
        component: FormOfertaComponent,
      },
      {
        path: 'cadastroproduto/:id',
        component: FormProdutoComponent,
      },
      {
        path: 'curso',
        component: TableCursoComponent,
      },
      {
        path: 'produto',
        component: TableProdutoComponent,
      },
      {
        path: 'oferta',
        component: TableOfertaComponent,
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
