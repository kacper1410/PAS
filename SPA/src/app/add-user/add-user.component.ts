import { Component } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.less']
})
export class AddUserComponent {

  newUser: User = {
    login: 'login',
    name: 'name',
    lastName: 'last name'
  };

  constructor(private userService: UserService) { }

  addAdministrator(): void {
    this.userService.addAdministrator(this.newUser).subscribe();
  }

  addEmployee(): void {
    this.userService.addEmployee(this.newUser).subscribe();
  }

  addClient(): void {
    this.userService.addClient(this.newUser).subscribe();
  }

  clear(): void {
    this.newUser = {
      login: '',
      name: '',
      lastName: ''
    };
  }
}
