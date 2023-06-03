import { Injectable } from '@angular/core';
import { LabelService } from './label.service';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor() { }

imageId: number = 0;
imageUrl: string = '';
width: number = 0;
height: number = 0;
tags: LabelService[] = [];;
}
