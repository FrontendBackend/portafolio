import { environment } from './../../../../environments/environment';
import { Observable } from 'rxjs';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ETipoAccionCRUD } from 'src/app/enums/tipo-accion';
import { TblPerfilDTO } from 'src/app/models/TblPerfilDTO';
import { PerfilService } from 'src/app/services/perfil.service';

@Component({
  selector: 'app-perfil-general',
  templateUrl: './perfil-general.component.html',
  styleUrls: ['./perfil-general.component.scss']
})
export class PerfilGeneralComponent implements OnInit {

  @Input() tblPerfilDTO: TblPerfilDTO;

  @Input() tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  @Input() idPersona: number;

  @Output() eventoPerfilModificado = new EventEmitter<TblPerfilDTO>();

  enProceso = false;

  controlesNovalidos: any[];

  esFormularioSoloLectura = false;

  frmReactivo: FormGroup;

  host_img_portafolio = environment.HOST_IMG_PERFIL;

  constructor(private formBuilder: FormBuilder,
    private perfilService: PerfilService,
    private matSnackBar: MatSnackBar,
    ) { }

  ngOnInit(): void {
    this.iniciarFormulario();
    // this.configurarInicio();
  }

  iniciarFormulario() {

    if (this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }

    let dateFeNacimiento: any;
    if (this.tblPerfilDTO.feNacimientoPerfil != null) {
      dateFeNacimiento = new Date(this.tblPerfilDTO.feNacimientoPerfil);
    }

    this.frmReactivo = this.formBuilder.group(
      {

        // Identificación
        nuDniPerfil: [this.tblPerfilDTO.nuDniPerfil, [Validators.required]],
        noPerfil: [this.tblPerfilDTO.noPerfil, [Validators.required]],
        apPerfil: [this.tblPerfilDTO.apPerfil, [Validators.required]],
        dirPerfil: [this.tblPerfilDTO.dirPerfil],
        telPerfil: [this.tblPerfilDTO.telPerfil],
        emailPerfil: [this.tblPerfilDTO.emailPerfil],
        feNacimientoPerfil: [dateFeNacimiento],
        // imgPerfil: [this.tblPerfilDTO.imgPerfil],
        // tipoImg: [this.tblPerfilDTO.tipoImg],
        // codImg: [this.tblPerfilDTO.codImg],
      }
    );

    if (this.esFormularioSoloLectura) {
      Object.values(this.frmReactivo.controls).forEach(control => control.disable());
    }
  }

  /**
   * Permite verificar si el control dado por el nombre tiene algún tipo de error.
   * @param campo Nombre del control.
   * @param error Error buscado.
   */
  tieneError(campo: string, error: string): boolean {
    if (error === 'any' || error === '') {
      return (
        this.frmReactivo.get(campo).invalid
      );
    }

    return (
      this.frmReactivo.get(campo).hasError(error)
    );
  }

  enviar() {
    // this.buscarControlesNoValidos();
    if (this.frmReactivo.invalid) {
      Object.values(this.frmReactivo.controls).forEach(control => control.markAllAsTouched());

      this.matSnackBar.open('Existen datos incorrectos o faltantes, por favor verifique', 'CERRAR', {
        duration: 4000
      });
      return;
    }

    this.procesarCrearOModificar();
  }


  /**
   * Permite procesar la acción de crear o modificar una persona.
   */
  procesarCrearOModificar() {
    this.enProceso = true;

    let pgimContratoDTOCU = new TblPerfilDTO();
    pgimContratoDTOCU = this.frmReactivo.value;

    // Recuperando las propiedades originales
    pgimContratoDTOCU.esRegistro = this.tblPerfilDTO.esRegistro;
    pgimContratoDTOCU.idPerfil = this.tblPerfilDTO.idPerfil;


    let peticion: Observable<TblPerfilDTO>;
    let mensaje: string;

    switch (+this.tipoAccionCrud) {
      // case ETipoAccionCRUD.CREAR:
      //   peticion = this.perfilService.crearPerfil(pgimContratoDTOCU);
      //   mensaje = 'Genial, la persona ha sido creada';
      //   break;
      case ETipoAccionCRUD.MODIFICAR:
        peticion = this.perfilService.modificarPerfil(pgimContratoDTOCU);
        mensaje = 'Genial, la persona ha sido modificada';
        break;
      default:
        console.log('Ninguna acción implementada');
        break;
    }

    if (peticion) {
      peticion.subscribe(respuesta => {
        this.matSnackBar.open(mensaje, 'Ok', {
          duration: 4000
        });

        this.tblPerfilDTO = respuesta;

        if (this.tipoAccionCrud === ETipoAccionCRUD.CREAR) {
          // this.eventoPersonaCreado.emit(this.tblPerfilDTO);
        } else {
          this.eventoPerfilModificado.emit(this.tblPerfilDTO);
          this.enProceso = false;
        }
      }
      );
    }
  }

  get etiquetaAccion(): string {
    let nombreAccionCrud: string;
    if (this.tipoAccionCrud === ETipoAccionCRUD.CREAR) {
      nombreAccionCrud = 'CREAR';
    } else if (this.tipoAccionCrud === ETipoAccionCRUD.MODIFICAR) {
      nombreAccionCrud = 'MODIFICAR';

    }
    return nombreAccionCrud;
  }

  public buscarControlesNoValidos() {
    this.controlesNovalidos = [];
    const controls = this.frmReactivo.controls;
    for (const name in controls) {
      if (controls[name].invalid) {
        this.controlesNovalidos.push({ control: name, errores: controls[name].errors });
      }
    }
  }
}
