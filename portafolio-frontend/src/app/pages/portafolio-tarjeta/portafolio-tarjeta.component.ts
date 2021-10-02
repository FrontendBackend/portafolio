import { Component, Input, OnInit } from '@angular/core';
import { TblPortafolioDTO } from 'src/app/models/TblPortafolioDTO';

@Component({
  selector: 'app-portafolio-tarjeta',
  templateUrl: './portafolio-tarjeta.component.html',
  styleUrls: ['./portafolio-tarjeta.component.scss']
})
export class PortafolioTarjetaComponent implements OnInit {

  @Input() tblPortafolioDTO: TblPortafolioDTO;

  constructor() { }

  ngOnInit(): void {
  }

}
