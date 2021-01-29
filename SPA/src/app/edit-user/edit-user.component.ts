import { Component } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.less']
})
export class EditUserComponent {

  editUser: User = {
    userId: 0,
    login: 'login',
    name: 'name',
    lastName: 'last name'
  };

  constructor(private userService: UserService) {
  }

  updateAdministrator(): void {
    this.userService.updateAdministrator(this.editUser).subscribe();
    this.clear();
  }

  updateClient(): void {
    this.userService.updateClient(this.editUser).subscribe();
    this.clear();
  }

  updateEmployee(): void {
    this.userService.updateEmployee(this.editUser).subscribe();
    this.clear();
  }

  clear(): void {
    this.editUser = {
      userId: 0,
      login: '',
      name: '',
      lastName: ''
    };
  }
}
