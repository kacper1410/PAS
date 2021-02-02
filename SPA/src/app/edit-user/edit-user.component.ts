import { Component, Input } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.less']
})
export class EditUserComponent {

  etag: any = '';

  editUser: User = {
    userId: 0,
    login: 'login',
    name: 'name',
    lastName: 'last name'
  };

  constructor(private userService: UserService) {
  }

  updateAdministrator(): void {
    this.getEtag().subscribe((response: Response) => {
      this.etag = response.headers.get('etag');
      this.etag = this.etag.replace('\"', '');
      this.etag = this.etag.replace('\"', '');

      this.userService.updateAdministrator(this.editUser, this.etag).subscribe();
      this.clear();
    });
  }

  updateClient(): void {
    this.getEtag().subscribe((response: Response) => {
      this.etag = response.headers.get('etag');
      this.etag = this.etag.replace('\"', '');
      this.etag = this.etag.replace('\"', '');

      this.userService.updateClient(this.editUser, this.etag).subscribe();
      this.clear();
    });
  }

  updateEmployee(): void {
    this.getEtag().subscribe((response: Response) => {
      this.etag = response.headers.get('etag');
      this.etag = this.etag.replace('\"', '');
      this.etag = this.etag.replace('\"', '');

      this.userService.updateEmployee(this.editUser, this.etag).subscribe();
      this.clear();
    });
  }

  getEtag(): any {
    return this.userService.getUserEtag(this.editUser.userId);
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
