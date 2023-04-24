import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom, Observable } from 'rxjs';
import {Page} from "../models/page.model";
import { environment } from '../../environments/environment';
import { IProdutos } from '../models/produtos';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  constructor(public http: HttpClient) {}

  api_url = environment.backend;

  public async postProdutos(produtos: IProdutos) {
    await lastValueFrom(
      this.http.post<IProdutos>(`${this.api_url}/products`, produtos)
    );
  }

  public async updateProdutos(id:number, produtos: IProdutos) {
    await lastValueFrom(
      this.http.put<IProdutos>(`${this.api_url}/products/${id}`, produtos)
    );
  }

  public findAll(): Observable<Page<IProdutos>> {
    return this.http.get<Page<IProdutos>>(`${this.api_url}/products`)
  }

  public findProdutosById(id:number): Observable<IProdutos> {
    return this.http.get<IProdutos>(`${this.api_url}/products/${id}`);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api_url}/products/${id}`);
  }

}

