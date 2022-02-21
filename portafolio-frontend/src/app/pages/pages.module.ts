import { NgModule } from '@angular/core';
import { CommonModule, DatePipe, DecimalPipe } from '@angular/common';

import { PagesRoutingModule } from './pages-routing.module';
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
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SkillsetTarjetaComponent } from './skillset-tarjeta/skillset-tarjeta.component';
import { SkillsetDialogoComponent } from './skillset-dialogo/skillset-dialogo.component';
import { SkillsetUploadComponent } from './skillset-upload/skillset-upload.component';
import { SkillsetFotoComponent } from './skillset-foto/skillset-foto.component';
import { PerfilFormularioComponent } from './perfil/perfil-formulario/perfil-formulario.component';
import { PerfilGeneralComponent } from './perfil/perfil-general/perfil-general.component';
import { MatTabsModule } from '@angular/material/tabs';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatDatepickerModule } from "@angular/material/datepicker";
import { ErrorStateMatcher, MAT_DATE_LOCALE, ShowOnDirtyErrorStateMatcher, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { MatPaginatorImpl } from 'src/shared/material/mat-paginator';
import { NgxCurrencyModule } from 'ngx-currency';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { CurriculumVitaeDialogoComponent } from './curriculum-vitae-dialogo/curriculum-vitae-dialogo.component';

const FECHA_FORMATOS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MM YYYY',
  },
};

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
    PerfilFormularioComponent,
    PerfilGeneralComponent,
    CurriculumVitaeDialogoComponent,
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
    MatTabsModule,
    NgxMaterialTimepickerModule,
    MatMomentDateModule,
    MatDatepickerModule,
    NgxCurrencyModule,
    MatGridListModule,
    MatAutocompleteModule,
    PdfViewerModule,
    // MaterialModule,
    // SharedModule,
  ],
  exports: [CurriculumVitaeDialogoComponent],
  providers: [DatePipe,
    DecimalPipe,
    { provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher },
    { provide: MatPaginatorIntl, useClass: MatPaginatorImpl },
    { provide: MAT_DATE_LOCALE, useValue: 'es-ES' },
    { provide: MAT_DATE_FORMATS, useValue: FECHA_FORMATOS }
  ]
})
export class PagesModule { }
