import { Component } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import { DataService } from '../data/data.service';
import { ImageService } from '../data/image.service';
import { ImageIdService } from '../data/image-id.service';

@Component({
  selector: 'ics-single-image-page',
  templateUrl: './single-image-page.component.html',
  styleUrls: ['./single-image-page.component.scss']
})
export class SingleImagePageComponent {

  // imageId: number = 0;
  postError: boolean = false;
  postErrorMessage: any;
  imageUrl: string = '';

  constructor(private dataService: DataService,
    private imageIdService: ImageIdService) {}

  ngOnInit() {
    this.dataService.getImageById(this.getImageId).subscribe(
      result => {
        console.log(this.getImageId);
        this.imageUrl = result.imageUrl;
      },
      error => {
        this.onHttpError(error);
      }
    );} 

    get getImageId() : number {
      return this.imageIdService.imageId;
    }

    
    onHttpError(errorResponse:any) {
      console.log("Error: ", errorResponse);
      this.postError = true;
      this.postErrorMessage = errorResponse.error.errorMessage;
    }
  }

  

