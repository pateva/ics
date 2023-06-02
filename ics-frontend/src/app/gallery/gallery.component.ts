import { Component } from '@angular/core';
import { DataService } from '../data/data.service';
import { ImageIdService } from '../data/image-id.service';
// import { MatToolbarModule } from '@angular/material/toolbar';
// import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'ics-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent {
  value: string = ''; // Declare the 'value' property
  images: string[] = [];
  postError = false;
  postErrorMessage = '';

  constructor( private dataService: DataService,
    private imageIdService: ImageIdService) {
  }

  ngOnInit() {
    this.dataService.getAllImages().subscribe(
      result => {
        this.images = result.map((image: { imageUrl: string }) => image.imageUrl);
      },
      error => {
        this.onHttpError(error);
      }
    );}

  onHttpError(errorResponse: any) {
    console.log("Error: ", errorResponse);
    this.postError = true;
    this.postErrorMessage = errorResponse.error.errorMessage;
  }
}
