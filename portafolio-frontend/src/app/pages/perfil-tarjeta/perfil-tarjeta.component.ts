import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TblPerfilDTO } from 'src/app/models/TblPerfilDTO';

@Component({
  selector: 'app-perfil-tarjeta',
  templateUrl: './perfil-tarjeta.component.html',
  styleUrls: ['./perfil-tarjeta.component.scss']
})
export class PerfilTarjetaComponent implements OnInit {

  @Input() tblPerfilDTO: TblPerfilDTO;


  @Output() eventoPerfilSolicitadoParaModificar = new EventEmitter<TblPerfilDTO>();

  @Output() eventoPerfilSolicitadoParaConsultar = new EventEmitter<number>();

  @Output() eventoPerfilSolicitadoParaEliminar = new EventEmitter<TblPerfilDTO>();

  constructor() { }

  ngOnInit(): void {
    console.log(this.tblPerfilDTO);

  }

}
