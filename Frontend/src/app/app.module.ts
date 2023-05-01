import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule, Pipe } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CurrencyMaskModule} from 'ng2-currency-mask';
import {NgxMaskModule} from 'ngx-mask';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {MaterialNgModule} from './material/material.module';
import {FormCursoComponent} from './pages/curso/form-curso/form-curso.component';
import {HomeComponent} from './pages/home/home.component';
import {FooterComponent} from './shared/footer/footer.component';
import {HeaderComponent} from './shared/header/header.component';
import {FormProdutoComponent} from './pages/produto/form-produto/form-produto.component';
import {TableCursoComponent} from './pages/curso/table-curso/table-curso.component';
import {HomeLayoutComponent} from "./pages/home-layout/home-layout.component";
import { LoaderComponent} from "./shared/loader/loader.component";
import { LoaderInterceptor} from "./interceptor/loader.interceptor";
import { TableProdutoComponent } from './pages/produto/table-produto/table-produto.component';
import { CarouselComponent } from './pages/home/carousel/carousel.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormOfertaComponent } from './pages/oferta/form-oferta/form-oferta.component';
import { SearchBarComponent} from "./shared/search-bar/search-bar.component";
import { TableOfertaComponent } from './pages/oferta/table-oferta/table-oferta.component';
import { TranslateStatusPipe } from './pages/produto/table-produto/translate-status.pipe';
import { TranslateActivePipe } from './pages/oferta/table-oferta/translate-active.pipe';
import { DataPipe } from './pages/oferta/table-oferta/translate-data.pipe';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        HomeComponent,
        FooterComponent,
        FormCursoComponent,
        FormProdutoComponent,
        TableCursoComponent,
        HomeLayoutComponent,
        LoaderComponent,
        TableProdutoComponent,
        CarouselComponent,
        FormOfertaComponent,
        SearchBarComponent,
        TableOfertaComponent,
        TranslateStatusPipe,
        TranslateActivePipe,
        DataPipe
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MaterialNgModule,
        ReactiveFormsModule,
        FormsModule,
        NgxMaskModule.forRoot(),
        CurrencyMaskModule,
        FontAwesomeModule,
        HttpClientModule
    ],
    providers: [


      {
        provide: HTTP_INTERCEPTORS,
        useClass: LoaderInterceptor,
        multi: true,
      },
    ],
    bootstrap: [AppComponent],
    exports: [
        HeaderComponent
    ],
    schemas: [
      CUSTOM_ELEMENTS_SCHEMA
    ]
})
export class AppModule {}
