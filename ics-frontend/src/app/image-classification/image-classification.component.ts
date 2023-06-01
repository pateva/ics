import { Component } from '@angular/core';
import {NgForm, NgModel } from '@angular/forms';
import { DataService } from '../data/data.service';
import { NavigationExtras, Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'ics-image-classification',
  templateUrl: './image-classification.component.html',
  styleUrls: ['./image-classification.component.scss'],

})
export class ImageClassificationComponent {
  originalImageUrl: string = ''; 
  postError = false;
  postErrorMessage = '';
  isWaiting: boolean = false;

  imageUrl: string = this.originalImageUrl

  onSubmit(form: NgForm) {
    if(form.valid) {
    this.isWaiting = true;
    console.log("In onSubmit: ", form.valid);
    this.dataService.postImageUrl(this.imageUrl).subscribe(
      result => {
        console.log("Success: ", result);
        const id = result.imageId;
        this.isWaiting = false; // Update isWaiting to false
        // this.navigateToImageClassification(id);
        this.router.navigateByUrl(`/image-classification/${id}`);
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
    this.postErrorMessage = errorResponse.error.errorMessage;
  }

  onBlur(field: NgModel) {
    console.log("On blur:", field.valid);
    
  }

  constructor(private router: Router, private dataService: DataService, private route: ActivatedRoute) {

  }
}


