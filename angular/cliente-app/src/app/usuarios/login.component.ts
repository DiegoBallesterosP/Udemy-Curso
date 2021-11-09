import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import { Usuario } from './usuario';
import { AuthService} from './auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  titulo:string ='Por favor Sing In!';
  usuario:Usuario;

  constructor(private authService: AuthService, private router:Router) {
    this.usuario=new Usuario();
  }

  ngOnInit() {
  }

  login():void{
    console.log(this.usuario);
    if(this.usuario.username==null||this.usuario.password==null){
      swal('Error Login', ' Username o password vacias!', 'error');
      return;
    }

    this.authService.login(this.usuario).subscribe(response=>{
      console.log(response);
      this.router.navigate(['/clientes']);
      swal('Login', `Bienvenido ${response.username}, has iniciado sesión con éxito!`, 'success');
    })
  
  } 
}
