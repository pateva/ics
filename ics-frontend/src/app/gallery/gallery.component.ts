import { Component } from '@angular/core';
import { DataService } from '../data/data.service';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { ImageClassificationResponse } from '../interfaces/imageClassificationResponse';

@Component({
  selector: 'ics-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent {
  images: ImageClassificationResponse[] = [];
  postError = false;
  postErrorMessage = '';
  searchQuery = ' ';


  constructor(private dataService: DataService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.dataService.getAllImages().subscribe(
      result => {
        this.images = result;
      },
      error => {
        this.onHttpError(error);
      }
    );

    this.route.queryParams.subscribe(params => {
      this.searchQuery = params['labels'];
    })

    if (this.searchQuery) {
      this.searchImages();
    }
  }

  onHttpError(errorResponse: any) {
    console.log("Error: ", errorResponse);
    this.postError = true;
    this.postErrorMessage = errorResponse.error?.errorMessage;
  }

  searchImages() {
    const query = this.getSearchQuery(this.searchQuery);
    this.dataService.getImagesByLabels(query).subscribe(

      result => {
        this.images = result;
        this.updateUrl(query);
      },
      error => {
        this.onHttpError(error);
      }
    );
  }

  getSearchQuery(input: string): string[] {
    return input.split(' ').filter(word => word.trim() !== '');
  }

  updateUrl(queryParams: any) {
    this.router.navigate([], { queryParams });
  }

  navigateToImage(id: number) {
    this.router.navigateByUrl(`/image-classification/${id}`);
  }

  closeOverlay() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigateByUrl(currentUrl);
    });
  }
}
