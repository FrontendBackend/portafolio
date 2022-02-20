import { Img } from 'src/app/models/Img';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ArchivoService } from 'src/app/services/archivo.service';
import { PerfilService } from 'src/app/services/perfil.service';
@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.scss']
})
export class ExperienceComponent implements OnInit {

  pdfSrc: string;

  // titulo = 'Experience';
  imagenData = Img.noImgCode;

  constructor(private archivoService: ArchivoService,
    private perfilService: PerfilService,
    private sanitization: DomSanitizer) { }

  ngOnInit(): void {
    // this.generarReporteCurriculum();
    this.pdfSrc = "https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf";
  }

  //pdfs//
  // generarReporteCurriculum() {
  //   this.perfilService.generarReporteCurriculum().subscribe(data => {
  //     let reader = new FileReader();
  //     reader.onload = (e: any) => {
  //       this.pdfSrc = e.target.result;
  //       console.log(this.pdfSrc);
  //     }
  //     reader.readAsArrayBuffer(data);
  //   });
  // }
}
