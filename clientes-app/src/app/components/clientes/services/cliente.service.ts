import { URL_BACKEND } from './../../../config/config';
import { Router } from '@angular/router';
import { Cliente } from './../../../models/cliente';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Region } from 'src/app/models/region';
@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private urlEndPoint: string = URL_BACKEND + '/api/clientes';


  constructor(private http: HttpClient, private router: Router) { }

  getRegiones(): Observable<Region[]> {
    return this.http.get(this.urlEndPoint + '/regiones').pipe(
      map((response) => {
        return response as Region[];
      })

    );
  }
  // Esta a la escucha de los cambios que hay en la BD
  getClientes(page: number): Observable<any> {
    return this.http.get(this.urlEndPoint + '/page/' + page).pipe(
      map((response: any) => {
        (response.content as Cliente[]).map(cliente => {
          return cliente;
        });
        return response;
      }),
    );
  }

  create(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.urlEndPoint, cliente).pipe(
      map((response: any) => response.cliente as Cliente),
      catchError(e => {

        if (e.status === 400) {
          return throwError(e);
        }
        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }
      )
    );
  }

  getCliente(id: number): Observable<Cliente> {

    return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if (e.status != 401 && e.error.mensaje) {
          this.router.navigate(['/clientes']);
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }
      )
    )
      ;

  }
  update(cliente: Cliente): Observable<any> {
    return this.http.put<any>(`${this.urlEndPoint}/${cliente.id}`, cliente).pipe(
      catchError(e => {
        if (e.status === 400) {
          return throwError(e);
        }
        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }
      )
    );
  }
  delete(id: number): Observable<Cliente> {
    return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }
        return throwError(e);
      }
      )
    );
  }


  subirFoto(archivo: File, id: any): Observable<HttpEvent<{}>> {
    const formData = new FormData();

    formData.append('archivo', archivo);
    formData.append('id', id);
    const req = new HttpRequest('POST', `${this.urlEndPoint}/upload`, formData, {
      reportProgress: true,
    });
    return this.http.request(req)
  }
}
