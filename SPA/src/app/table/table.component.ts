import { Component, Input, OnInit } from '@angular/core';
import { User } from '../model/user';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.less']
})
export class TableComponent {

  @Input()
  users: User[] = [];

  constructor() { }
}
