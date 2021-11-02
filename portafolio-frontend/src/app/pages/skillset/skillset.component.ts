import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { TblSkillsetDTO } from 'src/app/models/TblSkillsetDTO';
import { SkillsetService } from 'src/app/services/skillset.service';
import { pgimAnimations } from 'src/shared/animations/pgim-animations';

@Component({
  selector: 'app-skillset',
  templateUrl: './skillset.component.html',
  styleUrls: ['./skillset.component.scss'],
  animations: [pgimAnimations],
})
export class SkillsetComponent implements OnInit {

  /**
   * Señala si la carga de la lista de contratos se encuentra en curso.
   */
  enProceso = false;

  /**
  * Lista de contratos.
  */
  lTblSkillsetDTO: TblSkillsetDTO[];

  constructor(private skillsetService: SkillsetService,
    // private confirmService: AppConfirmService,
    private sanitization: DomSanitizer) { }

  ngOnInit(): void {
    // window.document.title = this.titulo;
    this.listarSkillset();
  }

  listarSkillset(): void {
    this.enProceso = true;

    this.skillsetService.listarSkillset().subscribe((respuesta) => {
      this.lTblSkillsetDTO = respuesta;
      // this.totalElements = respuesta.totalElements;

      this.enProceso = false;
    });
  }

  crearSkillset(): void {

  }

  eliminarCentro(tblSkillsetDTO: TblSkillsetDTO, indice: number) {
    // const contenidoMensaje = `Esta acción eliminará el registro de un centro de estudio <strong style="text-transform: uppercase;">${legCentroDTO.noCentro}</strong>
    // , si lo hace, ya no podrá acceder a su información.`;

    // this.confirmService.confirmar(
    //   {
    //     titulo: '¿Eliminar un centro de estudio?',
    //     mensaje: contenidoMensaje,
    //     nombreAccion: 'ELIMINAR'
    //   }).subscribe(respuesta => {
    //     if (respuesta) {
    //       // Se procede con la eliminación lógica.
    //       this.centroService.eliminarCentro(legCentroDTO.idCentro).subscribe(respuesta => {
    //         this.listarCentro();
    //       });
    //     }
    //   });
  }
}
