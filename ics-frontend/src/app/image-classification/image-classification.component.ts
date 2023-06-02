import { Component } from '@angular/core';
import {NgForm, NgModel } from '@angular/forms';
import { DataService } from '../data/data.service';
import { NavigationExtras, Router } from '@angular/router';
import { ImageIdService } from '../data/image-id.service';

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

  constructor(private router: Router, 
    private dataService: DataService,
    private imageIdService: ImageIdService) {
  }

  onSubmit(form: NgForm) {
    if(form.valid) {
    this.isWaiting = true;
    console.log("In onSubmit: ", form.valid);
    this.dataService.postImageUrl(this.imageUrl).subscribe(
      result => {
        console.log("Success: ", result);
        this.setImageId = result.imageId;
        this.isWaiting = false; // Update isWaiting to false
        this.router.navigateByUrl(`/image-classification/${this.getImageId}`);
      },
      error => {
        this.onHttpError(error);
        this.isWaiting = false; // Update isWaiting to false
      }
    );}   
  }

  navigateToImageClassification(imageId: number) {
    const navigationExtras: NavigationExtras = {
      state: {
        imageId: imageId
      }
    };
    this.router.navigateByUrl(`/image-classification/${imageId}`, navigationExtras);
  }

  onHttpError(errorResponse:any) {
    console.log("Error: ", errorResponse);
    this.postError = true;
    this.postErrorMessage = errorResponse.error?.errorMessage;
  }

  onBlur(field: NgModel) {
    console.log("On blur:", field.valid);
    
  }

  get getImageId(): number {
    return this.imageIdService.imageId;
  }

  set setImageId(id: number) {
    this.imageIdService.imageId = id;
  }

}


