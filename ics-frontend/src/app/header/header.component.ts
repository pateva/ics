import { Component } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'ics-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  @Output() otherThemeEvent = new EventEmitter<boolean>();
  otherTheme = false;

  changeTheme() {
    this.otherTheme = !this.otherTheme;
    this.otherThemeEvent.emit(this.otherTheme);
  }
}


