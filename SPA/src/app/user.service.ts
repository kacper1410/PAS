import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Administrator } from './model/administrator';
import { Employee } from './model/employee';
import { User } from './model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = 'https://localhost:8181/PAS/api/user';
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

  getUserEtag(id: number | undefined): any {
    return this.http.get(this.url + '/getUserById/' + id, { observe: 'response' });
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

  updateAdministrator(editUser: Administrator, etag: string): any {
    return this.http.put<any>(this.url + '/updateAdministrator/' + editUser.userId, editUser, { headers: { 'if-match': etag }});
  }

  updateClient(editUser: User, etag: string): any {
    return this.http.put<any>(this.url + '/updateClient/' + editUser.userId, editUser, { headers: { 'if-match': etag }});
  }

  updateEmployee(editUser: Employee, etag: string): any {
    return this.http.put<any>(this.url + '/updateEmployee/' + editUser.userId, editUser, { headers: { 'if-match': etag }});
  }
}
