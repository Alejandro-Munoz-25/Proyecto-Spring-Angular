import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'paginator-nav',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent implements OnInit, OnChanges {

  @Input() paginador: any;
  paginas: number[] = [];

  desde!: number;
  hasta!: number;
  MAX_PAGINAS = 5;

  constructor() { }

  ngOnInit(): void {
    this.initPaginator();
  }

  ngOnChanges(changes: SimpleChanges): void {
    const paginadorActualizado = changes.paginador;
    if (paginadorActualizado.previousValue) {
      this.initPaginator();
    }
  }

  private initPaginator(): void {
    if (this.paginador.totalPages > this.MAX_PAGINAS) {
      const rangoMin = Math.max(0, this.paginador.number - (Math.trunc(this.MAX_PAGINAS / 2)));
      const rangoMax = Math.min(this.paginador.number + (Math.trunc((this.MAX_PAGINAS + 1) / 2)), this.paginador.totalPages);
      this.desde = Math.min(rangoMin, this.paginador.totalPages - this.MAX_PAGINAS);
      this.hasta = Math.max(rangoMax, this.MAX_PAGINAS);

      this.paginas = new Array(this.hasta - this.desde).fill(0).map((valor, indice) => indice + 1 + this.desde);
    } else {
      this.paginas = new Array(this.paginador.totalPages).fill(0).map((valor, indice) => indice + 1);
    }

  }

}
