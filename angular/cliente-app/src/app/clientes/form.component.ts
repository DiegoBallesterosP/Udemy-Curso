import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import {Router, ActivatedRoute} from '@angular/router';
import swal from 'sweetalert2';
@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

   public cliente: Cliente = new Cliente()
   public titulo:string = "Crear Cliente"
  constructor(private clienteService:ClienteService, private router:Router, private activatedRoute: ActivatedRoute) { }
   

  ngOnInit(){
    this.cargarCliente()
  }

  cargarCliente():void{
    this.activatedRoute.params.subscribe(params => {
      let id = params ['id']
      if(id){
        this.clienteService.getCliente(id).subscribe((cliente) => this.cliente =cliente)
      }
    })
  }

  create(): void{
    this.clienteService.create(this.cliente)
    .subscribe(cliente => {
      this.router.navigate(['/clientes'])
      swal('Nuevo Cliente', `El cliente ${cliente.nombre} ha sido creado con exito`, 'success')
    }
    )
  }

    update():void{
      this.clienteService.update(this.cliente)
      .subscribe(cliente => {
        this.router.navigate(['/clientes'])
        swal('Cliente Actualizado', `El cliente ${cliente.nombre} ha sido actualizado con exito`, 'success')
      }
      )
    }
}