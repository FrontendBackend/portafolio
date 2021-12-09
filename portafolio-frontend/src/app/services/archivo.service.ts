import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ArchivoService {

  private url = `${environment.HOST}/archivos`;

  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = new HttpHeaders({ "Content-Type": "application/json" });
  }

  guardarArchivo(data: File){
    let formdata: FormData = new FormData();
    formdata.append('adjunto', data);
    return this.httpClient.post(`${this.url}/guardarArchivo`, formdata)
  }

  leerArchivo() {
    return this.httpClient.get(`${this.url}/leerArchivo/2`, {
      responseType: 'blob'
    });
  }
}
