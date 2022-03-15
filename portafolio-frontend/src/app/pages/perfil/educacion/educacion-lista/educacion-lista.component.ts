import { TblPerfilDTO } from './../../../../models/TblPerfilDTO';
import { Component, OnInit, Input } from '@angular/core';
import { ETipoAccionCRUD } from 'src/app/enums/tipo-accion';

@Component({
  selector: 'app-educacion-lista',
  templateUrl: './educacion-lista.component.html',
  styleUrls: ['./educacion-lista.component.scss']
})
export class EducacionListaComponent implements OnInit {

  @Input() tblPerfilDTO: TblPerfilDTO;

  @Input() tipoAccionCrud = ETipoAccionCRUD.NINGUNA;

  constructor() { }

  ngOnInit(): void {
    // console.log(this.tblPerfilDTO);

  }

}
