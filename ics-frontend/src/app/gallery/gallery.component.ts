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
    this.route.queryParams.subscribe(params => {
      this.searchQuery = params['labels'];
    })

    if (this.searchQuery) {
      this.searchImages();
      console.log("search query " + this.searchQuery);
    } else {
      this.dataService.getAllImages().subscribe(
        result => {
          this.images = result;
        },
        error => {
          this.onHttpError(error);
        }
      );
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
        console.log("result num " + this.images.length);
      },
      error => {
        this.onHttpError(error);
      }
    );
  }

  getSearchQuery(input: string): string[] {
    console.log("two");
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
