import { Img } from 'src/app/models/Img';
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
  imagenData = Img.noImgCode;

  constructor(private archivoService: ArchivoService,
    private sanitization: DomSanitizer) { }

  ngOnInit(): void {

  }

}
