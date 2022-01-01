import { HttpEventType } from '@angular/common/http';
import { Component, Inject, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ParametroDialogo } from 'src/app/models/ParametroDialogo';
import { TblSkillsetDTO } from 'src/app/models/TblSkillsetDTO';
import { SkillsetService } from 'src/app/services/skillset.service';
import Swal from 'sweetalert2';
import { SkillsetComponent } from '../skillset/skillset.component';

@Component({
  selector: 'app-skillset-foto',
  templateUrl: './skillset-foto.component.html',
  styleUrls: ['./skillset-foto.component.scss']
})
export class SkillsetFotoComponent implements OnInit {

  clienteSeleccionado: TblSkillsetDTO;
  @ViewChild(SkillsetComponent) esTarjetaInfoComponent: SkillsetComponent;
  @Input() tblSkillsetDTO: TblSkillsetDTO;
  public fotoSeleccionada: File;
  progreso: number = 0;

  constructor(public dialogRef: MatDialogRef<SkillsetFotoComponent>,
    @Inject(MAT_DIALOG_DATA) public parametroDialogo: ParametroDialogo<TblSkillsetDTO, any>,
    public skillsetService: SkillsetService,
    private snack: MatSnackBar) { }

  ngOnInit(): void {
    this.clienteSeleccionado = this.parametroDialogo.objeto;
    // console.log(this.parametroDialogo);
    console.log(this.clienteSeleccionado);

  }

  seleccionarFoto(event: any) {
    this.fotoSeleccionada = event.target.files[0];
    this.progreso = 0;
    console.log({'seleccionado: ': this.fotoSeleccionada});
    if (this.fotoSeleccionada.type.indexOf('image') < 0) {
      Swal.fire(
        'Error seleccionar imagen: ',
        'El archivo debe ser del tipo imagen',
        'error'
      );
      this.fotoSeleccionada = null;
    }
  }

  subirFoto() {
    let idSkillset = this.parametroDialogo.objeto.idSkillset;

    if (!this.fotoSeleccionada) {
      Swal.fire('Error Upload: ', 'Debe seleccionar una foto', 'error');
    } else {
      this.skillsetService.subirFoto(this.fotoSeleccionada, idSkillset).subscribe((event) => {
        if (event.type === HttpEventType.Response) {
          let response: any = event.body;
          this.clienteSeleccionado = response.tblSkillsetDTO as TblSkillsetDTO;
          console.log({ 'subido: ': this.clienteSeleccionado});

          // if (idSkillset != null){
          //   this.snack.open('mensaje', 'Ok', {
          //     duration: 3000
          //   });
          // }
          Swal.fire(
            'La foto se ha subido completamente!',
            response.mensaje,
            'success',
          );
        }
      });
    }
  }

  eventoImgSubido() {

    this.esTarjetaInfoComponent.listarSkillset();
  }
}
