import { Component } from '@angular/core';

@Component({
  selector: 'ics-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  otherTheme = false;

  changeTheme() {
    this.otherTheme = !this.otherTheme;
  }
}


