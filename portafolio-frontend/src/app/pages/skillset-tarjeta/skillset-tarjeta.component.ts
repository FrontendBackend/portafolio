import { Component, Input, OnInit } from '@angular/core';
import { TblSkillsetDTO } from 'src/app/models/TblSkillsetDTO';

@Component({
  selector: 'app-skillset-tarjeta',
  templateUrl: './skillset-tarjeta.component.html',
  styleUrls: ['./skillset-tarjeta.component.scss']
})
export class SkillsetTarjetaComponent implements OnInit {

  @Input() tblSkillsetDTO: TblSkillsetDTO;

  constructor() { }

  ngOnInit(): void {
  }

}
