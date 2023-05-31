import { Component } from '@angular/core';
import {NgForm, NgModel } from '@angular/forms';
import { DataService } from '../data/data.service';
@Component({
  selector: 'ics-image-classification',
  templateUrl: './image-classification.component.html',
  styleUrls: ['./image-classification.component.scss'],

})
export class ImageClassificationComponent {
  originalImageUrl: string = ''; 
  postError = false;
  postErrorMessage = '';

  imageUrl: string = this.originalImageUrl

  onSubmit(form: NgForm) {
    console.log("In onSubmit: ", form.valid);
    this.dataService.postImageUrl(this.imageUrl).subscribe(
      result => console.log("Success: ", result),
      error => this.onHttpError(error)
    );
  }

  onHttpError(errorResponse:any) {
    console.log("Error: ", errorResponse);
    this.postError = true;
    this.postErrorMessage = errorResponse.error.errorMessage;
  }

  onBlur(field: NgModel) {
    console.log("On blur:", field.valid);
    
  }

  constructor(private dataService: DataService) {

  }
}


