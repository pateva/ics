import { Component } from '@angular/core';
import { DataService } from '../data/data.service';
import { ActivatedRoute, NavigationExtras, Params, Router } from '@angular/router';
import { Label } from '../interfaces/labelResponse';

@Component({
  selector: 'ics-single-image-page',
  templateUrl: './single-image-page.component.html',
  styleUrls: ['./single-image-page.component.scss']
})
export class SingleImagePageComponent {

  postError: boolean = false;
  postErrorMessage: any;
  imageUrl: string = '';
  labels: Label[] = [];
  id: number = 0;
  numDisplayedLabels = 5;

  constructor(private dataService: DataService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    })

    this.dataService.getImageById(this.id).subscribe(
      result => {
        this.imageUrl = result.imageUrl;
        this.labels = result.labels;
        console.log(this.labels);
      },
      error => {
        this.onHttpError(error);
      }
    );
  }

  onHttpError(errorResponse: any) {
    console.log("Error: ", errorResponse);
    this.postError = true;
    this.postErrorMessage = errorResponse.error?.errorMessage;
  }

  searchByLabel(label: Label) {
    this.router.navigateByUrl(`/gallery?labels=${label.labelDescription}`);
  }

  getOtherLabels(): string {
    return this.labels.slice(this.numDisplayedLabels)
      .map(label => label.labelDescription)
      .join(', ');
  }
}



