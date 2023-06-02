import { Component } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import { DataService } from '../data/data.service';
import { ImageService } from '../data/image.service';
import { ImageIdService } from '../data/image-id.service';
import { LabelService } from '../data/label.service';

@Component({
  selector: 'ics-single-image-page',
  templateUrl: './single-image-page.component.html',
  styleUrls: ['./single-image-page.component.scss']
})
export class SingleImagePageComponent {

  postError: boolean = false;
  postErrorMessage: any;
  imageUrl: string = '';
  labels: string[] = [];

  constructor(private dataService: DataService,
    private imageIdService: ImageIdService) {}

  ngOnInit() {
    this.dataService.getImageById(this.getImageId).subscribe(
      result => {
        this.imageUrl = result.imageUrl;
        this.labels = result.labels.map((label:{ labelDescription: string }) => label.labelDescription);
        console.log(this.labels);
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

  

