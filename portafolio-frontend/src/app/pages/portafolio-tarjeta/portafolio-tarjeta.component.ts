import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TblPortafolioDTO } from 'src/app/models/TblPortafolioDTO';

@Component({
  selector: 'app-portafolio-tarjeta',
  templateUrl: './portafolio-tarjeta.component.html',
  styleUrls: ['./portafolio-tarjeta.component.scss']
})
export class PortafolioTarjetaComponent implements OnInit {

  @Input() tblPortafolioDTO: TblPortafolioDTO;

  @Output() eventoPortafolioSolicitadoParaModificar = new EventEmitter<number>();

  @Output() eventoPortafolioSolicitadoParaConsultar = new EventEmitter<number>();

  @Output() eventoPortafolioSolicitadoParaEliminar = new EventEmitter<TblPortafolioDTO>();

  constructor() { }

  ngOnInit(): void {
  }

  /**
   * Permite solicitar la modificación del portafolio.
   * @param idPortafolio Identificador interno de portafolio a modificar.
   */
  solicitarModificacion(idPortafolio: number) {
    this.eventoPortafolioSolicitadoParaModificar.emit(idPortafolio);
  }

  /**
   * Permite abrir el formulario de portafolio en modo lectura
   * @param idPortafolio Identificador de portafolio.
   */
  solicitarConsulta(idPortafolio: number) {
    this.eventoPortafolioSolicitadoParaConsultar.emit(idPortafolio);
  }

  /**
   * Permite abrir el formulario de consulta para confirmar la eliminación de un portafolio.
   * @param tblPortafolioDTO Objeto de portafolio.
   */
  solicitarEliminacion(tblPortafolioDTO: TblPortafolioDTO) {
    this.eventoPortafolioSolicitadoParaEliminar.emit(tblPortafolioDTO);
  }
}
