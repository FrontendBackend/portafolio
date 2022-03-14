import { Observable } from 'rxjs';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ETipoAccionCRUD } from 'src/app/enums/tipo-accion';
import { TblPerfilDTO } from 'src/app/models/TblPerfilDTO';
import { PerfilService } from 'src/app/services/perfil.service';
import { TblEducacionDTO } from 'src/app/models/TblEducacionDTO';

@Component({
  selector: 'app-educacion-general',
  templateUrl: './educacion-general.component.html',
  styleUrls: ['./educacion-general.component.scss']
})
export class EducacionGeneralComponent implements OnInit {

  @Input() tblPerfilDTO: TblPerfilDTO;

  @Input() tblEducacionDTO: TblEducacionDTO;

  @Input() tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  @Input() idPersona: number;

  @Output() eventoEducacionModificado = new EventEmitter<TblEducacionDTO>();

  enProceso = false;

  controlesNovalidos: any[];

  esFormularioSoloLectura = false;

  frmReactivo: FormGroup;

  // lLegUbigeoDTONacimiento: TblUbigeoDTO[];
  // cargandoUbigeosNacimiento = false;
  // legUbigeoDTONacimientoSelec: TblUbigeoDTO;

  constructor(private formBuilder: FormBuilder,
    private perfilService: PerfilService,
    private matSnackBar: MatSnackBar,) { }

  ngOnInit(): void {
    this.iniciarFormulario();
    this.configurarInicio();
    console.log(this.tblPerfilDTO);

  }

  guardarActualizarEducacion(): void {

    if (!this.frmReactivo.valid) {
      return;
    }

    this.tblEducacionDTO.deEducacion = this.frmReactivo.value.deEducacion;
    this.tblEducacionDTO.idPerfil = this.tblPerfilDTO.idPerfil;
    this.perfilService.modificarEducacion2(this.tblEducacionDTO).subscribe(resp => {
      if ('success' === resp.status) {
        this.matSnackBar.open(resp.mensaje, 'OK', { duration: 4000 });
      } else {
        this.matSnackBar.open(resp.mensaje, 'ERROR', { duration: 4000 });
      }
    });
  }

  iniciarFormulario() {

    if (this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }

    this.frmReactivo = this.formBuilder.group(
      {
        deEducacion: [this.tblEducacionDTO.deEducacion],
      }
    );

    if (this.esFormularioSoloLectura) {
      Object.values(this.frmReactivo.controls).forEach(control => control.disable());
    }
  }

  configurarInicio() {
    this.enProceso = true;

    let idPerfil = 0;

    if (this.tblPerfilDTO.idPerfil) {
      idPerfil = this.tblPerfilDTO.idPerfil;
    }

    this.perfilService.obtenerEducacionPorId(idPerfil).subscribe(respuesta => {

      if (this.tipoAccionCrud === ETipoAccionCRUD.MODIFICAR || this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
        const clave = 'tblEducacionDTO';
        this.tblEducacionDTO = respuesta[clave];

        this.enProceso = false;
      }

      this.enProceso = false;

    });
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

    let tblEducacionDTOCU = new TblEducacionDTO();
    tblEducacionDTOCU = this.frmReactivo.value;

    // Recuperando las propiedades originales
    tblEducacionDTOCU.esRegistro = this.tblEducacionDTO.esRegistro;
    tblEducacionDTOCU.idPerfil = this.tblEducacionDTO.idPerfil;

    let peticion: Observable<TblEducacionDTO>;
    let mensaje: string;

    switch (+this.tipoAccionCrud) {
      // case ETipoAccionCRUD.CREAR:
      //   peticion = this.perfilService.crearPerfil(tblEducacionDTOCU);
      //   mensaje = 'Genial, la persona ha sido creada';
      //   break;
      case ETipoAccionCRUD.MODIFICAR:
        peticion = this.perfilService.modificarEducacion(tblEducacionDTOCU);
        mensaje = 'Genial, la educación ha sido modificada';
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

        this.tblEducacionDTO = respuesta;

        if (this.tipoAccionCrud === ETipoAccionCRUD.CREAR) {
          // this.eventoPersonaCreado.emit(this.tblPerfilDTO);
        } else {
          this.eventoEducacionModificado.emit(this.tblEducacionDTO);
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
