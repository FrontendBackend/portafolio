import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  title = 'Hello, I´m Juan Valerio Mayta';

  constructor() { }

  ngOnInit(): void {
  }

}
