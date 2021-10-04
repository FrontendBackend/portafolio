import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SkillsetService {

  private url = `${environment.HOST}/skillsets`;

  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = new HttpHeaders({ "Content-Type": "application/json" });
  }

  crearSkillset(data: File){ //medico: Medico
    let formdata: FormData = new FormData();
    formdata.append('adjunto', data);
    //const medicoBlob = new Blob([JSON.stringify(medico)], { type: "application/json" });
    //formdata.append('medico', medicoBlob)

    return this.httpClient.post(`${this.url}/crearSkillset`, formdata);
  }

  listarSkillset() {
    return this.httpClient.get(`${this.url}/listarSkillset/1`, {
      responseType: 'blob'
    });
  }
}
