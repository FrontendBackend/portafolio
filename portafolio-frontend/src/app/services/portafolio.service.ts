import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
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
  }

  /**
   * Permite crear un centro de estudio.
   * @param tblPortafolioDTO,
   */

   crearPortafolio(tblPortafolioDTO: TblPortafolioDTO): Observable<TblPortafolioDTO> {
    const urlEndPoint = `${this.url}/crearPortafolio`;
    const clave = 'tblPortafolioDTOCreado';

    return this.httpClient.post<TblPortafolioDTO>(urlEndPoint, tblPortafolioDTO, { headers: this.httpHeaders })
      .pipe(
        map(respuesta => respuesta[clave] as TblPortafolioDTO )
      );
  }

  /**
   * Permite modificar un centro de estudio.
   * @param tblPortafolioDTO,
   */
   modificarPortafolio(tblPortafolioDTO: TblPortafolioDTO): Observable<TblPortafolioDTO> {
    const urlEndPoint = `${this.url}/modificarPortafolio`;
    const clave = 'tblPortafolioDTOModificada';
    return this.httpClient.put<TblPortafolioDTO>(urlEndPoint, tblPortafolioDTO, { headers: this.httpHeaders })
      .pipe(
        map(respuesta => respuesta[clave] as TblPortafolioDTO)
      );
  }

  /**
   * Permite obtener las configuraciones necesarias para el listado de centro de estudio.
   */
   obtenerConfiguracionesGenerales(idPortafolio: number): Observable<any> {
    const urlEndPoint = `${this.url}/obtenerConfiguracionesGenerales/${idPortafolio}`;
    return this.httpClient.get<any[]>(urlEndPoint);
  }

  /**
   * Permite analizar el error y responder un error específico para el usuario.
   * @param error que debe ser controlado.
   */
   private manejarError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // Se produjo un error del lado del cliente o de la red. Manejarlo en consecuencia.
      console.error("Un error ha ocurrido:", error.error.message);
    } else {
      // El backend devolvió un código de respuesta fallido.
      // El cuerpo de respuesta puede contener pistas sobre lo que salió mal.
      console.error(
        `Código devuelto por el servidor ${error.status}, el cuerpo es: ${error.error.error}`
      );
    }
    // retornar un mensaje observable con un mensaje de error orientado al usuario.
    console.log(error);

    const claveRespuesta = "mensaje";
    const mensajeError = error.error[claveRespuesta] || error.message;

    return throwError(
      `${mensajeError}; por favor, vuelva a intentarlo más tarde.`
    );
  }
}
