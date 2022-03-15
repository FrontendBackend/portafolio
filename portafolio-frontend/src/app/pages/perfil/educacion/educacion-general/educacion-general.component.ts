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

  @Input() tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

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

    if (this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }

    // console.log(this.tblPerfilDTO);

    // this.configurarInicio();
    this.iniciarFormulario();
  }



  get f() { return this.frmReactivo.controls; }

  iniciarFormulario() {

    this.frmReactivo = this.formBuilder.group(
      {
        deEducacion: [this.tblPerfilDTO.deEducacion]
      }
    );

    if (this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
      Object.values(this.frmReactivo.controls).forEach(control => control.disable());
    }
  }

  guardarActualizarEducacion(): void {

    if (!this.frmReactivo.valid) {
      return;
    }

    this.tblPerfilDTO.deEducacion = this.frmReactivo.value.deEducacion;
    this.perfilService.modificarEducacion2(this.tblPerfilDTO).subscribe(resp => {
      if ('success' === resp.status) {
        this.matSnackBar.open(resp.mensaje, 'OK', { duration: 4000 });
      } else {
        this.matSnackBar.open(resp.mensaje, 'ERROR', { duration: 4000 });
      }
    });
  }

  // configurarInicio() {
  //   this.enProceso = true;

  //   let idPerfil = 0;

  //   const tblEducacionDTO = new TblEducacionDTO();

  //   tblEducacionDTO.idPerfil = this.tblPerfilDTO.idPerfil;

  //   idPerfil = tblEducacionDTO.idPerfil

  //   this.perfilService.obtenerEducacionPorId(idPerfil).subscribe(respuesta => {

  //     if (this.tipoAccionCrud === ETipoAccionCRUD.MODIFICAR || this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
  //       const clave = 'tblEducacionDTO';
  //       this.tblEducacionDTO = respuesta[clave] as TblEducacionDTO;

  //       this.enProceso = false;
  //     }

  //     this.enProceso = false;

  //   });
  // }

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
