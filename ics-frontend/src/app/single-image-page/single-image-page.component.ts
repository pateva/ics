import { Component } from '@angular/core';
import { DataService } from '../data/data.service';
import { ImageIdService } from '../data/image-id.service';
import { ActivatedRoute } from '@angular/router';


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
  id: number = 0;

  constructor(private dataService: DataService,
    private imageIdService: ImageIdService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    })

    this.dataService.getImageById(this.id).subscribe(
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
      //this was variant 1 where I shared the id via the service
    }

    onHttpError(errorResponse:any) {
      console.log("Error: ", errorResponse);
      this.postError = true;
      this.postErrorMessage = errorResponse.error.errorMessage;
    }
  }

  

