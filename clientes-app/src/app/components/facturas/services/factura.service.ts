import { Factura } from './../../../models/factura';
import { Producto } from './../../../models/producto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { URL_BACKEND } from 'src/app/config/config';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {
  private urlEndpoint: string = URL_BACKEND + "/api/facturas/";
  constructor(private http: HttpClient) { }

  getFactura(id: number): Observable<Factura> {
    return this.http.get<Factura>(`${this.urlEndpoint}${id}`);
  }
  deleteFactura(id: number): Observable<void> {
    return this.http.delete<void>(`${this.urlEndpoint}${id}`);
  }
  filtrarProductos(palabra: string): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.urlEndpoint}filtar-productos/${palabra}`);
  }

  createFactura(factura: Factura): Observable<Factura> {
    return this.http.post<Factura>(this.urlEndpoint, factura);
  }
}
