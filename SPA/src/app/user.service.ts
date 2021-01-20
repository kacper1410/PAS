import { Injectable } from '@angular/core';
import { Administrator } from './model/administrator';
import { Employee } from './model/employee';
import { Client } from './model/client';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = 'http://localhost:8080/PAS/api/user';

  constructor(private http: HttpClient) {
  }

  getAllClients(): any {
    return this.http.get(this.url + '/getAllClients');
  }
}
