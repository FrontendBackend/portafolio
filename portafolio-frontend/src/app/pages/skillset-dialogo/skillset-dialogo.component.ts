import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { ETipoAccionCRUD } from 'src/app/enums/tipo-accion';
import { ParametroDialogo } from 'src/app/models/ParametroDialogo';
import { TblSkillsetDTO } from 'src/app/models/TblSkillsetDTO';
import { SkillsetService } from 'src/app/services/skillset.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-skillset-dialogo',
  templateUrl: './skillset-dialogo.component.html',
  styleUrls: ['./skillset-dialogo.component.scss']
})
export class SkillsetDialogoComponent implements OnInit {

  frmReactivo: FormGroup;

  tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  enProceso = false;

  esFormularioSoloLectura = false;

  nombreAccion: string;

  controlesNovalidos: any[];

  tblSkillsetDTO: TblSkillsetDTO;

  constructor(public dialogRef: MatDialogRef<SkillsetDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) public parametroDialogo: ParametroDialogo<TblSkillsetDTO, any>,
    private portafolioService: SkillsetService,
    private fb: FormBuilder,
    private snack: MatSnackBar,) { }

  ngOnInit(): void {
    this.tblSkillsetDTO = this.parametroDialogo.objeto;
    this.nombreAccion = ETipoAccionCRUD[this.parametroDialogo.accion];
    if (this.parametroDialogo.accion === ETipoAccionCRUD.CONSULTAR) {
      this.esFormularioSoloLectura = true;
    }

    this.inicializarFormulario();
  }

  /**
   * Permite iniciar la configuración del formulario reactivo.
   */
  inicializarFormulario(): void {
    this.frmReactivo = this.fb.group(
      {
        noSkillset: [null],
      }
    );
    // this.dialogRef.updateSize('28%');

    this.configurarInicio();
  }

  configurarInicio() {
    this.enProceso = true;

    const idPortafolio = this.tblSkillsetDTO.idSkillset;

    this.portafolioService.obtenerConfiguracionesGenerales(idPortafolio).subscribe((respuesta: any) => {
      const clave = 'tblSkillsetDTO';

      if (this.tipoAccionCrud === ETipoAccionCRUD.MODIFICAR || this.tipoAccionCrud === ETipoAccionCRUD.CONSULTAR) {
        this.tblSkillsetDTO = respuesta[clave];
      }

      this.iniciarFormulario();

      this.enProceso = false;
    });
  }

  iniciarFormulario(): void {

    this.frmReactivo = this.fb.group({
      noSkillset: [this.tblSkillsetDTO.noSkillset, [Validators.required]],
    });

    if (this.esFormularioSoloLectura) {
      Object.values(this.frmReactivo.controls).forEach(control => control.disable());
    }
  }

  tieneError(campo: string, error: string): boolean {
    if (error === 'any' || error === '') {
      return (
        this.frmReactivo.get(campo).invalid &&
        this.frmReactivo.get(campo).touched
      );
    }

    return (
      this.frmReactivo.get(campo).hasError(error)
    );
  }

  /**
   * Procesa el envío del formulario.
   */
  enviar() {

    this.buscarControlesNoValidos();

    if (this.frmReactivo.invalid) {

      Object.values(this.frmReactivo.controls).forEach(control => control.markAllAsTouched());

      this.snack.open('Existen datos incorrectos o faltantes, por favor verifique', 'CERRAR', {
        duration: 4000
      });

      return;
    }

    this.procesarCrearOModificar();
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

  /**
   * Permite procesar la acción de crear o modificar.
   */
  procesarCrearOModificar() {
    this.enProceso = true;

    let tblSkillsetDTOCU = new TblSkillsetDTO();
    tblSkillsetDTOCU = this.frmReactivo.value;

    // Recuperando las propiedades originales
    tblSkillsetDTOCU.idSkillset = this.tblSkillsetDTO.idSkillset;

    let peticion: Observable<TblSkillsetDTO>;
    let mensaje: string;

    switch (+this.parametroDialogo.accion) {
      case ETipoAccionCRUD.CREAR:
        peticion = this.portafolioService.crearSkillset(tblSkillsetDTOCU);
        mensaje = 'La skillset ha sido creada';
        break;
      case ETipoAccionCRUD.MODIFICAR:
        peticion = this.portafolioService.modificarSkillset(tblSkillsetDTOCU);
        mensaje = 'La skillset ha sido modificada';
        break;
      default:
        console.log('Ninguna acción implementada');
        break;
    }

    if (peticion) {
      peticion.subscribe(respuesta => {
        this.snack.open(mensaje, 'Ok', {
          duration: 3000
        });

        this.parametroDialogo.objeto = respuesta;
        this.parametroDialogo.resultado = 'ok';

        this.enProceso = false;
        this.dialogRef.close();
      }
      );
    }
  }

}
