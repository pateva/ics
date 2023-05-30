import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
@Component({
  selector: 'ics-image-classification',
  templateUrl: './image-classification.component.html',
  styleUrls: ['./image-classification.component.scss'],

})
export class ImageClassificationComponent {
  value: string = ''; // Declare the 'value' property
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);

  isValidUrl(): boolean {
    let regex = new RegExp('^(https?://)?([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}(/[a-zA-Z0-9-]*)*(\?[a-zA-Z0-9-]+=[a-zA-Z0-9-]*)?$');
  
    return regex.test(this.value);
  }

}


