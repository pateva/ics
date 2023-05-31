import { Component } from '@angular/core';
import {NgForm, NgModel } from '@angular/forms';
@Component({
  selector: 'ics-image-classification',
  templateUrl: './image-classification.component.html',
  styleUrls: ['./image-classification.component.scss'],

})
export class ImageClassificationComponent {
  originalImageUrl: string = ''; 
  imageUrl: string = this.originalImageUrl


  onSubmit(form: NgForm) {
    console.log("In onSubmit: ", form.valid);
  }

  onBlur(field: NgModel) {
    console.log("On blur:", field.valid);
    
  }
}


