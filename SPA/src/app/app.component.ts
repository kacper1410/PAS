import { Component } from '@angular/core';
import { UserService } from './user.service';
import { Client } from './model/client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  title = 'SPA';
  clients: Client[] = [];

  constructor(private userService: UserService) {
  }

  onClickButton(): void {
    this.clients = this.userService.getAllClients().subscribe();
  }
}
