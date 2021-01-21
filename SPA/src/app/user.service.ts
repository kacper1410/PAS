import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = 'http://localhost:8080/PAS/api/user';

  constructor(private http: HttpClient) {
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
}
