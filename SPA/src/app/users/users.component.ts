import { Component } from '@angular/core';
import { Client } from '../model/client';
import { Employee } from '../model/employee';
import { Administrator } from '../model/administrator';
import { UserService } from '../user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.less']
})
export class UsersComponent {

  activeClients: Client[] = [];
  clients: Client[] = [];
  employees: Employee[] = [];
  administrators: Administrator[] = [];

  constructor(private userService: UserService) { }

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
