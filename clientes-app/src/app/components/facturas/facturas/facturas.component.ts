import { ItemFactura } from './../../../models/item-factura';
import { Producto } from './../../../models/producto';
import { FacturaService } from './../services/factura.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteService } from './../../clientes/services/cliente.service';
import { Factura } from './../../../models/factura';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import { map, mergeMap, startWith } from 'rxjs/operators';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';

import Swal from 'sweetalert2';
@Component({
  selector: 'app-facturas',
  templateUrl: './facturas.component.html'
})
export class FacturasComponent implements OnInit {

  titulo: string = "Nueva Factura";
  factura: Factura = new Factura();
  autoCompleteControl = new FormControl();
  // productos: string[] = ['Tablet', 'Sony', 'Escritorio', 'TV Samsung'];
  productosFiltrados: Observable<Producto[]>;

  constructor(private clienteService: ClienteService, private activatedRoute: ActivatedRoute,
    private facturaService: FacturaService, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      let clienteId = + params.clienteId;
      this.clienteService.getCliente(clienteId).subscribe(cliente => {
        this.factura.cliente = cliente;
      });
    });
    this.filtrarProductos();
    this.recuperarLineas();
  }



  private _filter(value: string): Observable<Producto[]> {
    const filterValue = value.toLowerCase();

    // return this.productos.filter(option => option.toLowerCase().includes(filterValue));
    return this.facturaService.filtrarProductos(filterValue);
  }

  mostrarNombre(producto?: Producto): string | undefined {
    return producto ? producto.nombre : undefined;
  }

  seleccionarProducto(event: MatAutocompleteSelectedEvent): void {

    let producto = event.option.value as Producto;
    let nuevoItem = new ItemFactura();

    if (this.existeItem(producto.id)) {
      this.incrementaCantidad(producto.id);
    } else {
      nuevoItem.producto = producto;
      this.factura.items.push(nuevoItem);
    }
    localStorage.setItem('linea', JSON.stringify(this.factura.items));
    this.autoCompleteControl.setValue('');//deja vacio el input del autocompletado
    event.option.focus();//quita el focus del input
    event.option.deselect();//quita del autocomplete
    this.filtrarProductos();

  }

  actualizarCantidad(id: number, event: any): void {

    let cantidad: number = event.target.value as number;

    if (cantidad <= 0) {
      return this.eliminarItemFactura(id);
    }

    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id == item.producto.id) {
        item.cantidad = cantidad;
      }
      return item;
    }
    );
    localStorage.setItem('linea', JSON.stringify(this.factura.items));

  }

  existeItem(id: number): boolean {
    let existe = false;

    this.factura.items.forEach((item: ItemFactura) => {

      if (id == item.producto.id) {
        existe = true;
      }

    });

    return existe;

  }

  incrementaCantidad(id: number): void {

    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id == item.producto.id) {
        ++item.cantidad;
      }
      return item;
    }
    );
  }

  eliminarItemFactura(id: number) {
    this.factura.items = this.factura.items.filter((item: ItemFactura) => id !== item.producto.id);
    localStorage.setItem('linea', JSON.stringify(this.factura.items));
  }

  filtrarProductos(): Observable<Producto[]> {
    return this.productosFiltrados = this.autoCompleteControl.valueChanges
      .pipe(
        startWith(''),
        map(value => typeof (value) === 'string' ? value : value.nombre),
        mergeMap(value => value ? this._filter(value) : [])
      );
  }

  crearFactura(facturasForm: any): void {

    if (this.factura.items.length <= 0) {
      this.autoCompleteControl.setErrors({ 'invalid': true })
    }

    if (facturasForm.form.valid && this.factura.items.length > 0) {

      console.log(this.factura);
      localStorage.removeItem('linea');

      this.facturaService.createFactura(this.factura).subscribe(factura => {
        Swal.fire({
          title: this.titulo,
          text: `Factura ${factura.descripcion} creada con exito`,
          icon: 'success'
        });
        this.router.navigate(['/facturas', factura.id]);
      });
    }
  }

  recuperarLineas() {
    let linea = JSON.parse(localStorage.getItem('linea')) as ItemFactura[];
    if (linea.length > 0) {
      linea.forEach((item: ItemFactura) => {
        let recuperarItem = new ItemFactura();
        recuperarItem.producto = item.producto as Producto;
        recuperarItem.cantidad = item.cantidad as number;
        this.factura.items.push(recuperarItem);
      })
    } else {
      localStorage.removeItem('linea');
    }
  }
}

