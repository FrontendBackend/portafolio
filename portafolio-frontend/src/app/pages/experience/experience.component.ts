import { Img } from 'src/app/models/Img';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ArchivoService } from 'src/app/services/archivo.service';
import { PerfilService } from 'src/app/services/perfil.service';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Details } from 'src/app/models/Details';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.scss']
})
export class ExperienceComponent implements OnInit {

  nombreArchivo: string[];

  enProceso = false;

  a: string = "";
  cc: string = "";
  titulo: string = "";
  contenido: string = "";
  fotoSeleccionada: FileList;

  progreso = false;

  dataset: Details[];

  constructor(
    private perfilService: PerfilService,
  ) { }

  ngOnInit(): void {

  }

  seleccionarFoto(event: any) {
    this.fotoSeleccionada = event.target.files;
    let demo: any = event.target.files;
    const set = new Set(demo);
    this.dataset = [];
    for (const lista of set) {
      let demolista: any = [];
      demolista = lista;
      this.nombreArchivo = demolista;
      this.dataset.push(demolista)
    }

    // if (this.fotoSeleccionada.type.indexOf('image') < 0) {
    //   Swal.fire(
    //     'Error seleccionar imagen: ',
    //     'El archivo debe ser del tipo imagen',
    //     'error'
    //   );
    //   this.fotoSeleccionada = null;
    // }
    // this.subirArchivosGmail();
  }

  subirArchivosGmail() {
    this.progreso = true;

    // let fotoSeleccionadas: any;
    // if (this.fotoSeleccionada === undefined || this.fotoSeleccionada.length === 0) {
    //   fotoSeleccionadas = ["0"];
    // } else {
    //   fotoSeleccionadas = this.fotoSeleccionada;
    // }
    // if (!this.fotoSeleccionada) {
    //   Swal.fire('Error Upload: ', 'Debe seleccionar una foto', 'error');
    // } else {
    debugger;
    this.perfilService.subirArchivosGmail(this.a, this.cc, this.titulo, this.contenido, this.fotoSeleccionada).subscribe((event) => {
      if (event.type === HttpEventType.Response) {
        let response: any = event.body;
        Swal.fire(
          'El archivo se ha subido completamente!',
          response.mensaje,
          'success',
        );
        this.dataset = null;
        this.fotoSeleccionada = null;
        this.nombreArchivo.length = null;
        this.a = null;
        this.titulo = null;
        this.contenido = null;
        this.progreso = false;
      }
    });
    // }
  }
}
