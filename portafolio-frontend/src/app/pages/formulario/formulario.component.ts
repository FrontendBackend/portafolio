import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.scss']
})
export class FormularioComponent implements OnInit {

  // titulo = 'Formulario';

  constructor() { }

  ngOnInit(): void {
    // window.document.title = this.titulo;
  }

}
