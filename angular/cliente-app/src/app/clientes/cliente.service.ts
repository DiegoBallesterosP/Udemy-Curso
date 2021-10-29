import { Injectable } from '@angular/core';
import { CLIENTES } from './clientes.json';
import { Cliente } from './cliente';
import { Observable, pipe } from 'rxjs';
import { of, observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {map, catchError} from 'rxjs/operators';
import swal from 'sweetalert2';
import { Router } from '@angular/router';
import { formatDate, DatePipe} from '@angular/common';


@Injectable()
export class ClienteService {
  private urlEndPoint:string='http://localhost:8081/api/clientes';
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient, private router: Router) { }

  getClientes(): Observable<Cliente[]> {
    return this.http.get(this.urlEndPoint).pipe(
      map(response => {
      
        let clientes = response as Cliente[];
        return clientes.map(cliente=> {
          cliente.nombre = cliente.nombre.toUpperCase();
          
          let datePipe =new DatePipe('es');
          //cliente.createAt= datePipe.transform(cliente.createAt,'EEEE dd, MMMM, yyyy'); //formatDate(cliente.createAt, 'dd-mm-yyyy', 'es-US')
          return cliente;
        });
      }
      )
    ); 
    }


    create(cliente:Cliente): Observable<Cliente>{
      return this.http.post(this.urlEndPoint, cliente, {headers: this.httpHeaders}).pipe(
        map((response:any)=>response.cliente as Cliente),
        catchError(e =>{

          if(e.status==400){
            return throwError(e);
          }
          console.error(e.error.mensaje);
          swal(e.error.mensaje, e.error.mensaje, 'error');
          return throwError(e);
        })
      );
    }

    getCliente(id): Observable<Cliente>{
      return this.http.get<Cliente>(`${this.urlEndPoint}/${id}`).pipe(
        catchError(e =>{
          this.router.navigate(['/clientes']);
          console.error(e.error.mensaje);
          swal('Error al editar', e.error.mensaje, 'error');
          return throwError(e);
        })
      );
    }

    update(cliente: Cliente): Observable<Cliente>{
      return this.http.put(`${this.urlEndPoint}/${cliente.id}`, cliente,{headers: this.httpHeaders}).pipe(
        map((response:any)=>response.cliente as Cliente),
        catchError(e =>{

          if(e.status==400){
            return throwError(e);
          }

          console.error(e.error.mensaje);
          swal(e.error.mensaje, e.error.mensaje, 'error');
          return throwError(e);
        })
      );
    }
    
    delete(id: number): Observable<Cliente>{
      return this.http.delete<Cliente>(`${this.urlEndPoint}/${id}`,{headers: this.httpHeaders}).pipe(
        catchError(e =>{
          console.error(e.error.mensaje);
          swal(e.error.mensaje, e.error.mensaje, 'error');
          return throwError(e);
        })
      );
    }
}
 