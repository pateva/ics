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

    console.log(imageUrl);

    return this.http.post('http://localhost:8080/images', this.postImageRequestBody(imageUrl));
    
    //return of(image);
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
