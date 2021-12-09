import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ArchivoService } from 'src/app/services/archivo.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.scss']
})
export class ExperienceComponent implements OnInit {

  // titulo = 'Experience';

  nombreArchivo: string;
  archivosSeleccionados: FileList;
  imagenEstado: boolean = false;
  imagenData: any;

  constructor(private archivoService: ArchivoService,
    private sanitization: DomSanitizer) { }

  ngOnInit(): void {
    // window.document.title = this.titulo;
    this.archivoService.leerArchivo().subscribe(data => {
      this.convertir(data);
      // console.log({data});

    });
  }

  convertir(data: any) {
    let reader = new FileReader();
    reader.readAsDataURL(data);
    reader.onloadend = () => {
      let base64 = reader.result;
      // console.log(base64);
      this.sanar(base64);
    }
  }

  sanar(base64: any) {
    this.imagenData = this.sanitization.bypassSecurityTrustResourceUrl(base64);
    this.imagenEstado = true;
  }

  seleccionarArchivo(e: any) {
    // console.log(e);
    this.nombreArchivo = e.target.files[0].name;
    this.archivosSeleccionados = e.target.files;

  }

  subirArchivo() {
    this.archivoService.guardarArchivo(this.archivosSeleccionados.item(0)).subscribe();
  }
}
