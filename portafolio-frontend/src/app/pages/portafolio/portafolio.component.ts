import Swal from 'sweetalert2';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ETipoAccionCRUD } from 'src/app/enums/tipo-accion';
import { ParametroDialogo } from 'src/app/models/ParametroDialogo';
import { TblPortafolioDTO } from 'src/app/models/TblPortafolioDTO';
import { PortafolioService } from 'src/app/services/portafolio.service';
import { pgimAnimations } from 'src/shared/animations/pgim-animations';
import { PortafolioDialogoComponent } from '../portafolio-dialogo/portafolio-dialogo.component';

@Component({
  selector: 'app-portafolio',
  templateUrl: './portafolio.component.html',
  styleUrls: ['./portafolio.component.scss'],
  animations: [pgimAnimations],
})
export class PortafolioComponent implements OnInit {

  titulo = 'Portafolio';

  /**
   * Señala si la carga de la lista de contratos se encuentra en curso.
   */
  enProceso = false;

  /**
   * Lista de contratos.
   */
  lTblPortafolioDTO: TblPortafolioDTO[];

  constructor(
    private portafolioService: PortafolioService,
    private dialog: MatDialog,) {
  }

  ngOnInit(): void {
    // window.document.title = this.titulo;
    this.listarPortafolio();
  }

  /**
   * Permite listar los portafolios.
   */
  listarPortafolio(): void {
    this.enProceso = true;

    this.portafolioService.listarPortafolio().subscribe((respuesta) => {
      this.lTblPortafolioDTO = respuesta;
      // this.totalElements = respuesta.totalElements;

      this.enProceso = false;
    });
  }

  /**
   * Permite iniciar la creación de un portafolio.
   */
  crearPortafolio(): void {
    let tblPortafolioDTO: TblPortafolioDTO;

    const parametroDialogo = new ParametroDialogo<TblPortafolioDTO, any>();

    parametroDialogo.accion = ETipoAccionCRUD.CREAR;
    parametroDialogo.objeto = new TblPortafolioDTO();
    parametroDialogo.objeto.idPortafolio = 0;

    const dialogRef = this.dialog.open(PortafolioDialogoComponent, {
      disableClose: true,
      data: parametroDialogo,
      width: '120%',
    });

    dialogRef.afterClosed().subscribe(resultado => {
      if (parametroDialogo.resultado === 'ok') {
        tblPortafolioDTO = parametroDialogo.objeto;
        this.listarPortafolio();
      }
    });
  }

  modificarPortafolio(tblPortafolioDTO: TblPortafolioDTO): void {
    const parametroDialogo = new ParametroDialogo<TblPortafolioDTO, any>();

    parametroDialogo.accion = ETipoAccionCRUD.MODIFICAR;
    parametroDialogo.objeto = tblPortafolioDTO;

    const dialogRef = this.dialog.open(PortafolioDialogoComponent, {
      disableClose: true,
      data: parametroDialogo,
      width: '120%',
    });

    dialogRef.afterClosed().subscribe(resultado => {
      if (parametroDialogo.resultado === 'ok') {
        tblPortafolioDTO = parametroDialogo.objeto;
        this.listarPortafolio();
      }
    });
  }

  eliminarPortafolio(tblPortafolioDTO: TblPortafolioDTO) {
    Swal.fire({
      title: 'Está seguro?',
      text: `¿Seguro que desea eliminar el Skillset ${tblPortafolioDTO.noPortafolio}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      cancelButtonText: 'No, cancelar!',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.portafolioService.eliminarPortafolio(tblPortafolioDTO.idPortafolio).subscribe(
          () => {
            this.lTblPortafolioDTO = this.lTblPortafolioDTO.filter(cli => cli !== tblPortafolioDTO)
            Swal.fire(
              'Portafolio eliminado!',
              `Portafolio ${tblPortafolioDTO.noPortafolio} eliminado con éxito.`,
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
