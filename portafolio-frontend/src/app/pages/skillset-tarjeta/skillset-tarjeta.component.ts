import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TblSkillsetDTO } from 'src/app/models/TblSkillsetDTO';
import { SkillsetService } from 'src/app/services/skillset.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-skillset-tarjeta',
  templateUrl: './skillset-tarjeta.component.html',
  styleUrls: ['./skillset-tarjeta.component.scss']
})
export class SkillsetTarjetaComponent implements OnInit {

  @Input() tblSkillsetDTO: TblSkillsetDTO;

  host_img = environment.HOST_IMG;
  // host_img_upload = `${environment.HOST_IMG_UPLOAD}`;


  @Output() eventoSkillsetSolicitadoParaModificar = new EventEmitter<TblSkillsetDTO>();

  @Output() eventoSkillsetSolicitadoParaConsultar = new EventEmitter<number>();

  @Output() eventoSkillsetSolicitadoParaEliminar = new EventEmitter<TblSkillsetDTO>();

  @Output() eventoSkillsetSolicitadoParaSubirFoto = new EventEmitter<TblSkillsetDTO>();

  constructor(private skillsetService: SkillsetService) { }

  ngOnInit(): void {
  }

  /**
    * Permite solicitar la modificación del skillset.
    * @param idSkillset Identificador interno de skillset a modificar.
    */
  solicitarModificacion(tblSkillsetDTO: TblSkillsetDTO) {
    this.eventoSkillsetSolicitadoParaModificar.emit(tblSkillsetDTO);
  }

  /**
   * Permite abrir el formulario de skillset en modo lectura
   * @param idSkillset Identificador de skillset.
   */
  solicitarConsulta(idSkillset: number) {
    this.eventoSkillsetSolicitadoParaConsultar.emit(idSkillset);
  }

  /**
   * Permite abrir el formulario de consulta para confirmar la eliminación de un skillset.
   * @param tblSkillsetDTO Objeto de skillset.
   */
  solicitarEliminacion(tblSkillsetDTO: TblSkillsetDTO) {
    this.eventoSkillsetSolicitadoParaEliminar.emit(tblSkillsetDTO);
  }

  solicitarSubidaFoto(tblSkillsetDTO: TblSkillsetDTO) {
    // this.skillsetService.abrirModal();
    this.eventoSkillsetSolicitadoParaSubirFoto.emit(tblSkillsetDTO);
  }
}
