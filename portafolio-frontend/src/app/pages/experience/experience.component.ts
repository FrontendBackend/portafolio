import { Img } from 'src/app/models/Img';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ArchivoService } from 'src/app/services/archivo.service';
import { PerfilService } from 'src/app/services/perfil.service';
import { HttpClient } from '@angular/common/http';
import { Details } from 'src/app/models/Details';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.scss']
})
export class ExperienceComponent implements OnInit {

  pdfSrc: string;

  enProceso = false;

  // titulo = 'Experience';
  imagenData = Img.noImgCode;

  title = 'EmailTemplate';
  dataset: Details = {
    name: '',
    age: null,
    country: '',
    email: ''
  };

  constructor(private archivoService: ArchivoService,
    private perfilService: PerfilService,
    private sanitization: DomSanitizer,
    private https: HttpClient
  ) { }

  ngOnInit(): void {

  }

  onSubmit() {
    this.enProceso = true;

    this.https.post<Details>('http://localhost:9999/testapp/getdetails', this.dataset).subscribe(
      res => {
        this.dataset = res;
        console.log(this.dataset);
        Swal.fire(
          'Notificación enviada!',
          'Revise su correo',
          'success',
        );
        this.dataset.age = null;
        this.dataset.name = '';
        this.dataset.country = '';
        this.dataset.email = '';

        this.enProceso = false;
      });
  }
}
