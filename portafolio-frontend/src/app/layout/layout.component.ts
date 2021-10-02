import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  title = 'Hello, I´m Valerious';

  titulo = 'Juan Valerio - Portafolio';

  constructor() { }

  ngOnInit(): void {
    window.document.title = this.titulo;
  }

}
