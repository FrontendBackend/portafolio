import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-portafolio',
  templateUrl: './portafolio.component.html',
  styleUrls: ['./portafolio.component.scss']
})
export class PortafolioComponent implements OnInit {

  titulo = 'Portafolio';


  ngOnInit(): void {
    // window.document.title = this.titulo;
  }
}
