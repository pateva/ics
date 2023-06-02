import { Component } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'ics-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  @Output() otherThemeEvent = new EventEmitter<boolean>();

  changeTheme() {
    this.otherThemeEvent.emit(!this.otherThemeEvent);
  }
}


