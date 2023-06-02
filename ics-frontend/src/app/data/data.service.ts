import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { PostRequestBody } from './postRequestBody';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/images';

  postImageUrl(imageUrl: string) : Observable<any> {

    return this.http.post(this.baseUrl, this.postImageRequestBody(imageUrl));
  }

  getImageById(imageId: number) : Observable<any> {
    return this.http.get( this.baseUrl + `/${imageId}`);
  }

  getAllImages() : Observable<any> {
    return this.http.get(this.baseUrl);
  }

  getImagesByLabels(labels: string[]) : Observable<any> {
    const queryParams = labels.map(label => `labels=${encodeURIComponent(label)}`).join('&');
    return this.http.get(this.baseUrl + `?${queryParams}`);
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
