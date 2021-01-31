import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from '../services/cliente.service';
import Swal from 'sweetalert2';
import { Region } from 'src/app/models/region';

@Component({
  selector: 'app-form-clientes',
  templateUrl: './form-clientes.component.html',
})
export class FormClientesComponent implements OnInit {
  public cliente: Cliente = new Cliente();
  public regiones: Region[];
  // tslint:disable-next-line: no-inferrable-types
  public titulo: string = 'Crear cliente';

  public error: any;
  public errores: string[] = [];
  constructor(private clienteService: ClienteService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarCliente();
    this.clienteService.getRegiones().subscribe(regiones => {
      this.regiones = regiones;
    })
  }

  /**
   * create
   */
  public create(): void {
    this.clienteService.create(this.cliente).subscribe(
      cliente => {
        this.router.navigate(['/clientes']);
        Swal.fire({
          title: 'Nuevo Cliente',
          text: `Cliente ${cliente.nombre + ' ' + cliente.apellido} creado con éxito!`,
          icon: 'success',
          confirmButtonText: 'Continuar'
        });
      },
      error => {
        this.error = error.error;
        this.errores = Object.values(error.error);
        console.log(this.error);

      }
    );
  }

  public cargarCliente(): void {
    this.activatedRoute.params.subscribe(params => {
      const id = params.id;
      if (id) {
        this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente);
      }
    });
  }
  /**
   * update
   */
  public update(): void {

    this.cliente.facturas = null;//se asigna a  null las facturas ,para que no se actualizen,ya que solo se requiere actualizar el cliente
    this.clienteService.update(this.cliente).subscribe(json => {
      this.router.navigate(['/clientes']);
      Swal.fire({
        title: 'Cliente Actualizado',
        text: `Cliente ${json.cliente.nombre + ' ' + json.cliente.apellido} Actualizado con éxito!`,
        icon: 'success',
        confirmButtonText: 'Continuar'
      });
    },
      error => {
        this.error = error.error;
        this.errores = Object.values(error.error);

      });
  }


  compararRegion(o1: Region, o2: Region): boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }
    return o1 === null || o2 === null || o1 === undefined || o2 === undefined ? false : o1.id === o2.id;
  }
}
