import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TblPortafolioDTO } from '../models/TblPortafolioDTO';

@Injectable({
  providedIn: 'root'
})
export class PortafolioService {

  private url = `${environment.HOST}/portafolios`;

  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = new HttpHeaders({ "Content-Type": "application/json" });
  }


  listarPortafolio(): Observable<TblPortafolioDTO[]> {

    const urlEndPoint = `${this.url}/listarPortafolio`;

    return this.httpClient.get<TblPortafolioDTO[]>(urlEndPoint, { headers: this.httpHeaders });

    // const urlEndPoint = `${this.url}/listarPortafolio`;

    // return this.httpClient.get<TblPortafolioDTO[]>(urlEndPoint,
    //   { headers: this.httpHeaders });
  }
}
