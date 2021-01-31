import { Router } from '@angular/router';
import { AuthService } from './../clientes/services/auth.service';
import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  title = 'App Angular+Spring';
  constructor(public authService: AuthService, private route: Router) { }
  logout(): void {
    let username = this.authService.usuario.username;
    this.authService.logout();
    Swal.fire({
      title: 'Logout',
      text: `Hola ${username}, has cerrado sesión con éxito`,
      icon: 'success'
    });
    this.route.navigate(['/login'])
  }

}
