import Swal from 'sweetalert2';
import { Img } from './../../../models/Img';
import { environment } from './../../../../environments/environment';
import { Observable } from 'rxjs';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ETipoAccionCRUD } from 'src/app/enums/tipo-accion';
import { TblPerfilDTO } from 'src/app/models/TblPerfilDTO';
import { PerfilService } from 'src/app/services/perfil.service';
import { debounceTime, finalize, switchMap, tap } from 'rxjs/operators';
import { TblUbigeoDTO } from 'src/app/models/TblUbigeoDTO';
import { HttpEventType } from '@angular/common/http';

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

  lLegUbigeoDTONacimiento: TblUbigeoDTO[];
  cargandoUbigeosNacimiento = false;
  legUbigeoDTONacimientoSelec: TblUbigeoDTO;

  // CUANDO NO HAY IMAGEN REAL
  imagenData = Img.noPerfilCode;

  // CUANDO SI HAY IMAGEN REAL
  imagenDataPerfil = Img.noPerfilCode2;

  public fotoSeleccionada: File;
  progreso = false;
  clienteSeleccionado: TblPerfilDTO;

  constructor(private formBuilder: FormBuilder,
    private perfilService: PerfilService,
    private matSnackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.iniciarFormulario();
    this.configurarInicio();

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
        sobreMi: [this.tblPerfilDTO.sobreMi],
        resumen: [this.tblPerfilDTO.resumen],
        deHabilidad: [this.tblPerfilDTO.deHabilidad],
        deIdioma: [this.tblPerfilDTO.deIdioma],
        deDato: [this.tblPerfilDTO.deDato],
        descUbigeoNacimientoCompleto: [this.tblPerfilDTO.descUbigeoNacimientoCompleto, [Validators.required]],
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

    // observaciones que corregir
    this.perfilService.obtenerConfiguracionesGenerales(idPerfil).subscribe(respuesta => {

      this.configurarAutocompletarUbigeoNacimiento();

      if (this.tipoAccionCrud === ETipoAccionCRUD.MODIFICAR || this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
        const clave = 'tblPerfilDTO';
        this.tblPerfilDTO = respuesta[clave];

        this.enProceso = false;
      }

      this.enProceso = false;

    });
  }

  /***
   * Permite configurar el autocompletado para los ubigeos de nacimiento.
   */
  private configurarAutocompletarUbigeoNacimiento() {
    const nombreControl = 'descUbigeoNacimientoCompleto';

    this.frmReactivo.controls[nombreControl].valueChanges.pipe(
      debounceTime(500),
      tap(() => {
        this.lLegUbigeoDTONacimiento = [];
        this.cargandoUbigeosNacimiento = true;
      }),
      switchMap(valor => this.perfilService.listarPorUbigeos(valor).pipe(
        finalize(() => {
          this.cargandoUbigeosNacimiento = false;
        }),
      )
      )
    ).subscribe(respuesta => {
      if (respuesta === undefined) {
        this.lLegUbigeoDTONacimiento = [];
      } else {
        this.lLegUbigeoDTONacimiento = respuesta;
      }
    });
  }

  seCambioUbigeoNacimiento(descUbigeoNacimientoCompletoActual: string): void {
    if (descUbigeoNacimientoCompletoActual !== '') {
      if (this.legUbigeoDTONacimientoSelec?.codigoUnico !== descUbigeoNacimientoCompletoActual) {
        this.frmReactivo.get('descUbigeoNacimientoCompleto').setErrors({ ubigeoNacimientoIncorrecto: true });
      } else {
        if (this.frmReactivo.get('descUbigeoNacimientoCompleto').hasError('descUbigeoNacimientoCompleto')) {
          delete this.frmReactivo.get('descUbigeoNacimientoCompleto').errors.ubigeoNacimientoIncorrecto;
        }
      }
    } else {
      this.legUbigeoDTONacimientoSelec = null;
    }
  }

  ubigeoNacimientoSeleccionado(seleccionado: boolean, legUbigeoDTO: TblUbigeoDTO): void {
    if (seleccionado) {
      this.legUbigeoDTONacimientoSelec = legUbigeoDTO;
    } else {
      this.legUbigeoDTONacimientoSelec = null;
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

    if (this.legUbigeoDTONacimientoSelec !== undefined && this.legUbigeoDTONacimientoSelec !== null) {
      pgimContratoDTOCU.idUbigeo = this.legUbigeoDTONacimientoSelec.idUbigeo;
    } else {
      pgimContratoDTOCU.idUbigeo = this.tblPerfilDTO.idUbigeo;
    }

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

  seleccionarFoto(event: any) {
    this.fotoSeleccionada = event.target.files[0];
    console.log({ 'seleccionado: ': this.fotoSeleccionada });
    if (this.fotoSeleccionada.type.indexOf('image') < 0) {
      Swal.fire(
        'Error seleccionar imagen: ',
        'El archivo debe ser del tipo imagen',
        'error'
      );
      this.fotoSeleccionada = null;
    }
    this.subirFoto();
  }

  subirFoto() {
    this.progreso = true;

    let idPerfil = this.tblPerfilDTO.idPerfil;

    if (!this.fotoSeleccionada) {
      Swal.fire('Error Upload: ', 'Debe seleccionar una foto', 'error');
    } else {
      this.perfilService.subirFoto(this.fotoSeleccionada, idPerfil).subscribe((event) => {
        if (event.type === HttpEventType.Response) {
          let response: any = event.body;
          this.clienteSeleccionado = response.tblPerfilDTO as TblPerfilDTO;
          console.log({ 'subido: ': this.clienteSeleccionado });

          Swal.fire(
            'La foto se ha subido completamente!',
            response.mensaje,
            'success',
          );
          this.configurarInicio();
          this.progreso = false;
        }
      });
    }
  }
}
