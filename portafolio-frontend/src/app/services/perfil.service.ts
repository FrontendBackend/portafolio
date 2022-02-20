import { map, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { TblPerfilDTO } from '../models/TblPerfilDTO';
import { TblUbigeoDTO } from '../models/TblUbigeoDTO';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  url = `${environment.HOST}/perfiles`;

  private httpHeaders: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  }

  obtenerPerfilPorId(idPerfil: number): Observable<any> {
    const urlEndPoint = `${this.url}/obtenerConfiguracionesGenerales/${idPerfil}`;
    return this.httpClient.get<any[]>(urlEndPoint);
  }

  modificarPerfil(tblPerfilDTO: TblPerfilDTO): Observable<TblPerfilDTO> {
    const urlEndPoint = `${this.url}/modificarPerfil`;
    const clavePersonaModificada = 'tblPerfilDTOModificada';
    return this.httpClient.put<TblPerfilDTO>(urlEndPoint, tblPerfilDTO, { headers: this.httpHeaders })
      .pipe(
        map((respuesta: any) => respuesta[clavePersonaModificada] as TblPerfilDTO)
      );
  }

  listarPorUbigeos(codigoUnico: string): Observable<TblUbigeoDTO[]> {
    if (codigoUnico === '') {
      codigoUnico = '_vacio_';
    }

    const urlEndPoint = `${this.url}/filtrar/palabraClave/${codigoUnico}`;
    const claveRespuesta = 'lTblUbigeoDTO';

    return this.httpClient.get<TblUbigeoDTO[]>(urlEndPoint, { headers: this.httpHeaders })
      .pipe(
        map((respuesta: any) => respuesta[claveRespuesta] as TblUbigeoDTO[]),
        catchError(error => this.manejarError(error))
      );
  }

  obtenerConfiguracionesGenerales(idPerfil: number): Observable<any> {
    const urlEndPoint = `${this.url}/obtenerConfiguracionesGenerales/${idPerfil}`;

    return this.httpClient.get<any[]>(urlEndPoint);
  }

  generarReporteCurriculum() {
    return this.httpClient.get(`${this.url}/generarReporteCurriculum`, {
      responseType: 'blob'
    });
  }

  /**
 * Permite analizar el error y responder un error específico para el usuario.
 * @param error que debe ser controlado.
 */
  private manejarError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // Se produjo un error del lado del cliente o de la red. Manejarlo en consecuencia.
      console.error('Un error ha ocurrido:', error.error.message);
    } else {
      // El backend devolvió un código de respuesta fallido.
      // El cuerpo de respuesta puede contener pistas sobre lo que salió mal.
      console.error(`Código devuelto por el servidor ${error.status}, el cuerpo es: ${error.error.error}`);
    }
    // retornar un mensaje observable con un mensaje de error orientado al usuario.
    console.log(error);

    const claveRespuesta = 'mensaje';
    const mensajeError = error.error[claveRespuesta] || error.message;

    return throwError(`${mensajeError}; por favor, vuelva a intentarlo más tarde.`);
  }
}
