import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GalleryComponent} from './gallery/gallery.component';
import { SingleImagePageComponent } from './single-image-page/single-image-page.component';
import { ImageClassificationComponent } from './image-classification/image-classification.component';

const routes: Routes = [
  {path: 'gallery', component: GalleryComponent},
  {path: 'image-classification', component: ImageClassificationComponent},
  {path: 'image-classification/:id', component: SingleImagePageComponent},
  {path: '', redirectTo: 'image-classification', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }