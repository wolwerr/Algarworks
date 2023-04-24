import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Injectable} from '@angular/core';
import {lastValueFrom, Observable} from "rxjs";
import {IOfertas} from "../models/ofertas";
import {Page} from "../models/page.model";

@Injectable({
  providedIn: 'root'
})
export class OfertasService {

  constructor(private http: HttpClient) { }

  api_url = environment.backend;

  public async postOfertas(ofertas: IOfertas){
    await lastValueFrom(
      this.http.post<IOfertas>(`${this.api_url}/offers`, ofertas)
    );
  }

  public async updateOfertas(code:string, ofertas: IOfertas) {
    await lastValueFrom(
      this.http.put<IOfertas>(`${this.api_url}/offers/${code}`, ofertas)
    );
  }

  public findOfertasById(code:string,): Observable<IOfertas> {
    return this.http.get<IOfertas>(`${this.api_url}/offers/${code}`)
  }

  public delete(code:string,): Observable<void> {
    return this.http.delete<void>(`${this.api_url}/offers/${code}`)
  }

  public findAll(): Observable<Page<IOfertas>> {
    return this.http.get<Page<IOfertas>>(`${this.api_url}/offers`)
  }
}

