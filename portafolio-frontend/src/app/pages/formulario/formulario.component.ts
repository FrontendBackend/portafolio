import { environment } from 'src/environments/environment';
import { Component, OnInit } from '@angular/core';
import { TblPerfilDTO } from 'src/app/models/TblPerfilDTO';
import { PerfilService } from 'src/app/services/perfil.service';
import { pgimAnimations } from 'src/shared/animations/pgim-animations';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.scss'],
  animations: [pgimAnimations],
})
export class FormularioComponent implements OnInit {

  enProceso = false;

  lTblPerfil: TblPerfilDTO[];

  // titulo = 'Formulario';
  host_img_portafolio = environment.HOST_IMG_PORTAFOLIO;

  constructor(private perfilService: PerfilService) { }

  ngOnInit(): void {
    // window.document.title = this.titulo;
    this.listarPerfil();
  }


  /**
   * Permite listar los portafolios.
   */
  listarPerfil(): void {
    this.enProceso = true;

    this.perfilService.listarPerfil().subscribe((respuesta) => {
      this.lTblPerfil = respuesta;
      // this.totalElements = respuesta.totalElements;

      this.enProceso = false;
    });
  }

  modificarPerfil(event:any): void{

  }

  eliminarPerfil(event:any): void{

  }
}
