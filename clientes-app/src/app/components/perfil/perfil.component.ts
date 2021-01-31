import { Factura } from './../../models/factura';
import { FacturaService } from './../facturas/services/factura.service';
import { AuthService } from './../clientes/services/auth.service';
import { ModalService } from './../clientes/services/modal.service';
import { ClienteService } from './../clientes/services/cliente.service';
import { Cliente } from 'src/app/models/cliente';
import { Component, Input, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';
import { URL_BACKEND } from 'src/app/config/config';
@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  @Input() cliente: Cliente;
  titulo = 'Perfil del Cliente';
  public fotoSeleccionada!: File;
  progress = 0;
  urlbackend: string = URL_BACKEND;

  constructor(private clienteService: ClienteService, public modalService: ModalService,
    public authService: AuthService, public facturaService: FacturaService) { }

  ngOnInit(): void {
    this.progress = 0;
  }

  seleccionarFoto(event: any): void {
    this.progress = 0;
    let imgEv = (event.target.files[0] != undefined) ? event.target.files[0].type.indexOf('image') : 0;;
    if (imgEv < 0) {
      this.fotoSeleccionada = null;
      Swal.fire({
        title: 'Error al seleccionar la imagen',
        text: 'El archivo debe de ser del tipo imagen',
        icon: 'error'
      });
    } else {
      this.fotoSeleccionada = event.target.files[0];
    }
  }

  subirFoto(): void {

    if (!this.fotoSeleccionada) {

      Swal.fire({
        title: 'Ocurrio un error',
        text: 'Selecciona una foto valida',
        icon: 'error',
      });

    } else {

      this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id).subscribe(
        event => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progress = Math.round((event.loaded / event.total) * 100);
          } else if (event.type === HttpEventType.Response) {
            let response: any = event.body;
            this.cliente = response.cliente as Cliente;
            this.modalService.notificarUpload.emit(this.cliente);
            Swal.fire({
              title: 'Foto subida correctamente',
              text: response.mensaje,
              icon: 'success',
              confirmButtonText: 'Continuar'
            });
          }
        },

      );
    }

  }

  cerrarModal() {
    this.modalService.cerrarModal();
    this.fotoSeleccionada = null;
    this.progress = 0;
  }
  delete(factura: Factura): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    });

    swalWithBootstrapButtons.fire({
      title: '¿Estas seguro de querer eliminar esta factura?',
      text: `¿Seguro que quieres eliminar la factura ${factura.id} con descripción ${factura.descripcion}?`,
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
        this.facturaService.deleteFactura(factura.id).subscribe(response => {

          this.cliente.facturas = this.cliente.facturas.filter(f => f !== factura);

          swalWithBootstrapButtons.fire(
            'Eliminado!',
            `La Factura ${factura.id} con descripción ${factura.descripcion} ha sido eliminada`,
            'success'
          );
        });

      } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelado',
          `La Factura ${factura.id} con descripción ${factura.descripcion} NO se ha eliminado`,
          'error'
        );
      }
    });
  }

  getFoto(nombreFoto: string): string {
    let urlFoto: string;
    if (nombreFoto.length > 0 && nombreFoto != null) {
      urlFoto = `https://firebasestorage.googleapis.com/v0/b/proyectoangular-587e7.appspot.com/o/images%2F${nombreFoto}?alt=media`;
    }
    return urlFoto;
  }
}
