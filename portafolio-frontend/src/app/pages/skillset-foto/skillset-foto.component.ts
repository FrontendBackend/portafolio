import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ParametroDialogo } from 'src/app/models/ParametroDialogo';
import { TblSkillsetDTO } from 'src/app/models/TblSkillsetDTO';

@Component({
  selector: 'app-skillset-foto',
  templateUrl: './skillset-foto.component.html',
  styleUrls: ['./skillset-foto.component.scss']
})
export class SkillsetFotoComponent implements OnInit {

  clienteSeleccionado: TblSkillsetDTO;

  constructor(public dialogRef: MatDialogRef<SkillsetFotoComponent>,
    @Inject(MAT_DIALOG_DATA) public parametroDialogo: ParametroDialogo<TblSkillsetDTO, any>,) { }

  ngOnInit(): void {
    this.clienteSeleccionado = this.parametroDialogo.objeto;
    // this.parametroDialogo.objeto.idSkillset = this.clienteSeleccionado.idSkillset;
    // console.log(this.parametroDialogo);
    // console.log(this.clienteSeleccionado);

  }
}
