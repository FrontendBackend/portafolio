import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TblSkillsetDTO } from '../models/TblSkillsetDTO';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class SkillsetService {

  private url = `${environment.HOST}/skillsets`;

  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = new HttpHeaders({ "Content-Type": "application/json" });
  }

  listarSkillset(): Observable<TblSkillsetDTO[]> {
    const urlEndPoint = `${this.url}/listarSkillset`;
    return this.httpClient.get<TblSkillsetDTO[]>(urlEndPoint, { headers: this.httpHeaders });
  }

  /**
   * Permite crear un skillsets.
   * @param tblSkillsetDTO,
   */
  crearSkillset(tblSkillsetDTO: TblSkillsetDTO): Observable<TblSkillsetDTO> {
    const urlEndPoint = `${this.url}/crearSkillset`;
    const clave = 'tblSkillsetDTOCreado';

    return this.httpClient.post<TblSkillsetDTO>(urlEndPoint, tblSkillsetDTO, { headers: this.httpHeaders })
      .pipe(
        map((respuesta: any) => respuesta[clave] as TblSkillsetDTO)
      );
  }

  /**
   * Permite modificar un skillsets.
   * @param tblSkillsetDTO,
   */
  modificarSkillset(tblSkillsetDTO: TblSkillsetDTO): Observable<TblSkillsetDTO> {
    const urlEndPoint = `${this.url}/modificarSkillset`;
    const clave = 'tblSkillsetDTOModificada';
    return this.httpClient.put<TblSkillsetDTO>(urlEndPoint, tblSkillsetDTO, { headers: this.httpHeaders })
      .pipe(
        map((respuesta: any) => respuesta[clave] as TblSkillsetDTO)
      );
  }

  /**
  * Permite eliminar un skillset.
  * @param idSkillset Identificador interno de skillset para la eliminación.
  */
  eliminarSkillset(idSkillset: number) {
    const urlEndPoint = `${this.url}/eliminarSkillset/${idSkillset}`;

    return this.httpClient.delete<any>(urlEndPoint, { headers: this.httpHeaders })
      .pipe(
        map(respuesta => {
          if (respuesta['mensaje'] === 'ok') {
            return true;
          }
        })
      );
  }

  /**
   * Permite obtener las configuraciones necesarias para el listado de skillsets.
   */
  obtenerConfiguracionesGenerales(idSkillset: number): Observable<any> {
    const urlEndPoint = `${this.url}/obtenerConfiguracionesGenerales/${idSkillset}`;
    return this.httpClient.get<any[]>(urlEndPoint);
  }

  subirFoto(archivo: File, id: any): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);

    const req = new HttpRequest('POST', `${this.url}/upload`, formData, {
      reportProgress: true
    });

    return this.httpClient.request(req);
  }
}
