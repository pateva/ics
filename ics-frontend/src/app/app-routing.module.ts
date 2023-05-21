import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GalleryComponent} from './gallery/gallery.component';
import { SingleImagePageComponent } from './single-image-page/single-image-page.component';
import { ImageClassificationComponent } from './image-classification/image-classification.component';

const routes: Routes = [
  {path: 'gallery', component: GalleryComponent},
  {path: 'imageClassification', component: ImageClassificationComponent},
  {path: 'singleImageView', component: SingleImagePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
