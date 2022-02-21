import { environment } from 'src/environments/environment';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.scss']
})
export class FormularioComponent implements OnInit {

  // titulo = 'Formulario';
  host_img_portafolio = environment.HOST_IMG_PORTAFOLIO;
  constructor() { }

  ngOnInit(): void {
    // window.document.title = this.titulo;
  }

}
