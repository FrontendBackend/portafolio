import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from './contact/contact.component';
import { ExperienceComponent } from './experience/experience.component';
import { PortafolioComponent } from './portafolio/portafolio.component';
import { SkillsetComponent } from './skillset/skillset.component';

const routes: Routes = [
  {
    path: 'portafolio',
    component: PortafolioComponent
  },
  {
    path: 'habilidades',
    component: SkillsetComponent
  },
  {
    path: 'experiencia',
    component: ExperienceComponent
  },
  {
    path: 'contacto',
    component: ContactComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
