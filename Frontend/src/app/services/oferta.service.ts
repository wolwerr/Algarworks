import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {IOferta} from "../models/oferta";
import {Page} from "../models/page.model";

export class OfertaService {
  constructor(private http: HttpClient) { }

  api_url = environment.backend;

  findOfertaByCode(code: string): Observable<IOferta> {
    return this.http.get<IOferta>(`${environment.backend}/api/v1/offers/${code}`)
  }

  register(requestBody: IOferta): Observable<IOferta> {
    return this.http.post<IOferta>(`${environment.backend}/api/v1/offers`, requestBody)
  }

  update(requestBody: IOferta): Observable<IOferta> {
    return this.http.put<IOferta>(`${environment.backend}/api/v1/offers/${requestBody.code}`, requestBody)
  }

  delete(code: string): Observable<void> {
    return this.http.delete<void>(`${environment.backend}/api/v1/offers/${code}`)
  }

  findAll(): Observable<Page<IOferta>> {

    return this.http.get<Page<IOferta>>(`${environment.backend}/api/v1/offers`)
  }
}
