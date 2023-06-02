import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageIdService {

  constructor() { }

  imageId: number = 0;
}
