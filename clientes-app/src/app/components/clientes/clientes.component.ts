import { AuthService } from './services/auth.service';
import { ModalService } from './services/modal.service';
import { Cliente } from 'src/app/models/cliente';
import { Component, OnInit } from '@angular/core';
import { ClienteService } from './services/cliente.service';
import { ActivatedRoute } from '@angular/router';

import Swal from 'sweetalert2';
import { URL_BACKEND } from 'src/app/config/config';
@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
})
export class ClientesComponent implements OnInit {
  clientes: Cliente[] = [];
  paginador: any;
  clienteSeleccionado: Cliente;
  urlBackend: string = URL_BACKEND;

  constructor(private clienteService: ClienteService,
    private activatedRoute: ActivatedRoute, public modalService: ModalService, public authService: AuthService) { }

  ngOnInit(): void {

    this.getClientes();

    this.modalService.notificarUpload.subscribe(cliente => {
      this.clientes.map(clienteOriginal => {
        if (cliente.id == clienteOriginal.id) {
          clienteOriginal.foto = cliente.foto;
        }
        return clienteOriginal;
      });

    });
  }
  public delete(cliente: Cliente): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    });

    swalWithBootstrapButtons.fire({
      title: '¿Estas seguro de querer eliminar este cliente?',
      text: `¿Seguro que quieres eliminar a ${cliente.nombre} ${cliente.apellido}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, Eliminar!',
      cancelButtonText: 'No, Cancelar!',
      buttonsStyling: false,
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger ml-2 alerta',
      },
      reverseButtons: false
    }).then((result) => {
      if (result.isConfirmed) {
        this.clienteService.delete(cliente.id).subscribe(response => {
          console.log(response);
          this.getClientes();
          swalWithBootstrapButtons.fire(
            'Eliminado!',
            `El cliente ${cliente.nombre} ${cliente.apellido} ha sido eliminado`,
            'success'
          );
        });

      } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelado',
          `El cliente ${cliente.nombre} ${cliente.apellido} NO se ha Eliminado `,
          'error'
        );
      }
    });

  }

  abrirModal(cliente: Cliente) {
    this.clienteSeleccionado = cliente;
    this.modalService.abrirModal();
  }

  getClientes() {
    this.activatedRoute.params.subscribe(params => {

      let page: number = params.page;

      if (!page) {
        page = 0;
      }

      this.clienteService.getClientes(page).subscribe(
        (response) => {
          this.paginador = response;
          this.clientes = response.content as Cliente[];
        }
      );
    });
  }
  getFoto(nombreFoto): string {
    let urlFoto: string;
    if (nombreFoto.length > 0 && nombreFoto != null) {
      urlFoto = `https://firebasestorage.googleapis.com/v0/b/proyectoangular-587e7.appspot.com/o/images%2F${nombreFoto}?alt=media`;
    }
    return urlFoto;
  }
}
