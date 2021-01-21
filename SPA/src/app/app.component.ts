import { Component } from '@angular/core';
import { UserService } from './user.service';
import { Client } from './model/client';
import { Employee } from './model/employee';
import { Administrator } from './model/administrator';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {

  clients: Client[] = [];
  employees: Employee[] = [];
  administrators: Administrator[] = [];

  activeClients: Client[] = [];

  constructor(private userService: UserService) {
  }

  getAllClients(): void {
    this.userService.getAllClients().subscribe((response: Client[]) => this.clients = response);
  }

  getAllEmployees(): void {
    this.userService.getAllEmployees().subscribe((response: Employee[]) => this.employees = response);
  }

  getAllAdministrators(): void {
    this.userService.getAllAdministrators().subscribe((response: Administrator[]) => this.administrators = response);
  }

  getAllActiveClients(): void {
    this.userService.getAllActiveClients().subscribe((response: Client[]) => this.activeClients = response);
  }

}
