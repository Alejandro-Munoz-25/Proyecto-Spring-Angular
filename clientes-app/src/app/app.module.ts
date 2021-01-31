import { VerificaTokenGuard } from './components/usuarios/guards/verifica-token.guard';
import { AuthInterceptor } from './components/usuarios/interceptors/auth.interceptor';
import { TokenInterceptor } from './components/usuarios/interceptors/token.interceptor';
import { RoleGuard } from './components/usuarios/guards/role.guard';
import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { DirectivaComponent } from './components/directiva/directiva.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { ClienteService } from './components/clientes/services/cliente.service';
import { PaginatorComponent } from './components/paginator/paginator.component';
import { FormClientesComponent } from './components/clientes/form-clientes/form-clientes.component';
import { DetalleFacturaComponent } from './components/facturas/detalle-factura.component';


import { Routes, RouterModule } from '@angular/router';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter';

import localeMX from '@angular/common/locales/es-MX';
import { registerLocaleData } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { from } from 'rxjs';
import { PerfilComponent } from './components/perfil/perfil.component';
import { LoginComponent } from './components/usuarios/login.component';
import { AuthGuard } from './components/usuarios/guards/auth.guard';
import { FacturasComponent } from './components/facturas/facturas/facturas.component';

import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';


registerLocaleData(localeMX);


const routes: Routes = [
  { path: '', redirectTo: '/clientes', pathMatch: 'full' },
  { path: 'clientes', component: ClientesComponent, canActivate: [VerificaTokenGuard] },
  { path: 'clientes/page/:page', component: ClientesComponent, canActivate: [VerificaTokenGuard] },
  { path: 'directivas', component: DirectivaComponent },
  { path: 'clientes/form', component: FormClientesComponent, canActivate: [AuthGuard, RoleGuard, VerificaTokenGuard], data: { role: 'ROLE_ADMIN' } },
  { path: 'clientes/form/:id', component: FormClientesComponent, canActivate: [AuthGuard, RoleGuard, VerificaTokenGuard], data: { role: 'ROLE_ADMIN' } },
  { path: 'login', component: LoginComponent },
  { path: 'facturas/:id', component: DetalleFacturaComponent, canActivate: [AuthGuard, RoleGuard, VerificaTokenGuard], data: { role: 'ROLE_USER' } },
  { path: 'facturas/form/:clienteId', component: FacturasComponent, canActivate: [AuthGuard, RoleGuard, VerificaTokenGuard], data: { role: 'ROLE_ADMIN' } },
  { path: '**', redirectTo: '/clientes', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    ClientesComponent,
    FormClientesComponent,
    PaginatorComponent,
    PerfilComponent,
    LoginComponent,
    DetalleFacturaComponent,
    FacturasComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule, // Permite las peticiones HTTP
    RouterModule.forRoot(routes),
    FormsModule,
    SweetAlert2Module.forRoot(),
    BrowserAnimationsModule,
    MatMomentDateModule,
    MatDatepickerModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatInputModule,
    MatFormFieldModule
  ],
  providers: [ClienteService,
    { provide: LOCALE_ID, useValue: 'es-MX' },//Establece la locacion en la aplicacion angular, se utiliza para mostrar la fecha del usuario
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },//interceptor para agregar la autorizacion a las cabeceras
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],//interceptor para agregar los errores de autorizacion
  bootstrap: [AppComponent]
})
export class AppModule { }
