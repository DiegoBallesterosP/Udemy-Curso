import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Region } from './Region';
@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  public cliente: Cliente = new Cliente()
  regiones: Region[];
  titulo: string = "Crear Cliente"

  public errores: string[];
  constructor(private clienteService: ClienteService, private router: Router, private activatedRoute: ActivatedRoute) { }


  ngOnInit() {
    this.cargarCliente()
  }

  cargarCliente(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if (id) {
        this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente)
      }
    });
    this.clienteService.getRegiones().subscribe(regiones=> this.regiones =regiones);
  }

  create(): void {
    console.log(this.cliente);
    this.clienteService.create(this.cliente)
      .subscribe(cliente => {
        this.router.navigate(['/clientes'])
        swal('Nuevo Cliente', `El cliente ${cliente.nombre} ha sido creado con exito`, 'success')
      },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      )
  }

  update(): void {
    console.log(this.cliente);
    this.clienteService.update(this.cliente)
      .subscribe(cliente => {
        this.router.navigate(['/clientes'])
        swal('Cliente Actualizado', `El cliente ${cliente.nombre} ha sido actualizado con exito`, 'success')
      },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      )
  }

  compararRegion(o1:Region, o2:Region):boolean{
    if (o1===undefined && o2===undefined){
      return true;
    }
return o1 === null || o2 ===null || o1 === undefined || o2 ===undefined? false: o1.id==o2.id;
  }
}