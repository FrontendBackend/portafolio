import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-skillset',
  templateUrl: './skillset.component.html',
  styleUrls: ['./skillset.component.scss']
})
export class SkillsetComponent implements OnInit {

  titulo = 'Skillset';

  constructor() { }

  ngOnInit(): void {
    // window.document.title = this.titulo;
  }

}
