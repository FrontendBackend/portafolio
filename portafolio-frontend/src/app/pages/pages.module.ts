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
import {MatDialogModule} from '@angular/material/dialog';

@NgModule({
  declarations: [
    FormularioComponent,
    PortafolioComponent,
    SkillsetComponent,
    ExperienceComponent,
    ContactComponent,
    PortafolioTarjetaComponent,
    PortafolioDialogoComponent
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
    MatDialogModule
    // MaterialModule
  ]
})
export class PagesModule { }
