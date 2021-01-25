import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from './model/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {

  newUser: User = {
    login: 'login',
    name: 'name',
    lastName: 'last name'
  };

  constructor(private userService: UserService) {
  }

  addAdministrator(): void {
    this.userService.addAdministrator(this.newUser).subscribe();
  }

  addEmployee(): void {
    this.userService.addEmployee(this.newUser).subscribe();
  }

  addClient(): void {
    this.userService.addClient(this.newUser).subscribe();
  }
}
