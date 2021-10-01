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


@NgModule({
  declarations: [
    FormularioComponent,
    PortafolioComponent,
    SkillsetComponent,
    ExperienceComponent,
    ContactComponent
  ],
  imports: [
    CommonModule,
    PagesRoutingModule,
    MatCardModule
    // MaterialModule
  ]
})
export class PagesModule { }
