import { Cliente } from "src/app/clientes/cliente";
import { Itemfactura } from "./itemfactura";

export class Factura {
    id : number;
    descripcion: string;
    observacion: string;
    items: Array<Itemfactura>=[];
    cliente: Cliente;
    total:number;
    createAt: string;

    calcularGranTotal(): number{
        this.total = 0;
        this.items.forEach((item:Itemfactura)=>{
        this.total+=item.calcularImporte();    
        });
        return this.total;
    }
}
