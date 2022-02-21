import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { NgxCurrencyModule } from "ngx-currency";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    // MaterialModule,
    FlexLayoutModule,
    PerfectScrollbarModule,
    NgxCurrencyModule
  ],
  exports: [
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    PerfectScrollbarModule,
    // MaterialModule,
    NgxCurrencyModule
  ]
})
export class SharedModule { }
