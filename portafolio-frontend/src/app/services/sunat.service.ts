import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SunatService {

  // private url = `${environment.HOST}/skillsets`;
  private url = `https://api.apis.net.pe/v1`;

  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  obtenerDatosSunat(numero: number): Observable<any> {
    const urlEndPoint = `${this.url}/dni?numero=${numero}`;
    return this.httpClient.get<any[]>(urlEndPoint);
  }
}
