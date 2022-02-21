import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CurriculumVitaeDialogoComponent } from '../pages/curriculum-vitae-dialogo/curriculum-vitae-dialogo.component';
import { PerfilService } from '../services/perfil.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  pdfSrc: string;

  title = 'Hello, I´m Valerious';

  titulo = 'Juan Valerio - Portafolio';

  constructor(
    private perfilService: PerfilService,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    window.document.title = this.titulo;
  }

  descargarReporteCurriculum() {
    this.perfilService.generarReporteCurriculum().subscribe(data => {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;
      a.download = 'JuanValerioCV.pdf';
      a.click();
    });
  }

  //pdfs//
  generarReporteCurriculum() {
    this.perfilService.generarReporteCurriculum().subscribe(data => {
      let reader = new FileReader();
      reader.onload = (e: any) => {
        this.pdfSrc = e.target.result;
        console.log(this.pdfSrc);
      }
      reader.readAsArrayBuffer(data);
    });
  }

  verCurriculumVitae(): void {
    const dialogRef = this.dialog.open(CurriculumVitaeDialogoComponent, {
      disableClose: true,
    });
    console.log(this.pdfSrc);

    dialogRef.afterClosed().subscribe(result => {
    });
  }
}
