import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { ActivatedRoute, Router } from '@angular/router';
import { ETipoAccionCRUD } from 'src/app/enums/tipo-accion';
import { TblEducacionDTO } from 'src/app/models/TblEducacionDTO';
import { TblPerfilDTO } from 'src/app/models/TblPerfilDTO';
import { PerfilService } from 'src/app/services/perfil.service';

@Component({
  selector: 'app-perfil-formulario',
  templateUrl: './perfil-formulario.component.html',
  styleUrls: ['./perfil-formulario.component.scss']
})
export class PerfilFormularioComponent implements OnInit {

  @ViewChild(MatSidenav)
  private sideNav: MatSidenav;

  tblPerfilDTO: TblPerfilDTO;

  idPerfil: TblPerfilDTO;

  tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  textoRetorno = '';

  pestaniaSeleccionada = 0;

  idTipoDocIdentidad: number;
  flConsorcio: string;

  enProceso: boolean;

  origen = 'defecto';

  testConsorcio: string;

  constructor(private router: Router,
    private actividateRoute: ActivatedRoute,
    private perfilService: PerfilService) {
    this.enProceso = false;
    this.pestaniaSeleccionada = 2;
  }

  ngOnInit(): void {
    this.enProceso = true;

    this.actividateRoute.params.subscribe((respuesta: any) => {
      const claveIdPersona = 'idPerfil';
      const claveNroPestania = 'nroPestania';

      const accion = this.actividateRoute.snapshot.url[0].path;

      if (accion === ETipoAccionCRUD.CREAR.toString()) {
        this.tipoAccionCrud = ETipoAccionCRUD.CREAR;
        this.tblPerfilDTO = new TblPerfilDTO();
        this.tblPerfilDTO.idPerfil = 0;
        this.enProceso = false;
      }
      else if (accion === ETipoAccionCRUD.MODIFICAR.toString()) {
        this.tipoAccionCrud = ETipoAccionCRUD.MODIFICAR;

        const idPerfil = +respuesta[claveIdPersona];

        if (respuesta[claveNroPestania]) {
          this.pestaniaSeleccionada = +respuesta[claveNroPestania];
        }

        this.obtenerPerfilPorId(idPerfil);
      }
      else if (accion === ETipoAccionCRUD.CONSULTAR.toString()) {
        this.tipoAccionCrud = ETipoAccionCRUD.CONSULTAR;

        const idPerfil = +respuesta[claveIdPersona];

        if (respuesta[claveNroPestania]) {
          this.pestaniaSeleccionada = +respuesta[claveNroPestania];
        }

        this.obtenerPerfilPorId(idPerfil);
      }

    });
  }

  /**
   * Permite obtener los datos de perfil de la persona
   * @param idPerfil,
   */
  obtenerPerfilPorId(idPerfil: number): void {
    this.perfilService.obtenerPerfilPorId(idPerfil = 1).subscribe((respuesta: any) => {
      const clavePgimPersonaDTO = 'tblPerfilDTO';
      this.tblPerfilDTO = respuesta[clavePgimPersonaDTO] as TblPerfilDTO;
      // console.log(this.tblPerfilDTO);

      this.enProceso = false;
    });
  }

  /**
   * Permite la Creacion de la persona natural o juridica
   *
   * @param pgimPersonaDTOCreado;
   */
  eventoPersonaCreado(pgimPersonaDTOCreado: TblPerfilDTO) {

    if (this.origen === 'defecto') {
      this.router.navigate(['/info-maestra/persona/2', pgimPersonaDTOCreado.idPerfil, 0]);
    }
  }

  eventoPerfilModificado(pgimPersonaDTOModificado: TblPerfilDTO) {
    this.tblPerfilDTO = pgimPersonaDTOModificado;
  }

  get habilitarOtrasPestanias(): boolean {
    let habilitar = false;

    if (this.tipoAccionCrud === ETipoAccionCRUD.MODIFICAR || this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      habilitar = true;
    }

    return habilitar;
  }


  toggleSideNav() {
    this.sideNav.opened = !this.sideNav.opened;
  }

  /**
   * Me permite retornar a la lista de personas juridicas y naturales
   */
  retornar() {
    if (this.origen === 'defecto') {
      this.router.navigate(['/pages']);
    }
  }
}
