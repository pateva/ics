import { Component } from '@angular/core';
import { DataService } from '../data/data.service';
import { ImageIdService } from '../data/image-id.service';
import { Router } from '@angular/router';
// import { MatToolbarModule } from '@angular/material/toolbar';
// import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'ics-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent {
  images = [];
  postError = false;
  postErrorMessage = '';
  searchQuery = ' ';


  constructor( private dataService: DataService,
    private imageIdService: ImageIdService,
    private router: Router) {
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

  searchImages() {
      this.dataService.getImagesByLabels(this.getSearchQuery(this.searchQuery)).subscribe(
        result => {
          this.images = result.map((image: { imageUrl: string }) => image.imageUrl);
        },
        error => {
          this.onHttpError(error);
        }
      );
  }

  getSearchQuery(input: string) : string[] {
    return input.split(' ').filter(word => word.trim() !== '');
  }

  navigateToImage(id: number) {
    this.router.navigateByUrl(`/image-classification/${id}`);
  }
}
