import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { AuthService } from '../../clientes/services/auth.service';
import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import Swal from 'sweetalert2';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      catchError(e => {
        if (e.status == 401) {
          if (this.authService.isAuthenticated()) {
            this.authService.logout();
          }

          this.router.navigate(['/login']);
        }
        if (e.status == 403) {
          Swal.fire({
            title: 'Acceso Denegado',
            text: `Hola ${this.authService.usuario.username} no tienes acceso a este recurso! `,
            icon: 'warning'
          });
          this.router.navigate(['/clientes']);
        }
        return throwError(e);
      })
    );
  }
}
