import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {lastValueFrom, Observable} from 'rxjs';
import {IObjetctProduto, IProduto} from '../models/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor( public http: HttpClient ) { }

  api_url = environment.backend;

  public async postProduto(produto: IProduto) {
    await lastValueFrom(
      this.http.post<IProduto>(`${this.api_url}/api/v1/products`, produto)
    );
  }

  public async updateProduto(id:number, produto: IProduto) {
    await lastValueFrom(
      this.http.put<IProduto>(`${this.api_url}/api/v1/products/${id}`, produto)
    );
  }

  public getAllProdutos(): Observable<IObjetctProduto> {
    return this.http.get<IObjetctProduto>(`${this.api_url}/api/v1/products`);
  }

  public findProdutoById(id:number): Observable<IProduto> {
    return this.http.get<IProduto>(`${this.api_url}/api/v1/products/${id}`);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api_url}/api/v1/products/${id}`);
  }

}
