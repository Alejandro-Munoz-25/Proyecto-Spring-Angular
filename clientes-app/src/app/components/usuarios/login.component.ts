import { Router } from '@angular/router';
import { AuthService } from './../clientes/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  titulo: string = "Por favor Inicie Sesión"
  usuario: Usuario;
  constructor(private authService: AuthService, private route: Router) {
    this.usuario = new Usuario();
  }

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      Swal.fire({
        title: 'Login',
        text: `Hola ${this.authService.usuario.username},ya has iniciado sesión anteriormente`,
        icon: 'info'
      })
      this.route.navigate(['/clientes']);
    }

  }
  login(): void {
    // console.log(this.usuario);
    if (this.usuario.username == null || this.usuario.password == null) {
      Swal.fire({
        title: 'Error de Login',
        text: 'Username o password vacíos',
        icon: 'error'
      })
      return;
    }
    this.authService.login(this.usuario).subscribe(response => {
      // console.log(response);

      this.authService.guardarUsuario(response.access_token);
      this.authService.guardarToken(response.access_token);
      this.authService.guardarRefreshToken(response.refresh_token);

      let usuario: Usuario = this.authService.usuario;
      this.route.navigate(['/clientes']);
      Swal.fire({
        title: 'Login',
        text: `Hola ${usuario.username}, has iniciado sesión con exito`,
        icon: 'success'
      })
    }, error => {
      if (error.status == 400) {
        Swal.fire({
          title: 'Error Login',
          text: `Usuario o clave incorrectas`,
          icon: 'error'
        })
      }
    });
  }
}
