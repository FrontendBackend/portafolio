import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.scss']
})
export class ExperienceComponent implements OnInit {

  // titulo = 'Experience';

  constructor() { }

  ngOnInit(): void {
    // window.document.title = this.titulo;
  }
}
