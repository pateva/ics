import { Component } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import { DataService } from '../data/data.service';

@Component({
  selector: 'ics-single-image-page',
  templateUrl: './single-image-page.component.html',
  styleUrls: ['./single-image-page.component.scss']
})
export class SingleImagePageComponent {

  // imageId: number = 0;
  postError: boolean = false;
  postErrorMessage: any;

  constructor(private route: ActivatedRoute, private router: Router, private dataService: DataService) {}

  ngOnInit() {
    //const imageId = this.route.snapshot.paramMap.get('imageId');
    //not sure how this should be

    this.dataService.getImageById(10).subscribe(
      result => {
        
      },
      error => {
        this.onHttpError(error);
      }
    );} 

    
    onHttpError(errorResponse:any) {
      console.log("Error: ", errorResponse);
      this.postError = true;
      this.postErrorMessage = errorResponse.error.errorMessage;
    }
  }

  

