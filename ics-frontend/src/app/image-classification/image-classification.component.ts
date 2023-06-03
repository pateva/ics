import { Component } from '@angular/core';
import { NgForm, NgModel } from '@angular/forms';
import { DataService } from '../data/data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'ics-image-classification',
  templateUrl: './image-classification.component.html',
  styleUrls: ['./image-classification.component.scss'],

})
export class ImageClassificationComponent {
  postError = false;
  postErrorMessage = '';
  isWaiting = false;
  imageUrl = ' ';

  constructor(private router: Router, private dataService: DataService) {

  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.isWaiting = true;
      console.log("In onSubmit: ", form.valid);
      this.dataService.postImageUrl(this.imageUrl).subscribe(
        result => {
          console.log("Success: ", result);
          const id = result.imageId;
          this.isWaiting = false; // Update isWaiting to false
          this.router.navigateByUrl(`/image-classification/${id}`);
        },
        error => {
          this.onHttpError(error);
          this.isWaiting = false; // Update isWaiting to false
        }
      );
    }
  }

  onHttpError(errorResponse: any) {
    console.log("Error: ", errorResponse);
    this.postError = true;
    this.postErrorMessage = errorResponse.error?.errorMessage;
  }

  onBlur(field: NgModel) {
    console.log("On blur:", field.valid);

  }
}


