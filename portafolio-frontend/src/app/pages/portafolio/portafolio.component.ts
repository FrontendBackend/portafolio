import { Component, OnInit } from '@angular/core';
import { TblPortafolioDTO } from 'src/app/models/TblPortafolioDTO';
import { PortafolioService } from 'src/app/services/portafolio.service';
import { pgimAnimations } from 'src/shared/animations/pgim-animations';

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
    private portafolioService: PortafolioService,) {
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
}
