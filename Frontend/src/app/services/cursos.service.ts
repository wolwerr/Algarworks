import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {lastValueFrom, Observable} from 'rxjs';
import {ICursos} from '../models/cursos';
import {Page} from "../models/page.model";


@Injectable({
  providedIn: 'root'
})
export class CursosService {

  constructor( public http: HttpClient ) { }

  api_url = environment.backend;

  public async postCursos(cursos: ICursos) {
    await lastValueFrom(
      this.http.post<ICursos>(`${this.api_url}/courses`, cursos)
    );
  }

  public async updateCursos(id:number, cursos: ICursos) {
    await lastValueFrom(
      this.http.put<ICursos>(`${this.api_url}/courses/${id}`, cursos)
    );
  }

  public findAll(): Observable<Page<ICursos>> {
    return this.http.get<Page<ICursos>>(`${this.api_url}/courses`)
  }

  public findCursosById(id:number): Observable<ICursos> {
    return this.http.get<ICursos>(`${this.api_url}/courses/${id}`);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api_url}/courses/${id}`);
  }

}
