import { Component, OnInit } from '@angular/core';

import { ClientService } from '../../services/client.service';
import { Client } from '../../interfaces/client';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

const clientCollection: Client[] = [];

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {

  clientForm: FormGroup = this.fb.group({
    firstName: ['', [Validators.required]],
    lastName: ['', [Validators.required]],
    phone: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
    email: ['', [Validators.required, Validators.email]]
  });

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'phone', 'actions'];
  dataSource = new MatTableDataSource(clientCollection);

  constructor(
    private clientService: ClientService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getClients();
  }

  onSave() {
    if (this.clientForm.invalid) {
      this.clientForm.markAllAsTouched();
      return;
    }
    const { firstName, lastName, phone, email } = this.clientForm.value;
    let client: Client;
    client = {
      id: 0,
      firstName: firstName,
      lastName: lastName,
      phone: phone,
      email: email
    }

    this.clientService.saveClient(client).subscribe((data: any[]) => {
      alert("Cliente guardado correctamente.");
      this.clientForm.reset();
      this.getClients();
    })
  }

  getClients() {
    this.clientService.getClients()
      .subscribe(
        (data) => {

          this.dataSource.data = [];
          clientCollection.length = 0;

          data.forEach(function (client: Client, index: number) {
            clientCollection.push(
              {
                id: client.id,
                firstName: client.firstName,
                lastName: client.lastName,
                phone: client.phone,
                email: client.email
              });
          });

          this.dataSource = new MatTableDataSource(clientCollection);
        }
      );
  }


  deleteClient(id: number) {
    if (confirm("¿Está seguro de eliminar el cliente?")) {
      this.clientService.deleteClient(id).subscribe((data: any[]) => {
        this.getClients();
        alert("Cliente eliminado correctamente.");
      })
    }
  }
}
