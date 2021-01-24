import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Administrator } from './model/administrator';
import { Employee } from './model/employee';
import { Client } from './model/client';
import { User } from './model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = 'http://localhost:8080/PAS/api/user';
  http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  getAllClients(): any {
    return this.http.get<any>(this.url + '/getAllClients');
  }

  getAllEmployees(): any {
    return this.http.get<any>(this.url + '/getAllEmployees');
  }

  getAllAdministrators(): any {
    return this.http.get<any>(this.url + '/getAllAdministrators');
  }

  getAllActiveClients(): any {
    return this.http.get<any>(this.url + '/getAllActiveClients');
  }

  addAdministrator(newUser: Administrator): any {
    return this.http.post<any>(this.url + '/addAdministrator', newUser);
  }

  addEmployee(newUser: Employee): any {
    return this.http.post<any>(this.url + '/addEmployee', newUser);
  }

  addClient(newUser: User): any {
    return this.http.post<any>(this.url + '/addClient', newUser);
  }
}
