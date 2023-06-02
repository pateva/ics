import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ics-frontend';
  otherTheme = false;

  changeTheme(newTheme:boolean) {
    this.otherTheme = newTheme;
  }
}
