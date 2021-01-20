import { Component } from '@angular/core';
import { UserService } from './user.service';
import { Client } from './model/client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  clients: Client[] = [];

  constructor(private userService: UserService) {
  }

  onClickButton(): void {
    this.userService.getAllClients().subscribe((response: Client[]) => this.clients = response);
  }
}
