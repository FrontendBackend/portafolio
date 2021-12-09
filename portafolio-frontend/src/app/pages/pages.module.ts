import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PagesRoutingModule } from './pages-routing.module';
import { MaterialModule } from 'src/shared/material/material.module';
import { FormularioComponent } from './formulario/formulario.component';
import { PortafolioComponent } from './portafolio/portafolio.component';
import { SkillsetComponent } from './skillset/skillset.component';
import { ExperienceComponent } from './experience/experience.component';
import { ContactComponent } from './contact/contact.component';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { PortafolioTarjetaComponent } from './portafolio-tarjeta/portafolio-tarjeta.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { PortafolioDialogoComponent } from './portafolio-dialogo/portafolio-dialogo.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SkillsetTarjetaComponent } from './skillset-tarjeta/skillset-tarjeta.component';
import { SkillsetDialogoComponent } from './skillset-dialogo/skillset-dialogo.component';
import { SkillsetUploadComponent } from './skillset-upload/skillset-upload.component';
import { SkillsetFotoComponent } from './skillset-foto/skillset-foto.component';
@NgModule({
  declarations: [
    FormularioComponent,
    PortafolioComponent,
    SkillsetComponent,
    ExperienceComponent,
    ContactComponent,
    PortafolioTarjetaComponent,
    PortafolioDialogoComponent,
    SkillsetTarjetaComponent,
    SkillsetDialogoComponent,
    SkillsetUploadComponent,
    SkillsetFotoComponent,
  ],
  imports: [
    CommonModule,
    PagesRoutingModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatTooltipModule,
    MatProgressBarModule,
    MatButtonModule,
    MatDividerModule,
    MatDialogModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatInputModule,
    MatSidenavModule,
    FlexLayoutModule,
    // MaterialModule
  ],
  exports:[],
  providers:[

  ]
})
export class PagesModule { }
