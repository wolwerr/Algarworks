import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormCursosComponent } from './pages/cursos/form-cursos/form-cursos.component';
import { TableCursosComponent } from './pages/cursos/table-cursos/table-cursos.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialNgModule } from './material/material.module';
import { TableProdutosComponent } from './pages/produtos/table-produtos/table-produtos.component';
import { FormProdutosComponent } from './pages/produtos/form-produtos/form-produtos.component';

@NgModule({
  declarations: [
    AppComponent,
    FormCursosComponent,
    TableCursosComponent,
    TableProdutosComponent,
    FormProdutosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialNgModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],

    schemas: [
      CUSTOM_ELEMENTS_SCHEMA
    ]
})
export class AppModule { }
