import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ParametroDialogo } from 'src/app/models/ParametroDialogo';
import { PerfilService } from 'src/app/services/perfil.service';

@Component({
  selector: 'app-curriculum-vitae-dialogo',
  templateUrl: './curriculum-vitae-dialogo.component.html',
  styleUrls: ['./curriculum-vitae-dialogo.component.scss']
})
export class CurriculumVitaeDialogoComponent implements OnInit {

  pdfSrc: string;

  // Me permite mostrar el mensaje cuando el reporte está en blanco o mal hecho.
  sinReporte: number;

  enProceso = false;

  constructor(public dialogRef: MatDialogRef<CurriculumVitaeDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private perfilService: PerfilService,
  ) { }

  ngOnInit(): void {
    this.generarReporteCurriculum();
  }

  //pdfs//
  generarReporteCurriculum() {

    this.enProceso = true;

    this.perfilService.generarReporteCurriculum(1).subscribe((data: Blob) => {
      let reader = new FileReader();
      reader.onload = (e: any) => {
        this.pdfSrc = e.target.result;
      }
      reader.readAsArrayBuffer(data);
      const data2 = data;
      this.sinReporte = data2.size;

      setTimeout(() => {
        this.enProceso = false;
      }, 1000)
    });
  }
}
