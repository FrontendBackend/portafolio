import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { SkillsetService } from 'src/app/services/skillset.service';

@Component({
  selector: 'app-skillset',
  templateUrl: './skillset.component.html',
  styleUrls: ['./skillset.component.scss']
})
export class SkillsetComponent implements OnInit {

  titulo = 'Skillset';

  nombreArchivo: string;
  archivosSeleccionados: FileList;

  imagenEstado: boolean = false;
  imagenData: any;

  constructor(private skillsetService: SkillsetService,
    private sanitization: DomSanitizer) { }

  ngOnInit(): void {
    this.listarSkillset();
    // window.document.title = this.titulo;
  }

  listarSkillset() {
    this.skillsetService.listarSkillset().subscribe(data => {
      this.convertir(data);
    });
  }

  convertir(data: any) {
    let reader = new FileReader();
    reader.readAsDataURL(data);
    reader.onloadend = () => {
      let base64 = reader.result;
      //console.log(base64);
      this.sanar(base64);
    }
  }

  sanar(base64: any) {
    this.imagenData = this.sanitization.bypassSecurityTrustResourceUrl(base64);
    this.imagenEstado = true;
  }

  seleccionarArchivo(e: any) {
    this.nombreArchivo = e.target.files[0].name;
    this.archivosSeleccionados = e.target.files;
  }

  subirArchivo(){
    this.skillsetService.crearSkillset(this.archivosSeleccionados.item(0)).subscribe();
  }
}
