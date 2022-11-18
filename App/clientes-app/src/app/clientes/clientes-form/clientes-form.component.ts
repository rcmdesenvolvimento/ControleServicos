import { Observable } from 'rxjs';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ClientesService } from './../../clientes.service';
import { Component, OnInit } from '@angular/core';

import { Cliente } from '../cliente';
//import { identifierModuleUrl } from '@angular/compiler';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css'],
})
export class ClientesFormComponent implements OnInit {
  cliente: Cliente;
  success: boolean = false;
  errors: String[];
  id: number;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activateRouter: ActivatedRoute
  ) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.activateRouter.params;
    params.subscribe((urlParams) => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service.getClienteById(this.id).subscribe(
          (response) => (this.cliente = response),
          (_errorResponse) => (this.cliente = new Cliente())
        );
      }
    });
  }

  onSubmit() {
    if (this.id) {
      this.service.atualizar(this.cliente).subscribe(
        (response) => {
          this.success = true;
          this.errors = null;
          console.log(response);
        },
        (errorResponse) => {
          console.log(errorResponse);
          this.errors = ['Erro ao atualizar o cliente.'];
        }
      );
    } else {
      this.service.salvar(this.cliente).subscribe(
        (response) => {
          console.log(response.cpf);
          // condição
          if (response.cpf == '67380700715') {
            console.log('Seja bem-vindo Ricardo Miranda');
          }
          this.success = true;
          this.errors = null;
          this.cliente = response;
        },
        (errorResponse) => {
          this.success = false;
          this.errors = errorResponse.error.erros;
        }
      );
    }
  }

  voltarParaListagem() {
    this.router.navigate(['/clientes-lista']);
  }
}
