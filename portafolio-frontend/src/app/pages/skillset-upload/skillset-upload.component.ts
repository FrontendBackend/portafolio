import { environment } from 'src/environments/environment';
import { HttpEventType } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { TblSkillsetDTO } from 'src/app/models/TblSkillsetDTO';
import { SkillsetService } from 'src/app/services/skillset.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-skillset-upload',
  templateUrl: './skillset-upload.component.html',
  styleUrls: ['./skillset-upload.component.scss'],
})
export class SkillsetUploadComponent implements OnInit {
  @Input() tblSkillsetDTO: TblSkillsetDTO;

  public fotoSeleccionada: File;

  progreso: number = 0;

  host_img_upload_ver = environment.HOST_IMG_UPLOAD_VER;

  constructor(
    public skillsetService: SkillsetService // public authService: AuthService,
  ) // public modalService: ModalService
  {}

  ngOnInit() {}

  seleccionarFoto(event: any) {
    this.fotoSeleccionada = event.target.files[0];
    this.progreso = 0;
    console.log(this.fotoSeleccionada);
    if (this.fotoSeleccionada.type.indexOf('image') < 0) {
      Swal.fire(
        'Error seleccionar imagen: ',
        'El archivo debe ser del tipo imagen',
        'error'
      );
      this.fotoSeleccionada = null;
    }
  }

  subirFoto() {
    let idSkillset = this.tblSkillsetDTO.idSkillset;

    if (!this.fotoSeleccionada) {
      Swal.fire('Error Upload: ', 'Debe seleccionar una foto', 'error');
    } else {
      this.skillsetService
        .subirFoto(this.fotoSeleccionada, idSkillset)
        .subscribe((event) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progreso = Math.round((event.loaded / event.total) * 100);
          } else if (event.type === HttpEventType.Response) {
            let response: any = event.body;
            this.tblSkillsetDTO = response.tblSkillsetDTO as TblSkillsetDTO;

            this.skillsetService.notificarUpload.emit(this.tblSkillsetDTO);
            Swal.fire(
              'La foto se ha subido completamente!',
              response.mensaje,
              'success'
            );
          }
        });
    }
  }

  cerrarModal() {
    this.skillsetService.cerrarModal();
    this.fotoSeleccionada = null;
    this.progreso = 0;
  }
}
