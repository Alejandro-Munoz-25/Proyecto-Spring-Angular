import { Component } from '@angular/core';

@Component({
  selector: 'app-directiva',
  templateUrl: './directiva.component.html',
})
export class DirectivaComponent {
  habilitar = true;
  listaCurso: string[] = ['JavaScript', 'TypeScript', 'Java SE', 'C#'];
  constructor() { }

  setHabilitar(): void {
    this.habilitar = this.habilitar === true ? false : true;
  }
}
