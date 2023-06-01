import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { PostRequestBody } from './postRequestBody';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  postImageUrl(imageUrl: string) : Observable<any> {

    return this.http.post('http://localhost:8080/images', this.postImageRequestBody(imageUrl));
  }

  getImageById(imageId: number) : Observable<any> {
    return this.http.get(`http://localhost:8080/images/${imageId}`);
  }

  postImageRequestBody(imageUrl: string) : PostRequestBody {
    const requestBody: PostRequestBody = {
      records: [
        {
          _url: imageUrl
        }
      ]
    };
  
    return requestBody;
  }
}
