import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { ETipoAccionCRUD } from 'src/app/enums/tipo-accion';
import { ParametroDialogo } from 'src/app/models/ParametroDialogo';
import { TblSkillsetDTO } from 'src/app/models/TblSkillsetDTO';
import { SkillsetService } from 'src/app/services/skillset.service';
import { pgimAnimations } from 'src/shared/animations/pgim-animations';
import Swal from 'sweetalert2';
import { SkillsetDialogoComponent } from '../skillset-dialogo/skillset-dialogo.component';
import { SkillsetFotoComponent } from '../skillset-foto/skillset-foto.component';
import { SkillsetUploadComponent } from '../skillset-upload/skillset-upload.component';

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

  clienteSeleccionado: TblSkillsetDTO;

  constructor(private skillsetService: SkillsetService,
    // private confirmService: AppConfirmService,
    private dialog: MatDialog,
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
    let tblSkillsetDTO: TblSkillsetDTO;

    const parametroDialogo = new ParametroDialogo<TblSkillsetDTO, any>();

    parametroDialogo.accion = ETipoAccionCRUD.CREAR;
    parametroDialogo.objeto = new TblSkillsetDTO();
    parametroDialogo.objeto.idSkillset = 0;

    const dialogRef = this.dialog.open(SkillsetDialogoComponent, {
      disableClose: true,
      data: parametroDialogo,
      width: '60%',
    });

    dialogRef.afterClosed().subscribe(resultado => {
      if (parametroDialogo.resultado === 'ok') {
        tblSkillsetDTO = parametroDialogo.objeto;
        this.listarSkillset();
      }
    });
  }

  modificarSkillset(tblSkillsetDTO: TblSkillsetDTO): void {
    const parametroDialogo = new ParametroDialogo<TblSkillsetDTO, any>();

    parametroDialogo.accion = ETipoAccionCRUD.MODIFICAR;
    parametroDialogo.objeto = tblSkillsetDTO;

    const dialogRef = this.dialog.open(SkillsetDialogoComponent, {
      disableClose: true,
      data: parametroDialogo,
      width: '60%',
    });

    dialogRef.afterClosed().subscribe(resultado => {
      if (parametroDialogo.resultado === 'ok') {
        tblSkillsetDTO = parametroDialogo.objeto;
        this.listarSkillset();
      }
    });
  }

  subirFoto(tblSkillsetDTO: TblSkillsetDTO): void {
    const parametroDialogo = new ParametroDialogo<TblSkillsetDTO, any>();

    parametroDialogo.objeto = tblSkillsetDTO;
    // parametroDialogo.objeto.idSkillset = tblSkillsetDTO.idSkillset;
    const dialogRef = this.dialog.open(SkillsetFotoComponent, {
      disableClose: true,
      data: parametroDialogo,
      // width: '30%',
    });

    dialogRef.afterClosed().subscribe(resultado => {
      if (parametroDialogo.resultado === 'ok') {
        tblSkillsetDTO = parametroDialogo.objeto;
      }
      this.listarSkillset();
    });
  }

  eliminarSkillset(tblSkillsetDTO: TblSkillsetDTO) {
    Swal.fire({
      title: 'Está seguro?',
      text: `¿Seguro que desea eliminar el Skillset ${tblSkillsetDTO.noSkillset}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      cancelButtonText: 'No, cancelar!',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.skillsetService.eliminarSkillset(tblSkillsetDTO.idSkillset).subscribe(
          response => {
            this.lTblSkillsetDTO = this.lTblSkillsetDTO.filter(cli => cli !== tblSkillsetDTO)
            Swal.fire(
              'Skillset Eliminado!',
              `Skillset ${tblSkillsetDTO.noSkillset} eliminado con éxito.`,
              'success'
            )
          }
        )

        // swal.fire(
        //   `El cliente ${cliente.nombre} ${cliente.apellido}`,
        //   'ha sido eliminado.',
        //   'success'
        // )
      }
    })
  }
}
