import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom, Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { IObjetctCurso, ICurso } from '../models/curso';

@Injectable({
  providedIn: 'root',
})
export class CursoService {
  constructor(public http: HttpClient) {}

  api_url = environment.backend;

  public async postCurso(curso: ICurso) {
    await lastValueFrom(
      this.http.post<ICurso>(`${this.api_url}/api/v1/courses`, curso)
    );
  }

  public async updateCurso(id:number, curso: ICurso) {
    await lastValueFrom(
      this.http.put<ICurso>(`${this.api_url}/api/v1/courses/${id}`, curso)
    );
  }

  public getAllCursos(): Observable<IObjetctCurso>{
    return this.http.get<IObjetctCurso>(`${this.api_url}/api/v1/courses`);
  }

  public findCursoById(id:number): Observable<ICurso> {
    return this.http.get<ICurso>(`${this.api_url}/api/v1/courses/${id}`);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api_url}/api/v1/courses/${id}`);
  }

}
